package org.gorany.backjoon.카드구매하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] dp = new int[1001];
    static int[] p = new int[1001];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            p[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = p[1];
        for(int i=2; i<=n; i++){
            int max = 0;
            for(int j=1; j<=i/2; j++){
                max = Math.max(dp[i-j] + dp[j], max);
            }
            dp[i] = Math.max(max, p[i]);
        }
        System.out.println(dp[n]);
        Arrays.stream(dp).forEach(i->System.out.print(i + " "));
    }
}