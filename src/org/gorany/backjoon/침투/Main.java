package org.gorany.backjoon.침투;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[][] map = new char[1001][1001];
    static boolean[][] visit = new boolean[1001][1001];
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static int r, c;

    static boolean DFS(int y, int x){

        if(y == r)
            return true;

        visit[y][x] = true;
        boolean result = false;

        for(int a=0; a<4; a++) {
            int ny = y+Y[a];
            int nx = x+X[a];

            if(ny < 1 || nx < 1 || ny > r || nx > c || visit[ny][nx] || map[ny][nx] == '1') continue;

            result |= DFS(ny, nx);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for(int i=1; i<=r; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=c; j++)
                map[i][j] = str[j-1];
        }

        boolean result = false;
        for(int i=1; i<=c; i++)
            if(!visit[1][i] && map[1][i] == '0')
                result |= DFS(1, i);

        System.out.println(result?"YES":"NO");
    }
}