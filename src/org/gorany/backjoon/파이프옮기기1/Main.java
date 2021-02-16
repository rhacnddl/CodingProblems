package org.gorany.backjoon.파이프옮기기1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] map = new int[17][17], dp[] = new int[17][17][3];
    static int[] Y = {0, 1, 1}, X = {1, 1, 0};

    static int DFS(int y, int x, int type){

        if(y == n && x == n)
            return 1;

        if(dp[y][x][type] != -1) return dp[y][x][type];

        dp[y][x][type] = 0;

        int v1, v2;

        if(type == 0) {
            v1 = 0;
            v2 = 1;
        }
        else if(type == 1){
            v1 = 0;
            v2 = 2;
        }
        else{
            v1 = 1;
            v2 = 2;
        }

        for (int a = v1; a <= v2; a++) {
            int ny = y + Y[a];
            int nx = x + X[a];

            if(ny > n || nx > n || map[ny][nx] == 1) continue;
            if(a == 1 && (map[ny-1][nx] == 1 || map[ny][nx-1] == 1)) continue;

            dp[y][x][type] += DFS(ny, nx, a);
        }

        return dp[y][x][type];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(DFS(1, 2, 0));
    }
}
