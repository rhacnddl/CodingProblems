package org.gorany.backjoon.RGB거리;

import java.util.Scanner;

public class Main {

    static final int R = 0;
    static final int G = 1;
    static final int B = 2;
    static final int MAX = 1001;
    static int[][] RGB = new int[MAX][3];

    static boolean isPossible(int i){

        boolean flag = true;
        if(RGB[i][R] < 1 || RGB[i][R] > 1000) flag = false;
        if(RGB[i][G] < 1 || RGB[i][G] > 1000) flag = false;
        if(RGB[i][B] < 1 || RGB[i][B] > 1000) flag = false;

        return flag;
    }

    static int getMin(int n) {

        int min;
        int[][] arr = new int[n+1][3];

        arr[1][R] = RGB[1][R]; arr[1][G] = RGB[1][G]; arr[1][B] = RGB[1][B];

        for(int i=2; i<=n; i++)
            for(int j=0; j<3; j++) //Color Iterator
                arr[i][j] = Math.min(RGB[i][j] + arr[i-1][(j+1)%3], RGB[i][j] + arr[i-1][(j+2)%3]);

        min = Math.min(arr[n][R], arr[n][G]);
        min = Math.min(min, arr[n][B]);

        return min;
    }

    /*
    ==> greedy
    static int first(int color, int n){

        int[][] arr = new int[n+1][2]; //arr[][sum, color] color : Red(0) Green(1) Blue(2)

        arr[1][0] = RGB[1][color]; arr[1][1] = color;

        for(int i=2; i<=n; i++) {
            arr[i][0] = 999999999;

            if(RGB[i][ (arr[i-1][1]+1) % 3 ] < RGB[i][ (arr[i-1][1]+2) % 3 ]){
                arr[i][0] = arr[i-1][0] + RGB[i][ (arr[i-1][1]+1)%3 ];
                arr[i][1] = (arr[i-1][1] + 1) % 3;
            }
            else{
                arr[i][0] = arr[i-1][0] + RGB[i][ (arr[i-1][1]+2)%3 ];
                arr[i][1] = (arr[i-1][1] + 2) % 3;
            }

            for(int j=0; j<3; j++){ //Color를 순회
                if(arr[i-1][1] == j) continue;

                if((arr[i-1][0] + RGB[i][j]) < arr[i][0]){ //이전 sum값 + i번 째 집의 R G B 가격 < i번째 까지의 sum?
                    arr[i][0] = arr[i-1][0] + RGB[i][j];
                    arr[i][1] = j;
                }
            }
        }

        return arr[n][0];
    }*/

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        if(n < 2 || n > 1000) System.exit(0);

        for(int i=1; i<=n; i++){
            RGB[i][0] = sc.nextInt();
            RGB[i][1] = sc.nextInt();
            RGB[i][2] = sc.nextInt();
            if(!isPossible(i)) System.exit(0);
        }
        System.out.printf("%d\n", getMin(n));
    }
}
