package org.gorany.backjoon.램프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[][] map = new char[51][51];

    static boolean isEqual(char[] arr1, char[] arr2, int m){

        for(int i=1; i<=m; i++)
            if(arr1[i] != arr2[i])
                return false;

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            char[] str = br.readLine().toCharArray();

            for(int j=1; j<=m; j++)
                map[i][j] = str[j-1];
        }

        int k = Integer.parseInt(br.readLine());
        int max = 0;

        /* 모든 행(Row)을 돌며 '0'의 개수 count */
        for(int i=1; i<=n; i++){

            int zero = 0;

            for(int j=1; j<=m; j++)
                if(map[i][j] == '0') zero++;

            int onRow = 0; //불이 켜진 행의 개수
            /* 0의 개수가 k이하 and 0의 개수, k가 모두 짝수? 홀수? */
            if(zero <= k && zero % 2 == k % 2){

                /* 현재 행(i)과 같은 행(같은 패턴)이 있으면 onRow 증가 */
                for(int j=1; j<=n; j++)
                    if(isEqual(map[i], map[j], m))
                        onRow++;

                max = Math.max(max, onRow);
            }

        }
        System.out.println(max);
    }
}