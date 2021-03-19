package org.gorany.backjoon.현수막;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int r, c;
    static int[][] map = new int[251][251];
    static int[] Y = {-1,1,0,0,-1,-1,1,1}, X={0,0,-1,1,-1,1,-1,1};
    static boolean[][] visit = new boolean[251][251];

    static void DFS(int y, int x){

        visit[y][x] = true;

        for(int a=0; a<8; a++){
            int ny = y+Y[a];
            int nx = x+X[a];

            if(ny < 1 || nx < 1 || ny > r || nx > c || visit[ny][nx] || map[ny][nx] == 0) continue;

            DFS(ny, nx);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for(int i=1; i<=r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=c; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        for(int i=1; i<=r; i++)
            for(int j=1; j<=c; j++)
                if(!visit[i][j] && map[i][j] == 1){
                    cnt++;
                    DFS(i, j);
                }
        System.out.println(cnt);
    }
}