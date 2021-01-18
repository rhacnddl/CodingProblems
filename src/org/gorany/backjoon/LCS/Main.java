package org.gorany.backjoon.LCS;

import java.util.Locale;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    static int getAnswer(String A, String B){

        int m = A.length(), n = B.length();
        int[][] dp = new int[m][n];

        IntStream.range(0, m).forEach(i->dp[i][0] = 0);
        IntStream.range(0, n).forEach(j->dp[0][j] = 0);

        for(int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if(A.charAt(i) == B.charAt(j))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String A = sc.nextLine();
        String B = sc.nextLine();
        if(A.length() > 1000 || B.length() > 1000) System.exit(0);

        A = " " + A.toUpperCase();
        B = " " + B.toUpperCase();

        System.out.printf("%d\n", getAnswer(A, B));
    }
}
