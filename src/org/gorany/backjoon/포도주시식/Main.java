package org.gorany.backjoon.포도주시식;

import java.util.Arrays;
import java.util.OptionalLong;
import java.util.Scanner;

public class Main {

    static int[] wine = new int[10001];

    static long getAnswer(int x){

        long[] arr = new long[10001];
        long max = 0;

        arr[1] = wine[1];
        arr[2] = wine[1] + wine[2];
        arr[3] = Math.max(wine[1], wine[2]) + wine[3];

        for(int i=4; i<=x; i++){
            arr[i] = Math.max(arr[i-4], arr[i-3]) + wine[i] + wine[i-1];
            arr[i] = Math.max(arr[i-2] + wine[i], arr[i]);
        }


        Arrays.stream(arr).forEach(a -> {
            if(a != 0)
                System.out.println(a);
        });

        OptionalLong a = Arrays.stream(arr).max();
        if(!a.isPresent()) return 0;
        max = a.getAsLong();
        return max;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        if(x < 1 || x > 10000) System.exit(0);

        for(int i=1; i<=x; i++){
            wine[i] = sc.nextInt();
            if(wine[i] < 0 || wine[i] > 1000) System.exit(0);
        }

        System.out.printf("%d\n", getAnswer(x));
        sc.close();
    }
}
