package org.gorany.backjoon.내리막길;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int[][] dp;
    static int[] Y = {1,-1,0,0};
    static int[] X = {0,0,1,-1};
    static int m, n;

    static int DFS(int y, int x){

        if(y == m-1 && x == n-1)
            return 1;

        if(dp[y][x] != -1)
            return dp[y][x];

        dp[y][x] = 0;

        for(int a=0; a<4; a++){
            int nY = y+Y[a];
            int nX = x+X[a];

            if((nY >= 0 && nY < m) && (nX >= 0 && nX < n) && map[nY][nX] < map[y][x]) {
                dp[y][x] += DFS(nY, nX);
            }
        }
        return dp[y][x];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        dp = new int[m][n];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(DFS(0, 0));

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
    }
}
/*
5 5
50 30 50 10 40
40 20 25 20 30
35 30 28 25 20
15 10 5 4 3
5 4 3 2 1
 */