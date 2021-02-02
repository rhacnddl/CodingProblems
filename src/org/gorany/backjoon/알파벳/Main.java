package org.gorany.backjoon.알파벳;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int[] X = {1, -1, 0, 0};
    static int[] Y = {0, 0, -1, 1};
    static String[][] map = new String[21][21];
    static boolean[][] visit = new boolean[21][21];
    static int r, c, max = 1, cnt=1;
    static Set<String> str = new HashSet<>();

    static void DFS(int y, int x){

        visit[y][x] = true;
        str.add(map[y][x]);
        cnt++;

        for(int a=0; a<4; a++){
            int ny = y+Y[a];
            int nx = x+X[a];

            if((ny>=0 && ny <r) && (nx>=0 && nx<c) && !str.contains(map[ny][nx]) && !visit[ny][nx]) {
                max = Math.max(max, cnt);
                DFS(ny, nx);
            }
        }
        cnt--;
        str.remove(map[y][x]);
        visit[y][x] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for(int i=0; i<r; i++) {
            String[] str = br.readLine().split("");
            for (int j=0; j<c; j++)
                map[i][j] = str[j];
        }

        DFS(0, 0);
        System.out.println(max);
    }
}