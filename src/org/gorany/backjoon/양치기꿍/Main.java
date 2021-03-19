package org.gorany.backjoon.양치기꿍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int r, c, wolves, sheeps, wolf, sheep;
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static char[][] map = new char[251][251];
    static boolean[][] visit = new boolean[251][251];

    static void DFS(int y, int x){

        visit[y][x] = true;
        if(map[y][x] == 'k') sheep++;
        else if(map[y][x] == 'v') wolf++;

        for(int a=0; a<4; a++){
            int ny = y+Y[a];
            int nx = x+X[a];

            if(ny < 1 || nx < 1 || ny > r || nx > c || visit[ny][nx] || map[ny][nx] == '#') continue;

            DFS(ny, nx);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for(int i=1; i<=r; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=c; j++) {
                map[i][j] = str[j - 1];

                if(map[i][j] == 'k') sheeps++;
                else if(map[i][j] == 'v') wolves++;
            }
        }

        for(int i=1; i<=r; i++)
            for(int j=1; j<=c; j++)
                if(map[i][j] != '#' && !visit[i][j]) {
                    wolf = 0;
                    sheep = 0;
                    DFS(i, j);

                    if (wolf >= sheep) sheeps -= sheep;
                    else wolves -= wolf;
                }
        System.out.print(sheeps + " " + wolves);
    }
}