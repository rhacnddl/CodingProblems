package org.gorany.backjoon.오르막수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main {

    static int[][] dp = new int[1001][10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        IntStream.range(0, 10).forEach(i->dp[1][i] = 1);

        for(int i=1; i<=n; i++)
            for(int j=0; j<10; j++)
                for(int k=j; k<10; k++)
                    dp[i][j] += (dp[i-1][k] % 10007);

        int sum = 0;
        for(int i=0; i<10; i++)
            sum += dp[n][i] % 10007;

        System.out.println(sum % 10007);
    }
}