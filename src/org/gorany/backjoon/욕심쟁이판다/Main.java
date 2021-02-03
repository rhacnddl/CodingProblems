package org.gorany.backjoon.욕심쟁이판다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int N = 501;
    static int[][] map = new int[N][N], dp = new int[N][N];
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static int n, max = 1;

    static int DFS(int y, int x){

        if(dp[y][x] != -1)
            return dp[y][x];

        dp[y][x] = 1;

        for(int a=0; a<4; a++){
            int ny = y+Y[a];
            int nx = x+X[a];

            if((ny>0 && ny<=n) && (nx>0 && nx<=n) && map[ny][nx] > map[y][x]) {
                int tmp = 1;

                tmp += DFS(ny, nx);
                dp[y][x] = Math.max(tmp, dp[y][x]);
                max = Math.max(max, dp[y][x]);
            }
        }
        return dp[y][x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++)
                if(dp[i][j] == -1)
                    DFS(i, j);

        for(int i=1; i<=n; i++) {
            for (int j = 1; j <= n; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
        System.out.println(max);
    }
}
