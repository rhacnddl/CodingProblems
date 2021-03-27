package org.gorany.backjoon.로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x, dir;
        public Point(int yy, int xx, int d){
            y=yy;
            x=xx;
            dir = d;
        }
    }
    static int r, c, cnt = 1;
    static int[] Y = {-1, 0, 1, 0}, X = {0, 1, 0, -1};
    static int[][] map = new int[51][51];
    static boolean[][] visit = new boolean[50][50];

    static void clean(Point start){
        Queue<Point> Q = new LinkedList<>();
        visit[start.y][start.x] = true;
        Q.add(new Point(start.y, start.x, start.dir));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;
            int dir = p.dir;
            int nDir = dir;

            boolean isClean = false;
            for(int a=0; a<4; a++){
                nDir -= 1;
                if(nDir < 0) nDir = 3;

                int ny = y+Y[nDir];
                int nx = x+X[nDir];

                if(map[ny][nx] == 1 || visit[ny][nx]) continue;

                isClean = true;
                visit[ny][nx] = true;
                cnt++;

                //System.out.println("("+ny+", "+nx+") 방문 , dir : " + nDir);
                Q.add(new Point(ny, nx, nDir));
                break;
            }

            if(!isClean) {
                int ny = y - Y[dir];
                int nx = x - X[dir];

                if(map[ny][nx] == 1)
                    break;

                Q.add(new Point(ny, nx, dir));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<c; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        clean(new Point(y, x, dir));

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++)
                System.out.print(visit[i][j]?"O ":"X ");
            System.out.println();
        }

        System.out.println(cnt);
    }
}