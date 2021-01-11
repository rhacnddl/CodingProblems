package org.gorany.backjoon.피보나치함수;

import java.util.Scanner;

/*
https://www.acmicpc.net/problem/1003

 */
public class Main {

    static int max = 2;
    static long[][] arr = new long[41][2];

    static void fibo(int n){
        arr[0][0] = 1; arr[0][1] = 0;
        arr[1][0] = 0; arr[1][1] = 1;

        if(n > max) {
            for (int i = max; i <= n; i++) {
                arr[i][0] = arr[i - 1][0] + arr[i - 2][0];
                arr[i][1] = arr[i - 1][1] + arr[i - 2][1];
            }
            max = n;
        }
        System.out.printf("%d %d\n", arr[n][0], arr[n][1]);
    }
    public static void main(String[] args) {
        /*Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int[] n = new int[T];

        for(int i=0; i<T; i++)
            n[i] = sc.nextInt();

        long start = System.currentTimeMillis();

        for(int i=0; i<n.length; i++)
            fibo(n[i]);

        long end = System.currentTimeMillis();
        System.out.println( "실행 시간 : " + ( end - start )/1000.0 +"초");*/

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        long start = System.currentTimeMillis();
        for(int i=0; i<T; i++)
            fibo(sc.nextInt());

        long end = System.currentTimeMillis();
        System.out.println( "실행 시간 : " + ( end - start )/1000.0 +"초");
    }
}
