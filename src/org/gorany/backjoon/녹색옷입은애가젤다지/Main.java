package org.gorany.backjoon.녹색옷입은애가젤다지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y,x;
        public Point(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
    static final int INF = 1000000000;
    static int[][] map;
    static int[][] dp;
    static int[] X = {1,0,-1,0};
    static int[] Y = {0, 1, 0, -1};
    static Queue<Point> Q = new LinkedList<>();

    static void BFS(int n, int cnt){

        dp[1][1] = map[1][1];
        Q.add(new Point(1,1));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];
                if((ny > 0 && ny <= n) && (nx > 0 && nx <= n)){
                    int nextVal = dp[y][x] + map[ny][nx];
                    if(dp[ny][nx] > nextVal){
                        dp[ny][nx] = nextVal;
                        Q.add(new Point(ny,nx));
                    }
                }
            }
        }
        System.out.println("Problem " + cnt + ": " + dp[n][n]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int c = 1;
        while(true){

            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            map = new int[n+1][n+1];
            dp = new int[n+1][n+1];

            for(int i=1; i<=n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=1; j<=n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dp[i][j] = INF;
                }
            }

            BFS(n, c++);
        }
    }
}
