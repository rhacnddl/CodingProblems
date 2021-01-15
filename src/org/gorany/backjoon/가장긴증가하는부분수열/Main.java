package org.gorany.backjoon.가장긴증가하는부분수열;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] arr = new int[1001];

    static int getAnswer(int x){

        int[] result = new int[x+1];
        int[] X = new int[x+1];
        int maxIdx = 1;

        result[1] = 1;
        X[1] = arr[1];

        for(int i=2; i<=x; i++)
            for(int j=1; X[j] != 0; j++)
                //case 1: arr[i] == X[j]
                if(arr[i] == X[j]){
                    result[i] = j;
                    break;
                }
                //case 2: arr[i] < X[j]
                else if(arr[i] < X[j]){
                    result[i] = j;
                    X[j] = arr[i];
                    break;
                }
                //case 3: arr[i] > X[j]
                else{
                    result[i] = j + 1;
                    if(X[j+1] == 0) {
                        X[j + 1] = arr[i];
                        maxIdx = j + 1;
                    }
                }

        Arrays.stream(result).forEach(a -> {
            if(a != 0)
                System.out.println(a);
        });
        System.out.println("==================");
        Arrays.stream(X).forEach(a -> {
            if(a != 0)
                System.out.println(a);
        });

        return maxIdx;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        if(x < 1 || x > 1000) System.exit(0);
        for(int i=1; i<=x; i++){
            arr[i] = sc.nextInt();
            if(arr[i] < 1 || arr[i] > 1000) System.exit(0);
        }

        System.out.printf("%d\n", getAnswer(x));
        sc.close();
    }
}
