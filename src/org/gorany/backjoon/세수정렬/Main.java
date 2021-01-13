package org.gorany.backjoon.세수정렬;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static void sort(int[] a){

        int tmp = 0;

        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++) {
                if(a[j] > a[j+1]) {
                    tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                }
            }
        }
        Arrays.stream(a).forEach(n -> System.out.printf("%d ", n));
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] a = new int[3];

        a[0] = sc.nextInt();
        a[1] = sc.nextInt();
        a[2] = sc.nextInt();

        if(a[0] == a[1] || a[1] == a[2] || a[1] == a[2]) System.exit(0);
        if(a[0] < 0 || a[1] < 0 || a[2] < 0) System.exit(0);
        if(a[0] > 1000000 || a[1] > 1000000 || a[2] > 1000000) System.exit(0);

        sort(a);
    }
}
