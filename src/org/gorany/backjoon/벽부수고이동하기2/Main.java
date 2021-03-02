package org.gorany.backjoon.벽부수고이동하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x, crush;
        public Point(int yy, int xx, int cc){
            y=yy; x=xx; crush=cc;
        }
    }
    static int[][][] dist = new int[1001][1001][11];
    static char[][] map = new char[1001][1001];
    static boolean[][][] visit = new boolean[1001][1001][11];
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static int min = 1000000000;

    static void BFS(int r, int c, int k){

        Queue<Point> Q = new LinkedList<>();

        visit[1][1][0] = true;
        dist[1][1][0] = 1;
        Q.add(new Point(1, 1, 0));

        while(!Q.isEmpty()){
            Point p = Q.poll();

            int crush = p.crush;
            int d = dist[p.y][p.x][crush];
            if(p.y == r && p.x == c) {
                min = dist[p.y][p.x][crush];
                break;
            }

            for(int a=0; a<4; a++){
                int ny = p.y+Y[a];
                int nx = p.x+X[a];
                int nd = d + 1;

                if(ny < 1 || nx < 1 || ny > r || nx > c || visit[ny][nx][crush]) continue;

                if(map[ny][nx] == '0') {
                    visit[ny][nx][crush] = true;
                    dist[ny][nx][crush] = nd;
                    Q.add(new Point(ny, nx, crush));
                }
                else if(map[ny][nx] == '1' && crush < k && !visit[ny][nx][crush + 1]){
                    //visit[p.y][p.x][crush + 1] = true;
                    visit[ny][nx][crush + 1] = true;
                    dist[ny][nx][crush + 1] = nd;
                    Q.add(new Point(ny, nx, crush + 1));
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=m; j++)
                map[i][j] = str[j-1];
        }

        BFS(n, m, k);

        System.out.println(min == 1000000000? -1:min);
    }
}