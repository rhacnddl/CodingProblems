package org.gorany.backjoon.스티커;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] s;
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++) {
            int n = Integer.parseInt(br.readLine());

            s = new int[2][n + 1];
            dp = new int[2][n + 1];

            for (int j = 0; j < 2; j++) {
                st = new StringTokenizer(br.readLine());
                for (int z = 1; z <= n; z++)
                    s[j][z] = Integer.parseInt(st.nextToken());

                dp[0][1] = s[0][1];
                dp[1][1] = s[1][1];
                for (int a = 2; a <= n; a++) {
                    dp[1][a] = Math.max(dp[0][a - 1], dp[0][a - 2]) + s[1][a];
                    dp[0][a] = Math.max(dp[1][a - 1], dp[1][a - 2]) + s[0][a];
                }
            }
            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
    }
}
