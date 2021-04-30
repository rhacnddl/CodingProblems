package org.gorany.backjoon.미로탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x, d, wall;

        public Point(int yy, int xx, int dist, int wall){
            y=yy;
            x=xx;
            d=dist;
            this.wall = wall;
        }
    }
    static int n, m;
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static int[][] map = new int[1001][1001];
    static boolean[][][] visit = new boolean[1001][1001][2];

    static int BFS(int sy, int sx, int ey, int ex){

        Queue<Point> Q = new LinkedList<>();

        visit[sy][sx][0] = true;
        Q.add(new Point(sy, sx, 0, 0));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;
            int dist = p.d;
            int wall = p.wall;

            if(y == ey && x == ex)
                return dist;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                if(ny < 1 || nx < 1 || ny > n || nx > m || visit[ny][nx][wall]) continue;

                if(map[ny][nx] == 1){
                    if(wall > 0) continue;
                    else{
                        visit[ny][nx][1] = true;
                        Q.add(new Point(ny, nx, dist + 1, 1));
                    }
                }
                else{
                    visit[ny][nx][wall] = true;
                    Q.add(new Point(ny, nx, dist + 1, wall));
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

        st = new StringTokenizer(br.readLine());
        int sY = Integer.parseInt(st.nextToken());
        int sX = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int eY = Integer.parseInt(st.nextToken());
        int eX = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(BFS(sY, sX, eY, eX));
    }
}