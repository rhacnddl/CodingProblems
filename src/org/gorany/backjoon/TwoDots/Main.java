package org.gorany.backjoon.TwoDots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static char[][] map = new char[51][51];
    static boolean[][] visit = new boolean[51][51];
    static boolean cycle;

    static void DFS(int y, int x, int bY, int bX, int cnt){

        if(visit[y][x] && cnt >= 4){
            //System.out.println("(" + y+", " + x +") 에서 사이클 발생");
            cycle = true;
            return;
        }

        visit[y][x] = true;
        char cur = map[y][x];

        for(int a=0; a<4; a++){
            int ny = y+Y[a];
            int nx = x+X[a];

            if(ny < 1 || nx < 1 || ny > n || nx > m || map[ny][nx] != cur) continue;
            if(ny == bY && nx == bX) continue;

            DFS(ny, nx, y, x, cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=m; j++)
                map[i][j] = str[j-1];
        }

        for(int i=1; i<=n; i++)
            for(int j=1; j<=m; j++){
                if(!visit[i][j])
                    DFS(i, j, 0, 0, 1);
            }
        System.out.println(cycle? "Yes":"No");
    }
}