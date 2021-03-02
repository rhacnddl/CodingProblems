package org.gorany.backjoon.키순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 1000000000;
    static int[][] d = new int[501][501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++)
                if(i == j) continue;
                else d[i][j] = INF;

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            d[v2][v1] = 1;
        }

        for(int k=1; k<=n; k++)
            for(int i=1; i<=n; i++)
                for(int j=1; j<=n; j++)
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);

        int cnt = 0;
        for(int i=1; i<=n; i++) {
            boolean flag = true;

            for (int j = 1; j <= n; j++)
                if(d[i][j] == INF && d[j][i] == INF) {
                    flag = false;
                    break;
                }

            if(flag) cnt++;
        }

        System.out.println(cnt);
    }
}
