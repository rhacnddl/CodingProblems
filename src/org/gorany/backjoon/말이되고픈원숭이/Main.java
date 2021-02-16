package org.gorany.backjoon.말이되고픈원숭이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point implements Comparable<Point>{
        int y, x, dist, cnt;
        public Point(int yy, int xx, int dist, int cnt){
            y=yy; x=xx;
            this.dist = dist;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(Point o) {
            return dist - o.dist;
        }
    }
    static final int INF = 1000000000;
    static int[][][] dp = new int[201][201][31];
    static int[][] map = new int[201][201];
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1}, Y_ = {2, 1, -1, -2, -2, -1, 1, 2}, X_ = {1, 2, 2, 1, -1, -2, -2, -1};
    static int k, r, c;

    /*static void Dijkstra(){

        Queue<Point> Q = new PriorityQueue<>();

        dp[1][1][0] = 0;
        Q.add(new Point(1, 1, 0,0));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;
            int dist = p.dist;
            int cnt = p.cnt;

            if(dp[y][x][cnt] < dist) continue;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                if(nx < 1 || ny < 1 || nx > c || ny > r || map[ny][nx] == 1) continue;

                if(dp[ny][nx][cnt] > dist + 1){
                    dp[ny][nx][cnt] = dist + 1;
                    Q.add(new Point(ny, nx, dist + 1, cnt));
                }
            }
            if(cnt < k)
                for(int a=0; a<8; a++){
                    int ny = y+Y_[a];
                    int nx = x+X_[a];

                    if(nx < 1 || ny < 1 || nx > c || ny > r || map[ny][nx] == 1) continue;

                    if(dp[ny][nx][cnt + 1] > dist + 1){
                        dp[ny][nx][cnt + 1] = dist + 1;
                        Q.add(new Point(ny, nx, dist + 1, cnt + 1));
                    }
                }
        }
    }*/
    static void BFS(){

        Queue<Point> Q = new LinkedList<>();

        dp[1][1][0] = 0;
        Q.add(new Point(1, 1, 0,0));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;
            int dist = p.dist;
            int cnt = p.cnt;

            if(dp[y][x][cnt] < dist) continue;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                if(nx < 1 || ny < 1 || nx > c || ny > r || map[ny][nx] == 1) continue;

                if(dp[ny][nx][cnt] > dist + 1){
                    dp[ny][nx][cnt] = dist + 1;
                    Q.add(new Point(ny, nx, dist + 1, cnt));
                }
            }
            if(cnt < k)
                for(int a=0; a<8; a++){
                    int ny = y+Y_[a];
                    int nx = x+X_[a];

                    if(nx < 1 || ny < 1 || nx > c || ny > r || map[ny][nx] == 1) continue;

                    if(dp[ny][nx][cnt + 1] > dist + 1){
                        dp[ny][nx][cnt + 1] = dist + 1;
                        Q.add(new Point(ny, nx, dist + 1, cnt + 1));
                    }
                }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        for(int i=1; i<=r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i][j], INF);
            }
        }
        BFS();
        //Dijkstra();
        int min = INF;

        for(int i=0; i<=k; i++)
            if(dp[r][c][i] < min) min = dp[r][c][i];

        /*for(int a=0; a<=k; a++) {
            for (int i = 1; i <= r; i++) {
                for (int j = 1; j <= c; j++)
                    System.out.print(dp[i][j][a] + " ");
                System.out.println();
            }
            System.out.println("-----------------------");
        }*/

        System.out.println(min == INF? -1 : min);
    }
}