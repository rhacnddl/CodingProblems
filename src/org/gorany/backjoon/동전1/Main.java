package org.gorany.backjoon.동전1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int coin[];
    static int[] dp;
    static void getAnswer(int n, int k){

        for(int i=1; i<=n; i++){
            if(coin[i] > k) continue;
            dp[coin[i]]++;

            for(int j=coin[i]; j<=k; j++)
                dp[j] += dp[j-coin[i]];
        }
        System.out.println(dp[k]);
        Arrays.stream(dp).forEach(System.out::println);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        coin = new int[n+1];
        dp = new int[k+1];
        for(int i=1; i<=n; i++)
            coin[i] = Integer.parseInt(br.readLine());

        getAnswer(n, k);
    }
}
