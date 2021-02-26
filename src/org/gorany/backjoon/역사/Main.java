package org.gorany.backjoon.역사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = 1000000000;
    static int[][] distance = new int[501][501];

    static void FloydWarshall(int n){

        for(int k=1; k<=n; k++)
            for(int i=1; i<=n; i++)
                for(int j=1; j<=n; j++)
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++)
            Arrays.fill(distance[i], INF);

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            distance[h][t] = 1;
        }

        FloydWarshall(n);

        int s = Integer.parseInt(br.readLine());

        for(int i=0; i<s; i++){
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if(distance[h][t] > distance[t][h])
                System.out.println(1);
            else if(distance[h][t] < distance[t][h])
                System.out.println(-1);
            else
                System.out.println(0);
        }
    }
}