package org.gorany.backjoon.가장긴바이토닉부분수열;

import java.util.Scanner;
/*
17
7 2 1 1 5 2 2 3 2 3 1 2 4 5 1 2 4 = 6

6
1 2 3 5 5 1 = 5

6
1 2 3 3 2 1 = 5

5
1 3 1 2 1 = 4

5
5 4 3 2 1 = 5

10
10 1 3 5 7 6 3 2 1 10 = 8

7
9 1 2 3 2 1 9 = 5

10
1 6 2 1 4 3 4 5 2 1 = 7

5
1 2 1 1 1 = 3

2
999 1000 = 2

10
1 2 2 2 2 2 2 2 2 2 = 2

7
1 4 3 2 1 5 6 = 5

7
1 2 4 3 2 4 1 = 6

5
1 5 4 2 3 = 4

6
3 2 1 4 5 6 = 4

7
5 1 6 2 6 2 1 = 5

9
5 1 6 2 7 3 7 2 1 = 6

10
9 8 6 2 3 5 4 10 1 7 = 6

11
1 2 3 4 5 1 5 4 3 2 1 = 9

5
5 4 3 2 3 = 4
 */
public class Main {

    static int[] arr = new int[1001];

    static int getAnswer(int x){

        int[][] result = new int[2][x+1];
        int[][] X = new int[2][x+1];

        int max = 1, i, j, z;

        X[0][1] = arr[1]; X[1][1] = arr[x];
        result[0][1] = 1;
        result[1][x] = 1;
        if(x > 1)
            for(i=2, j=x-1; i<=x; i++, j--){
                //왼쪽 -> 오른쪽 LIS
                for(z=1; X[0][z] != 0; z++)
                    if(arr[i] == X[0][z]){
                        result[0][i] = z;
                        break;
                    }
                    else if(arr[i] < X[0][z]){
                        result[0][i] = z;
                        X[0][z] = arr[i];
                        break;
                    }
                    else{
                        result[0][i] = z + 1;
                        if(X[0][z+1] == 0){
                            X[0][z+1] = arr[i];
                        }
                    }
                //오른쪽 -> 왼쪽 LIS
                for(z=1; X[1][z] != 0; z++)
                    if(arr[j] == X[1][z]){
                        result[1][j] = z;
                        break;
                    }
                    else if(arr[j] < X[1][z]){
                        result[1][j] = z;
                        X[1][z] = arr[j];
                        break;
                    }
                    else{
                        result[1][j] = z + 1;
                        if(X[1][z+1] == 0){
                            X[1][z+1] = arr[j];
                        }
                    }
            }
        //DP_1[i] + DP_2[i] - 1의 최대값 구하기
        for(i=1; i<=x; i++)
            max = Math.max(max, result[0][i] + result[1][i] - 1);
/*
        for(i=1; i<=x; i++)
            System.out.print(result[0][i] + " ");
        System.out.println();
        for(i=1; i<=x; i++)
            System.out.print(result[1][i] + " ");
        System.out.println("\n=======================================================================");
        for(i=1; i<=x; i++)
            System.out.print(result[0][i] + result[1][i] + - 1 + " ");
        System.out.println();
*/
        return max;
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
