package org.gorany.backjoon.다리놓기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][]dp;
    static void Dynamic(int n, int m){

        for(int i=1; i<=m; i++) dp[1][i] = i;

        for(int i=2; i<=n; i++)
            for(int j=1; j<=m; j++)
                for(int k=1; k<j; k++)
                    dp[i][j] += dp[i-1][k];

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            dp = new int[30][30];

            if(n != m) {

                Dynamic(n, m);
                System.out.println(dp[n][m]);
            }
            else
                System.out.println(1);
        }
    }
}
