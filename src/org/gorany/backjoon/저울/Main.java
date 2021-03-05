package org.gorany.backjoon.저울;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 1000000000;
    static int[][] d = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++)
                d[i][j] = (i != j) ? INF : 0;

        for(int i=0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int heavy = Integer.parseInt(st.nextToken());
            int light = Integer.parseInt(st.nextToken());

            d[heavy][light] = 1;
        }

        for(int k=1; k<=n; k++)
            for(int i=1; i<=n; i++)
                for(int j=1; j<=n; j++)
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);

        for(int i=1; i<=n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++)
                if(d[i][j] == INF && d[j][i] == INF)
                    cnt++;

            System.out.println(cnt);
        }
    }
}