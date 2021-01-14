package org.gorany.backjoon.정수삼각형;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class Main {

    static int getMax(int[] array){

        int max = 0;

        OptionalInt result = Arrays.stream(array).max();
        if(result.isPresent())
            max = result.getAsInt();

        return max;
    }

    static int triangle(int[][] arr, int n){

        int[][] result = new int[n+1][n+1];

        result[1][1] = arr[1][1];

        for(int i=2; i<=n; i++)
            for(int j=1; j<=i; j++) //양 끝쪽 index( << 1번 인덱스를 받고, >> j-1인덱스를 받는다) 나머지는 (j, j-1)
                if(j == 1)
                    result[i][j] = arr[i][j] + result[i-1][1];
                else if(j == i)
                    result[i][j] = arr[i][j] + result[i-1][j-1];
                else
                    result[i][j] = Math.max(arr[i][j] + result[i-1][j-1], arr[i][j] + result[i-1][j]);


        Arrays.stream(result).forEach(array -> System.out.println(Arrays.toString(array)));
        System.out.println("====");

        return getMax(result[n]);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        if(n < 1 || n > 500) System.exit(0);

        int[][] arr = new int[n+1][n+1];

        for(int i=1; i<= n; i++)
            for(int j=1; j<=i; j++){
                arr[i][j] = sc.nextInt();
                if(arr[i][j] >= 10000 || arr[i][j] < 0) System.exit(0);
            }

        Arrays.stream(arr).forEach(array -> System.out.println(Arrays.toString(array)));

        System.out.printf("%d\n", triangle(arr, n));
    }
}
