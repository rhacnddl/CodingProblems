package org.gorany.backjoon.플로이드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 1000000000;
    static int[][] d = new int[101][101];

    static void Floyd(int n){

        for(int k=1; k<=n; k++)
            for(int i=1; i<=n; i++)
                for(int j=1; j<=n; j++)
                    if(i == j) continue;
                    else d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++)
                System.out.print(d[i][j] != INF? d[i][j] + " " : 0 + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++)
            Arrays.fill(d[i], INF);

        for(int i=0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int depart = Integer.parseInt(st.nextToken());
            int arrive = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            d[depart][arrive] = Math.min(d[depart][arrive], distance);
        }

        Floyd(n);
    }
}
