package org.gorany.backjoon.일로만들기;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int getMinCnt(int x) {

        int[] arr = new int[x+1];
        int temp;

        arr[1] = 0;
        if(x >= 2) arr[2] = 1;
        if(x >= 3) arr[3] = 1;
        if(x >= 4)
        for(int i=4; i<=x; i++) {
            temp = 999999;

            if(i % 3 == 0){
                temp = Math.min(arr[i/3] + 1, temp);
                System.out.printf("case 1: arr[%d] = %d\n", i, temp);
            }
            if(i % 2 == 0){
                temp = Math.min(arr[i/2] + 1, temp);
                System.out.printf("case 2: arr[%d] = %d\n", i, temp);
            }
            temp = Math.min(arr[i-1] + 1, temp);
            System.out.printf("case 3: arr[%d] = %d\n", i, temp);

            arr[i] = temp;
        }

        return arr[x];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        if(x < 1 || x > 1000000) System.exit(0);

        System.out.printf("%d\n", getMinCnt(x));
    }
}
