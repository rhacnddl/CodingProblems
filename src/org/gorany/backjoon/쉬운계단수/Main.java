package org.gorany.backjoon.쉬운계단수;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
//https://www.acmicpc.net/problem/10844
    /*
        열 : 0, 1, 2, 3, 4, 5, 6, 7, 8, 9의 개수
        행 : 길이 n즉 1이면 1~9, 2이면 10~99, 3이면 100~999, 4이면 1000~9999
        {
        {},
        ...,
        {}
        }
     */
    private final static long MOD = 1000000000;

    static long getAnswer(int x) {

        int[][] arr = new int[x+1][10];

        for(int i=1; i<=9; i++) arr[1][i] = 1;

        for(int i=2; i<=x; i++)
            for(int j=0; j<=9; j++)
                if(j == 0)
                    arr[i][j+1] += arr[i-1][j] % MOD;

                else if(j == 9)
                    arr[i][j-1] += arr[i-1][j] % MOD;

                else{
                    arr[i][j-1] += arr[i-1][j] % MOD;
                    arr[i][j+1] += arr[i-1][j] % MOD;
                }

        Arrays.stream(arr).forEach(a -> System.out.println(Arrays.toString(a)));

        return IntStream.rangeClosed(0, 9).mapToLong(i -> arr[x][i]).sum() % MOD;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        if(x < 1 || x > 100) System.exit(0);

        System.out.printf("%d\n", getAnswer(x));
        sc.close();
    }
}
