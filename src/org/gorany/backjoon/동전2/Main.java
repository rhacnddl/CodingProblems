package org.gorany.backjoon.동전2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 1000000000;
    static int[] dp = new int[100001], coin = new int[101];
    static int n, k;

    static void Dynamic(){

        for(int i=1; i<=n; i++){
            dp[ coin[i] ] = 1;

            for(int j=coin[i]; j<=k; j++){
                int tmp = dp[j - coin[i]] + 1;

                dp[j] = tmp < dp[j]? tmp : dp[j];
            }
        }

        System.out.println(dp[k] == INF? -1:dp[k]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++)
            coin[i] = Integer.parseInt(br.readLine());

        Arrays.fill(dp, INF);
        Dynamic();
    }
}
