package org.gorany.backjoon.설탕배달;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int dp[] = new int[n+1];

        dp[0] = dp[1] = dp[2] = -1;
        dp[3] = 1;
        if(n>3) dp[4] = -1;
        if(n>4) dp[5] = 1;
        if(n>5)
            for(int i=6; i<=n; i++){
                if(dp[i-3] == -1 && dp[i-5] == -1) {
                    dp[i] = -1;
                    continue;
                }
                int tmp1 = 999999;
                int tmp2 = 999999;
                if(dp[i-3] > 0)
                    tmp1 = dp[i-3];
                if(dp[i-5] > 0)
                    tmp2 = dp[i-5];
                dp[i] = Math.min(tmp1, tmp2) + 1;
                //dp[i] = Math.min(dp[i-3], dp[i-5]) + 1;
            }
        System.out.println(dp[n]);
        for(int i=3; i<=n; i++)
            System.out.println(i+ " : " + dp[i]);
    }
}
