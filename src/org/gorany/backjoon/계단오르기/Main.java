package org.gorany.backjoon.계단오르기;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n < 1 || n > 300) System.exit(0);

        int[] stairs = new int[n+1];

        for(int i=1; i<=n; i++){
            stairs[i] = sc.nextInt();
            if(stairs[i] > 10000 || stairs[i] < 1)System.exit(0);
        }
        System.out.printf("%d\n", getMaxScore(stairs, n));
    }

    private static int getMaxScore(int[] stairs, int n) {

        int[] arr = new int[n+1];

        arr[1] = stairs[1];
        if(n > 1) arr[2] = stairs[1] + stairs[2];
        if(n > 2) arr[3] = Math.max(stairs[1], stairs[2]) + stairs[3];

        if(n > 3)
            for(int i=4; i<=n; i++)
                arr[i] = Math.max( arr[i-3] + stairs[i-1], arr[i-2] ) + stairs[i];

        return arr[n];
    }
}
