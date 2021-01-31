package org.gorany.backjoon.미로만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge>{
        int y,x,d;
        public Edge(int y, int x, int d){
            this.y=y;
            this.x=x;
            this.d=d;
        }
        @Override
        public int compareTo(Edge o) {
            return d - o.d;
        }
    }
    static final int INF = 1000000000;
    static Queue<Edge> Q = new PriorityQueue<>();
    static int[][] map;
    static int[][] dp;
    static int[] Y = {1, -1, 0, 0};
    static int[] X = {0, 0, 1, -1};

    static int Dijkstra(int n){

        dp[1][1] = 0;
        Q.add(new Edge(1,1,0));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            int y = edge.y;
            int x = edge.x;
            int d = edge.d;

            if(dp[y][x] < d) continue;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                if((ny > 0 && nx > 0) && (ny <= n && nx <= n)) {
                    int nd = (map[ny][nx] == 0)?d + 1:d;
                    if(dp[ny][nx] > nd){
                        dp[ny][nx] = nd;
                        Q.add(new Edge(ny, nx, nd));
                    }
                }
            }
        }

        return dp[n][n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new int[n+1][n+1];
        map = new int[n+1][n+1];

        for(int i=1; i<=n; i++){
            String[] st = br.readLine().split("");
            for(int j=1; j<=n; j++){
                map[i][j] = Integer.parseInt(st[j-1]);
                dp[i][j] = INF;
            }
        }

        System.out.println(Dijkstra(n));
    }
}
