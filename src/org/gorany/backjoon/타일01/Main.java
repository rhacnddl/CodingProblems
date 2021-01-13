package org.gorany.backjoon.타일01;

import java.util.Scanner;

public class Main {

    static long[] arr = new long[1000002];

    static long tile(int n){

        if(arr[n] != 0) return arr[n];

        for(int i=3; i<=n; i++)
            arr[i] = (arr[i-1] + arr[i-2]) % 15746;

        return arr[n];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        if(n > 1000000 || n < 1) System.exit(0);

        arr[1] = 1; arr[2] = 2;

        long cnt = tile(n);
        System.out.printf("%d\n", cnt);
    }
}
