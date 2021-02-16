package org.gorany.backjoon.합분해;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int MOD = 1000000000;
    static int[] arr = new int[201];
    static int[][] dp = new int[201][201];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i=1; i<k; i++)
            arr[i + 1] = arr[i] + i;

        Arrays.stream(arr).forEach(i->System.out.print(i + " "));
        System.out.println();

        dp[1][k] = k;

        for(int i=2; i<=n; i++)
            dp[i][k] = (dp[i-1][k] + arr[k]) % MOD;

        System.out.println(dp[n][k]);

        //dp[n][k]
        //dp[1][j] = i
        //dp[i][1] = dp[i-1][1] + 0
        //dp[i][2] = dp[i-1][2] + 1
        //dp[i][3] = dp[i-1][3] + 3
    }
}
