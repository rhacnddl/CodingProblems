package org.gorany.backjoon.파도반수열;

import java.util.Scanner;

public class Main {

    static long[] length = new long[101];

    static long triangle(int n){

        if(length[n] != 0) return length[n];

        for(int i=6; i<= n; i++)
            length[i] = length[i-1] + length[i-5];

        return length[n];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        length[1] = length[2] = length[3] = 1;
        length[4] = length[5] = 2;

        int n, t = sc.nextInt();

        for(int i=0; i<t; i++){
            n = sc.nextInt();

            if( n < 1 || n > 100) System.exit(0);

            System.out.printf("%d\n", triangle(n));
        }
    }
}
