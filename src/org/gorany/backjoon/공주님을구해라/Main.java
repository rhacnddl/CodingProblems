package org.gorany.backjoon.공주님을구해라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point{
        int y, x, find, move;
        public Point(int yy, int xx, int find, int move){
            y=yy; x=xx; this.find = find; this.move = move;
        }
    }
    static int r,c,t;
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static int[][] map = new int[101][101];
    static boolean[][][] visit = new boolean[101][101][2];

    static int BFS(){

        Queue<Point> Q = new LinkedList<>();
        int result = 100000000;
        visit[1][1][0] = true;

        Q.add(new Point(1,1, 0, 0));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;
            int move = p.move;
            int find = p.find;

            if(y == r && x == c)
                result = Math.min(result, move);

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];
                int nMove = move + 1;

                if(ny < 1 || nx < 1 || ny > r || nx > c || visit[ny][nx][find] || nMove > t) continue;
                if(find == 0 && map[ny][nx] == 1) continue;

                visit[ny][nx][find] = true;
                Q.add(new Point(ny, nx, find, nMove));

                if(map[ny][nx] == 2){
                    visit[ny][nx][1] = true;
                    Q.add(new Point(ny, nx, 1, nMove));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        for(int i=1; i<=r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=c; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        int result = BFS();
        System.out.println(result != 100000000? result : "Fail");
    }
}
