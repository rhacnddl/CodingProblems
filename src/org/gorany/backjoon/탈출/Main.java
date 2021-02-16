package org.gorany.backjoon.탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x;
        public Point(int yy, int xx){
            y=yy; x=xx;
        }
    }

    static int r, c, time = 0;
    static boolean flag;

    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static char[][] map = new char[51][51];
    static boolean[][] visit = new boolean[51][51];
    static Queue<Point> waterQ = new LinkedList<>(), Q = new LinkedList<>();

    static void waterBFS(){

        int size = waterQ.size();

        for(int i=0; i<size; i++){
            Point p = waterQ.poll();
            int y = p.y;
            int x = p.x;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                if(ny < 1 || nx < 1 || ny > r || nx > c || map[ny][nx] == 'D' || map[ny][nx] == 'X' || map[ny][nx] == '*') continue;

                map[ny][nx] = '*';
                waterQ.add(new Point(ny, nx));
            }
        }
    }
    static void animalBFS(){

        Queue<Point> tmpQ = new LinkedList<>();

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;

            for(int a=0; a<4; a++) {
                int ny = y + Y[a];
                int nx = x + X[a];

                if (ny < 1 || nx < 1 || ny > r || nx > c || visit[ny][nx] || map[ny][nx] == 'X' || map[ny][nx] == '*') continue;

                if(map[ny][nx] == 'D') flag = true;

                visit[ny][nx] = true;
                tmpQ.add(new Point(ny, nx));
            }
        }
        Q = tmpQ;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for(int i=1; i<=r; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=c; j++){
                map[i][j] = str[j-1];
                if(map[i][j] == 'S') {
                    visit[i][j] = true;
                    Q.add(new Point(i, j));
                }
                else if(map[i][j] == '*')
                    waterQ.add(new Point(i, j));
            }
        }

        while(true){
            time++;
            waterBFS();
            animalBFS();

            if(flag) break;
            if(Q.size() == 0){
                System.out.println("KAKTUS");
                System.exit(0);
            }
        }
        System.out.println(time);
    }
}
