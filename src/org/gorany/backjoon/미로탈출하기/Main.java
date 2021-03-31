package org.gorany.backjoon.미로탈출하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static char[][] map = new char[501][501];
    static int[][] dp = new int[501][501];
    static int[] Y = {-1, 0, 1, 0}, X = {0, 1, 0, -1};

    static boolean isEscape(int y, int x){

        return (y < 1 || x < 1 || y > n || x > m);
    }

    static int DFS(int y, int x){

        if(!isEscape(y, x) && dp[y][x] != -1)
            return dp[y][x];

        if(isEscape(y, x))
            return 1;

        dp[y][x] = 0;
        int ny = -1, nx = -1;

        if(map[y][x] == 'U')      {ny = y+Y[0]; nx = x+X[0];}
        else if(map[y][x] == 'R') {ny = y+Y[1]; nx = x+X[1];}
        else if(map[y][x] == 'D') {ny = y+Y[2]; nx = x+X[2];}
        else if(map[y][x] == 'L') {ny = y+Y[3]; nx = x+X[3];}

        dp[y][x] += DFS(ny, nx);

        return dp[y][x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=m; j++) {
                map[i][j] = str[j - 1];
                dp[i][j] = -1;
            }
        }
        for(int i=1; i<=n; i++)
            for(int j=1; j<=m; j++)
                if(dp[i][j] == -1)
                    DFS(i, j);

        int result = 0;
        for(int i=1; i<=n; i++)
            for(int j=1; j<=m; j++)
                result += dp[i][j];

        System.out.println(result);

        /*for(int i=1; i<=n; i++) {
            for (int j = 1; j <= m; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }*/
    }
}