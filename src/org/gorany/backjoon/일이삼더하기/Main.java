package org.gorany.backjoon.일이삼더하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        int[] dp = new int[12];
        dp[1]=1;
        dp[2]=2;
        dp[3]=4;
        for(int i=4; i<=11; i++)
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];

        for(int i=0; i<t; i++){

            System.out.println(dp[Integer.parseInt(br.readLine())]);

        }
    }
}
