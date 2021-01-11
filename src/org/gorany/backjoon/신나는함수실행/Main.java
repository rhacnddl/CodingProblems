package org.gorany.backjoon.신나는함수실행;

import java.util.Scanner;

public class Main {

    static int[][][] arr = new int[21][21][21];

    static int w(int a, int b, int c){

        if(a <= 0 || b <= 0 || c <= 0) //abc중 하나라도 0 이하? return 1;
            return 1;
        if(a > 20 || b > 20 || c > 20) //abc중 하나라도 21 이상? abc 모두 20
            a = b = c = 20;

        if(arr[a][b][c] != 0) return arr[a][b][c];

        if(a < b && b < c)
            return arr[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
        else
            return arr[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int a, b, c;

        while(true) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();

            if(a == -1 && b == -1 && c == -1) break;

            System.out.printf("w(%d, %d, %d) = %d\n", a, b, c, w(a, b, c));
        }
        sc.close();
    }
}
