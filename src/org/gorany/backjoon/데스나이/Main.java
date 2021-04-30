package org.gorany.backjoon.데스나이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x, m;
        public Point(int yy, int xx, int move){
            y=yy;
            x=xx;
            m=move;
        }
    }
    static int n;
    static int[] Y = {-2, -2, 0, 0, 2, 2}, X = {-1, 1, -2, 2, -1, 1};
    static boolean[][] visit = new boolean[200][200];

    static int BFS(int[] arr){
        Queue<Point> Q = new LinkedList<>();

        visit[arr[0]][arr[1]] = true;
        Q.add(new Point(arr[0], arr[1], 0));

        while(!Q.isEmpty()){
            Point p = Q.poll();

            int y = p.y;
            int x = p.x;
            int m = p.m;

            if(y == arr[2] && x == arr[3]) return m;

            for(int a=0; a<6; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                if(ny < 0 || nx < 0 || ny > n-1 || nx > n-1 || visit[ny][nx]) continue;

                visit[ny][nx] = true;
                Q.add(new Point(ny, nx, m+1));
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] loca = new int[4];
        for(int i=0; i<4; i++)
            loca[i] = Integer.parseInt(st.nextToken());

        System.out.println(BFS(loca));
    }
}