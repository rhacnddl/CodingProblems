package org.gorany.backjoon.연속합;

import java.util.Scanner;

public class Main {

    static int getAnswer(int[] arr, int n){

        int[] dp = new int[n+1];
        int max = dp[1] = arr[1];

        for(int i=2; i<=n; i++) {
            dp[i] = Math.max(arr[i], dp[i - 1] + arr[i]);
            if(dp[i] > max) max = dp[i];
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        if(n < 1 || n > 100000) System.exit(0);
        int[] arr = new int[n+1];

        for(int i=1; i<=n; i++){
            arr[i] = sc.nextInt();
            if(arr[i] < -1000 || arr[i] > 1000) System.exit(0);
        }
        System.out.printf("%d\n", getAnswer(arr, n));
    }
}
