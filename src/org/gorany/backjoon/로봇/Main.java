package org.gorany.backjoon.로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point implements Comparable<Point>{
        int y, x, dir, cnt;

        public Point(int yy, int xx, int dir, int cnt){
            y=yy; x=xx;
            this.dir=dir;
            this.cnt=cnt;
        }

        @Override
        public int compareTo(Point o) {
            return cnt - o.cnt;
        }
    }

    static int n, m;
    static int[][] map = new int[101][101], dp = new int[101][101];
    static boolean[][] visit = new boolean[101][101];
    static int[][] X = {{1, 2, 3}, {-1, -2, -3}, {0, 0, 0}, {0, 0, 0}}, Y = {{0, 0, 0}, {0, 0, 0}, {1, 2, 3}, {-1, -2, -3}};

    static int BFS(Point start, Point end){

        Queue<Point> Q = new PriorityQueue<>();

        visit[start.y][start.x] = true;
        dp[start.y][start.x] = 0;
        Q.add(new Point(start.y, start.x, start.dir, 0));

        while(!Q.isEmpty()){
            Point p = Q.poll();

            if(p.y == end.y && p.x == end.x) {

                //System.out.println("종료 지점 도착 p.cnt : " + p.cnt + "  " + p);

                if(p.dir == end.dir);
                else if(p.dir + end.dir == 3 || p.dir + end.dir == 7) p.cnt += 2;
                else p.cnt +=1;

                return p.cnt;
            }

            int y = p.y;
            int x = p.x;
            int dir = p.dir;
            int cnt = p.cnt;

            //동 1, 서 2, 남 3, 북 4
            for(int a=1; a<=4; a++) {
                for (int b = 0; b < 3; b++) {
                    int ny = y + Y[a - 1][b];
                    int nx = x + X[a - 1][b];
                    int nCnt = cnt + 1;

                    if (ny < 1 || nx < 1 || ny > n || nx > m || visit[ny][nx]) continue;
                    if(map[ny][nx] == 1) break;

                    if (a == dir) nCnt += 0;
                    else if ((a + dir) == 3 || (a + dir) == 7) nCnt += 2;
                    else nCnt += 1;

                    visit[ny][nx] = true;
                    dp[ny][nx] = nCnt;
                    Q.add(new Point(ny, nx, a, nCnt));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        Point[] points = new Point[2];
        for(int i=0; i<2; i++){
            st = new StringTokenizer(br.readLine());
            int Y = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int Dir = Integer.parseInt(st.nextToken());
            points[i] = new Point(Y, X, Dir, 0);
        }

        System.out.println(BFS(points[0], points[1]));

        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
    }
}
/*
5 6
1 1 1 1 1 1
1 0 0 0 0 1
1 0 1 1 0 1
1 0 0 0 0 1
1 1 1 1 1 1
2 2 3
4 5 1
3 답, 4 오답
 */