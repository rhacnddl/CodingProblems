package org.gorany.backjoon.벽부수고이동하기3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x, m, w;
        boolean day;
        public Point(int yy, int xx, int move, int wall, boolean day){
            y=yy; x=xx; w=wall; m=move;
            this.day = day;
        }
    }
    static int n, m, k, result = 1000000000;
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static char[][] map = new char[1001][1001];
    static boolean[][][][] visit = new boolean[1001][1001][11][2];

    static void BFS(){
        Queue<Point> Q = new LinkedList<>();

        visit[1][1][0][0] = true;
        Q.add(new Point(1, 1, 1, 0, true));

        while(!Q.isEmpty()){
            Point p = Q.poll();

            int y = p.y;
            int x = p.x;
            int w = p.w;
            int d = p.day? 0:1;
            int nd = d == 0? 1:0;

            int move = p.m;
            if(y == n && x == m) {
                result = Math.min(result, move);
            }

            if(!visit[y][x][w][nd]){
                visit[y][x][w][nd] = true;
                Q.add(new Point(y, x, move + 1, w, !p.day));
            }
            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                if(ny < 1 || nx < 1 || ny > n || nx > m || visit[ny][nx][w][nd]) continue;

                if(map[ny][nx] == '0') {
                    visit[ny][nx][w][nd] = true;
                    Q.add(new Point(ny, nx, move + 1, w, !p.day));
                }

                else if(map[ny][nx] == '1' && p.day && w < k) {
                    visit[ny][nx][w+1][nd] = true;
                    Q.add(new Point(ny, nx, move + 1, w + 1, false));
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=m; j++)
                map[i][j] = str[j-1];
        }

        BFS();
        System.out.println(result == 1000000000? -1:result);
    }
}