package org.gorany.backjoon.평범한배낭;

import java.util.Scanner;

public class Main {

    private static int knapSack(int[] val, int[] w, int n, int k) {

        int[][] dp = new int[n+1][k+1];
        int max = 0;

        for(int i=1; i<=n; i++){
            for(int j=1; j<=k; j++){
                if(w[i] <= j){
                    dp[i][j] = Math.max(val[i] + dp[i-1][j-w[i]], dp[i-1][j]);
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        for(int i=0; i<=n; i++){
            for(int j=0; j<=k; j++)
                System.out.printf("%2d ", dp[i][j]);
            System.out.println();
        }

        return max;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); //물건의 개수
        int K = sc.nextInt(); //배낭이 견디는 무게
        if(N < 1 || N > 100 || K < 1 || K > 100000) System.exit(0);
        int[] val = new int[N+1];
        int[] w = new int[N+1];
        for(int i=1; i<=N; i++){
            w[i] = sc.nextInt(); //무게
            val[i] = sc.nextInt(); //가치
            if(w[i] < 1 || w[i] > 100000 || val[i] < 0 || val[i] > 1000) System.exit(0);
        }
        System.out.printf("%d\n", knapSack(val, w, N, K));
    }
}
