package org.gorany.backjoon.전쟁전투;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m, white = 0, black = 0;
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static boolean[][] visit = new boolean[101][101], map = new boolean[101][101];

    static int DFS(int y, int x, boolean isWhite){

        int cnt = 1;
        visit[y][x] = true;

        for(int a=0; a<4; a++){
            int ny = y+Y[a];
            int nx = x+X[a];

            if(ny < 1 || nx < 1 || ny > n || nx > m || visit[ny][nx] || map[ny][nx] != isWhite) continue;

            cnt += DFS(ny, nx, isWhite);
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            char[] str = br.readLine().toCharArray();

            for(int j=1; j<=m; j++)
                map[i][j] = str[j-1] == 'W';
        }

        for(int i=1; i<=n; i++)
            for(int j=1; j<=m; j++)
                if(!visit[i][j]){
                    if(map[i][j])
                        white += Math.pow(DFS(i, j, map[i][j]), 2);
                    else
                        black += Math.pow(DFS(i, j, map[i][j]), 2);
                }

        System.out.println(white + " " + black);
    }
}