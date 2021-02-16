package org.gorany.backjoon.백조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x;
        public Point(int yy, int xx){ y=yy; x=xx; }
    }
    static Queue<Point> waterQ = new LinkedList<>(), swanQ = new LinkedList<>();
    static int r, c, day = 0;

    static boolean[][] visit;
    static char[][] map;

    static Point[] swan = new Point[2];
    static int[] Y = {-1,1,0,0}, X = {0,0,-1,1};
    static boolean find;

    static void waterBFS(){
        //얼음 녹이기
        int tmp = waterQ.size();

        for(int i=0; i<tmp; i++){
            Point p = waterQ.poll();

            for(int a=0; a<4; a++){
                int ny = p.y+Y[a];
                int nx = p.x+X[a];

                if(ny <= 0 || nx <= 0 || ny > r || nx > c) continue;

                if(map[ny][nx] == 'X') {
                    map[ny][nx] = '.';
                    waterQ.add(new Point(ny, nx));
                }
            }
        }
    }

    static void swanBFS(){
        Queue<Point> tmpQ = new LinkedList<>();

        while(!swanQ.isEmpty()){
            Point p = swanQ.poll();

            int y = p.y;
            int x = p.x;

            for(int a=0; a<4; a++){
                int ny = p.y+Y[a];
                int nx = p.x+X[a];
                if(ny <= 0 || nx <= 0 || ny > r || nx > c || visit[ny][nx]) continue;

                if(map[ny][nx] == '.')
                    swanQ.add(new Point(ny, nx));
                else if(map[ny][nx] == 'L'){
                    find = true;
                    return;
                }
                else if(map[ny][nx] == 'X') {
                    map[ny][nx] = '.';
                    tmpQ.add(new Point(ny, nx));
                }

                visit[ny][nx] = true;
            }
        }
        swanQ = tmpQ;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = 1501, a = 0;

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        visit = new boolean[N][N];
        map = new char[N][N];

        for(int i=1; i<=r; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=c; j++) {
                map[i][j] = str[j - 1];

                if(map[i][j] == 'L')
                    swan[a++] = new Point(i, j);

                if(map[i][j] != 'X')
                    waterQ.add(new Point(i, j));
            }
        }

        visit[swan[0].y][swan[0].x] = true;
        swanQ.add(new Point(swan[0].y, swan[0].x));

        while(!find) {
            swanBFS();

            if(find) break;

            waterBFS();
            day++;
        }
        System.out.println(day);
    }
}