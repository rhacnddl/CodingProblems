package org.gorany.backjoon.전깃줄;

import java.util.Arrays;
import java.util.Scanner;
/*
6
1 8
2 9
4 4
5 2
6 1
7 5 = 4
 */
public class Main {

    static final int N = 501;
    static int[] A = new int[N];
    static int[] B = new int[N];

    static int getLIS(int[] arr){

        int tmpIdx = 0, i, j;
        int[] X = new int[N];
        int[] result = new int[N];

        for(i=1; i<N; i++)
            if(arr[i] != 0){
                X[1] = arr[i];
                result[i] = 1;
                tmpIdx = i;
                break;
            }

        for(i=tmpIdx+1; i<N; i++){
            if(arr[i] == 0) continue;
            for(j=1; X[j] != 0; j++){

                if(arr[i] > X[j]){
                    result[i] = j + 1;
                    if(X[j+1] == 0) {
                        X[j + 1] = arr[i];
                        break;
                    }
                }
                else{
                    result[i] = j;
                    X[j] = arr[i];
                    break;
                }
            }
        }
        return Arrays.stream(result).max().getAsInt();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt(), idx, value;
        if(x < 1 || x > 100) System.exit(0);

        for(int i=1; i<=x; i++){
            idx = sc.nextInt();
            value = sc.nextInt();

            if(idx < 1 || value < 1) System.exit(0);
            if(idx >= N || value >= N) System.exit(0);
            if(A[idx] != 0) System.exit(0);

            A[idx] = value;
            B[value] = idx;
        }
        System.out.printf("%d\n", x - Math.max(getLIS(A), getLIS(B)));

        sc.close();
    }
}
