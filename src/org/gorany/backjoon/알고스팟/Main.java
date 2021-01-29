package org.gorany.backjoon.알고스팟;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int y, x, d;
        public Edge(int y, int x, int d){
            this.y=y;
            this.x=x;
            this.d=d;
        }
        @Override
        public int compareTo(Edge e) {
            return d-e.d;
        }
    }
    static int[][] dp;
    static int[][] maze;
    static final int INF = 1000000000;
    static Queue<Edge> Q = new PriorityQueue<>();
    static int m, n;

    static int[] Y = {1, 0, -1, 0};
    static int[] X = {0, 1, 0, -1};

    static void dijkstra(){

        dp[0][0] = 0;
        Q.add(new Edge(0,0,0));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            int y = edge.y;
            int x = edge.x;
            int distance = edge.d;

            if(dp[y][x] < distance) continue;

            for(int i=0; i<4; i++){
                int ny = y + Y[i];
                int nx = x + X[i];

                if((ny>=0 && ny<n) && (nx >= 0 && nx < m)) {

                    int nextDistance = distance + maze[ny][nx];
                    if (dp[ny][nx] > nextDistance) {
                        dp[ny][nx] = nextDistance;
                        Q.add(new Edge(ny, nx, nextDistance));
                    }
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        maze = new int[n][m];
        dp = new int[n][m];

        for(int i=0; i<n; i++){
            String[] str = br.readLine().split("");
            for(int j=0; j<m; j++){
                maze[i][j] = Integer.parseInt(str[j]);
                dp[i][j] = INF;
            }
        }

        dijkstra();

        System.out.println(dp[n-1][m-1]);
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
