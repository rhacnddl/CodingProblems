package org.gorany.backjoon.통나무옮기기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    /* Class Def */
    static class Point{
        int y, x;
        public Point(int yy, int xx){
            y=yy;
            x=xx;
        }
    }
    static class Log{
        Point[] p;
        int dir, move; //0 수직 1 수평
        public Log(Point[] logs, int d, int m){
            p=logs;
            dir=d;
            move=m;
        }
    }
    /* Setting */
    static int n;
    static int[] Y={-1,1,0,0,-1,-1,1,1}, X={0,0,-1,1,-1,1,-1,1};
    static char[][] map = new char[51][51];
    static boolean[][][] visit = new boolean[51][51][2]; //0: 수직 1: 수평

    /* Methods Def */
    static boolean up(Point[] p){
        for(Point log:p)
            if(log.y < 1 || map[log.y][log.x] == '1')
                return false;

        return true;
    }

    static boolean down(Point[] p){
        for(Point log:p)
            if(log.y > n || map[log.y][log.x] == '1')
                return false;

        return true;
    }

    static boolean left(Point[] p){
        for(Point log:p)
            if(log.x < 1 || map[log.y][log.x] == '1')
                return false;

        return true;
    }

    static boolean right(Point[] p){
        for(Point log:p)
            if(log.x > n || map[log.y][log.x] == '1')
                return false;

        return true;
    }

    static boolean rotate(Point[] p, int dir) {
        if(p[1].y-1 < 1 || p[1].y+1 > n || p[1].x-1 < 1 || p[1].x+1 > n)
            return false;

        for(int a=0; a<8; a++)
            if(map[p[1].y+Y[a]][p[1].x+X[a]] == '1')
                return false;

        if(dir == 0){
            p[0].y++;
            p[0].x--;
            p[2].y--;
            p[2].x++;
        }
        else{
            p[0].y--;
            p[0].x++;
            p[2].y++;
            p[2].x--;
        }
        return true;
    }
    static boolean isRight(Point p, int y, int x){
        if(p.y+y < 1 || p.y+y > n || p.x+x < 1 || p.x+x > n) return false;

        return true;
    }
    static int BFS(Point[] s){
        Queue<Log> Q = new LinkedList<>();

        int dir_s = s[0].x == s[1].x? 0:1;
        visit[s[1].y][s[1].x][dir_s] = true;
        Q.add(new Log(s, dir_s, 0));

        while(!Q.isEmpty()){
            Log log = Q.poll();
            Point[] cur = log.p;
            int dir = log.dir;
            int move = log.move;

            if(map[cur[0].y][cur[0].x] == 'E' && map[cur[2].y][cur[2].x] == 'E')
                return move;

            /* 위쪽 */
            if(isRight(cur[1], -1, 0) && !visit[cur[1].y-1][cur[1].x][dir]) {
                Point[] up = new Point[3];
                for(int i=0; i<3; i++)
                    up[i] = new Point(cur[i].y-1, cur[i].x);

                if (up(up)) {
                    visit[up[1].y][up[1].x][dir] = true;
                    Q.add(new Log(up, dir, move + 1));
                }
            }

            /* 아래쪽 */
            if(isRight(cur[1], 1, 0) && !visit[cur[1].y+1][cur[1].x][dir]) {
                Point[] down = new Point[3];
                for (int i = 0; i < 3; i++)
                    down[i] = new Point(cur[i].y+1, cur[i].x);

                if (down(down)) {
                    visit[down[1].y][down[1].x][dir] = true;
                    Q.add(new Log(down, dir, move + 1));
                }
            }

            /* 왼쪽 */
            if(isRight(cur[1], 0, -1) && !visit[cur[1].y][cur[1].x-1][dir]) {
                Point[] left = new Point[3];
                for (int i = 0; i < 3; i++)
                    left[i] = new Point(cur[i].y, cur[i].x-1);

                if (left(left)) {
                    visit[left[1].y][left[1].x][dir] = true;
                    Q.add(new Log(left, dir, move + 1));
                }
            }

            /* 오른쪽 */
            if(isRight(cur[1], 0, 1) && !visit[cur[1].y][cur[1].x+1][dir]) {
                Point[] right = new Point[3];
                for (int i = 0; i < 3; i++)
                    right[i] = new Point(cur[i].y, cur[i].x+1);

                if (right(right)) {
                    visit[right[1].y][right[1].x][dir] = true;
                    Q.add(new Log(right, dir, move + 1));
                }
            }

            /* 회전 */
            int nDir = dir == 0 ? 1 : 0;
            if(!visit[cur[1].y][cur[1].x][nDir]) {
                Point[] rotate = new Point[3];
                for (int i = 0; i < 3; i++)
                    rotate[i] = new Point(cur[i].y, cur[i].x);

                if (rotate(rotate, dir)) {
                    visit[rotate[1].y][rotate[1].x][nDir] = true;
                    Q.add(new Log(rotate, nDir, move + 1));
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int idx = 0;
        Point[] log = new Point[3];

        for (int i = 1; i <= n; i++) {
            char[] strs = br.readLine().toCharArray();
            for (int j = 1; j <= n; j++) {
                map[i][j] = strs[j - 1];

                if (map[i][j] == 'B')
                    log[idx++] = new Point(i, j);
            }
        }
        System.out.println(BFS(log));
    }
}