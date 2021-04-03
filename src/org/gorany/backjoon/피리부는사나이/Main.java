package org.gorany.backjoon.피리부는사나이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int r, c, cnt=0;
    static int[][] map = new int[1001][1001], check = new int[1001][1001];
    static boolean[][] visit = new boolean[1001][1001];
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};

    /*사이클 발견 : cnt++, 사이클이 아닌 벽으로 가로막힌곳, cnt++ */
    /* -1: 미방문 */
    static int DFS(int y, int x){

        if(check[y][x] != -1) return check[y][x];

        if(y < 1 || x < 1 || y > r || x > c || visit[y][x])
            return cnt++;

        visit[y][x] = true;

        int dir = map[y][x];

        int ny = y+Y[dir];
        int nx = x+X[dir];

        check[y][x] = DFS(ny, nx);

        return check[y][x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for(int i=1; i<=r; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=c; j++){
                char c = str[j-1];

                if(c == 'U') map[i][j] = 0;
                else if(c == 'D') map[i][j] = 1;
                else if(c == 'L') map[i][j] = 2;
                else map[i][j] = 3;

                check[i][j] = -1;
            }
        }

        for(int i=1; i<=r; i++)
            for(int j=1; j<=c; j++)
                if(!visit[i][j])
                    DFS(i, j);

        System.out.println(cnt);

        for(int i=1; i<=r; i++) {
            for (int j = 1; j <= c; j++)
                System.out.print(check[i][j] + " ");
            System.out.println();
        }
        for(int i=1; i<=r; i++) {
            for (int j = 1; j <= c; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
    }
}