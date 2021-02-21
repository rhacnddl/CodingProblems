package org.gorany.backjoon.불느낌표;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
    static char[][] map = new char[1002][1002];
    static boolean[][] visit = new boolean[1002][1002];
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static Queue<Point> fireQ = new LinkedList<>(), Q = new LinkedList<>();
    static int r, c;
    static boolean possible;

    static void fireBFS(){

        Queue<Point> tmpQ = new LinkedList<>();

        while(!fireQ.isEmpty()){
            Point p = fireQ.poll();

            for(int a=0; a<4; a++){
                int ny = p.y + Y[a];
                int nx = p.x + X[a];

                if(ny < 1 || nx < 1 || ny > r || nx > c || map[ny][nx] == '#' || map[ny][nx] == 'F') continue;

                map[ny][nx] = 'F';
                tmpQ.add(new Point(ny, nx));
            }
        }
        fireQ = tmpQ;
    }

    static boolean BFS(){

        Queue<Point> tmpQ = new LinkedList<>();

        while(!Q.isEmpty()){
            Point p = Q.poll();

            if(p.y < 1 || p.x < 1 || p.y > r || p.x > c) {
                possible = true;
                break;
            }

            for(int a=0; a<4; a++){
                int ny = p.y+Y[a];
                int nx = p.x+X[a];

                if(ny < 0 || nx < 0 || ny > r+1 || nx > c+1 || visit[ny][nx]) continue;

                if(map[ny][nx] == '.') {
                    visit[ny][nx] = true;
                    tmpQ.add(new Point(ny, nx));
                }
            }
        }

        Q = tmpQ;
        return Q.size() == 0?false:true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        Arrays.fill(map[0], '.');
        Arrays.fill(map[r+1], '.');
        for(int i=0; i<=r+1; i++) {
            map[i][0] = '.';
            map[i][c+1] = '.';
        }

        for(int i=1; i<=r; i++){
            char[] arr = br.readLine().toCharArray();
            for(int j=1; j<=c; j++){
                map[i][j] = arr[j-1];

                if(map[i][j] == 'J') {
                    Q.add(new Point(i, j));
                    visit[i][j] = true;
                }
                else if(map[i][j] == 'F')
                    fireQ.add(new Point(i, j));
            }
        }
        int cnt = -1;
        while(true){
            cnt++;
            fireBFS();

            if(!BFS()) break;
            if(possible) break;
        }
        System.out.println(possible?cnt:"IMPOSSIBLE");
    }
}