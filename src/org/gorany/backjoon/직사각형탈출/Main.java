package org.gorany.backjoon.직사각형탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x, move;

        public Point(int yy, int xx, int move){
            y=yy;
            x=xx;
            this.move=move;
        }
    }
    static int n, m, h, w;
    static int[] Y = {-1,1,0,0}, X = {0,0,-1,1};
    static boolean[][] map = new boolean[1001][1001];
    static boolean[][] visit = new boolean[1001][1001];

    static boolean isAnyWall(int upY, int upX, int downY, int downX, int a){

/*        for(int i=upX; i<=downX; i++)
            if(!map[upY][i] || !map[downY][i]) return false;

        for(int i=upY; i<=downY; i++)
            if(!map[i][upX] || !map[i][downX]) return false;*/

        if(a == 0){
            for(int i=upX; i<=downX; i++)
                if(!map[upY][i]) return false;
        }
        else if(a == 1){
            for(int i=upX; i<=downX; i++)
                if(!map[downY][i]) return false;
        }
        else if(a == 2){
            for(int i=upY; i<=downY; i++)
                if(!map[i][upX]) return false;
        }
        else{
            for(int i=upY; i<=downY; i++)
                if(!map[i][downX]) return false;
        }

        return true;
    }

    static int BFS(int startY, int startX, int endY, int endX){
        Queue<Point> Q = new LinkedList<>();

        visit[startY][startX] = true;
        Q.add(new Point(startY, startX, 0));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;

            if(y == endY && x == endX)
                return p.move;

            int by = y + h - 1;
            int bx = x + w - 1;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                int nBy = by+Y[a];
                int nBx = bx+X[a];

                if(ny < 1 || nx < 1 || ny > n || nx > m || visit[ny][nx]) continue;
                if(nBy > n || nBx > m) continue;

                if(!isAnyWall(ny, nx, nBy, nBx, a)) continue;

                visit[ny][nx] = true;
                Q.add(new Point(ny, nx, p.move + 1));
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=1; j<=m; j++){
                if(Integer.parseInt(st.nextToken()) == 0)
                    map[i][j] = true;
            }
        }

        st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int endY = Integer.parseInt(st.nextToken());
        int endX = Integer.parseInt(st.nextToken());

        System.out.println(BFS(y, x, endY, endX));
    }
}