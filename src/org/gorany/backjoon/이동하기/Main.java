package org.gorany.backjoon.이동하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int N = 1001;
    static int[][] map = new int[N][N], dp = new int[N][N];

    static int MAX(int a, int b, int c){
        int tmp = Math.max(a, b);
        return Math.max(c, tmp);
    }
    static void Dynamic(int r, int c){

        for(int i=1; i<=r; i++)
            for(int j=1; j<=c; j++)
                dp[i][j] = MAX(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + map[i][j];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        for(int i=1; i<=r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=c; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        Dynamic(r, c);
        System.out.println(dp[r][c]);
    }
}
