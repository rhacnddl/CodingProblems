package org.gorany.backjoon.보물섬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x, d;
        public Point(int yy, int xx, int dist){
            y=yy;
            x=xx;
            d = dist;
        }
    }
    static char[][] map = new char[51][51];
    static boolean[][] visit;

    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};

    static int n, m, max = 0;

    static void BFS(){

        Queue<Point> Q = new LinkedList<>();

        for(int i=1; i<=n; i++)
            for(int j=1; j<=m; j++){
                visit = new boolean[51][51];

                if(map[i][j] == 'L'){
                    visit[i][j] = true;
                    Q.add(new Point(i, j, 0));
                }

                while(!Q.isEmpty()){
                    Point p_ = Q.poll();
                    int y = p_.y;
                    int x = p_.x;
                    int d = p_.d;

                    for(int a=0; a<4; a++) {
                        int ny = y + Y[a];
                        int nx = x + X[a];
                        if (ny < 1 || nx < 1 || ny > n || nx > m || visit[ny][nx] || map[ny][nx] == 'W') continue;
                        visit[ny][nx] = true;

                        max = Math.max(d+1, max);
                        //System.out.println("다음 정점은 ("+ny+", "+nx+")이며, distance는 "+(d + 1)+"이다.");
                        Q.add(new Point(ny, nx, d + 1));
                    }
                }
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

        BFS();
        System.out.println(max);
    }
}