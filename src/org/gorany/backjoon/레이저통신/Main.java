package org.gorany.backjoon.레이저통신;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x, dir, mirror;
        public Point(int yy, int xx, int d, int mirror){
            y=yy;
            x=xx;
            dir=d;
            this.mirror = mirror;
        }
    }
    static final int INF = 1000000000;
    static int[] Y = {-1, 0, 1, 0}, X = {0, -1, 0, 1}; //0:상  1:좌  2:하  3:우
    static char[][] map = new char[101][101];
    static int w, h;

    static int[][][] mir = new int[101][101][4];
    static boolean[][][] visit = new boolean[101][101][4];
    static void BFS(Point start){

        Queue<Point> Q = new LinkedList<>();

        for(int a=0; a<4; a++)
            mir[start.y][start.x][a] = 0;

        Q.add(new Point(start.y, start.x, -1, 0));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;
            int dir = p.dir;
            int mirror = p.mirror;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];
                int nMirror = mirror;

                if(ny < 1 || nx < 1 || ny > h || nx > w || visit[ny][nx][a] || map[ny][nx] == '*') continue;

                if(dir % 2 == 0 && a % 2 != 0 && dir >= 0)
                    nMirror++;
                else if(dir % 2 != 0 && a % 2 == 0 && dir >= 0)
                    nMirror++;

                visit[ny][nx][a] = true;
                mir[ny][nx][a] = nMirror;
                Q.add(new Point(ny, nx, a, nMirror));

                while(true){
                    ny = ny+Y[a];
                    nx = nx+X[a];

                    if(ny < 1 || nx < 1 || ny > h || nx > w || visit[ny][nx][a] || map[ny][nx] == '*') break;

                    visit[ny][nx][a] = true;
                    mir[ny][nx][a] = nMirror;
                    Q.add(new Point(ny, nx, a, nMirror));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        Point[] p = new Point[2];

        for(int i=1, idx=0; i<=h; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=w; j++){
                map[i][j] = str[j-1];

                if(map[i][j] == 'C')
                    p[idx++] = new Point(i, j, -1, 0);

                for(int a=0; a<4; a++)
                    mir[i][j][a] = INF;
            }
        }
        BFS(p[0]);

        int min = INF;
        for(int a=0; a<4; a++)
            min = Math.min(min, mir[p[1].y][p[1].x][a]);

        System.out.println(min);
    }
}//https://bingorithm.tistory.com/2