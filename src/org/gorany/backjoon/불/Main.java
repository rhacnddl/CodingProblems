package org.gorany.backjoon.불;

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
    static final int N = 1001;
    static int h, w, move;
    static boolean flag;
    static Queue<Point> fireQ, Q;

    static char[][] map;
    static boolean[][] visit;
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};


    static void fireBFS(){

        Queue<Point> tmpQ = new LinkedList<>();

        while(!fireQ.isEmpty()){
            Point p = fireQ.poll();

            for(int a=0; a<4; a++){
                int ny = p.y+Y[a];
                int nx = p.x+X[a];

                if(ny < 1 || nx < 1 || ny > h || nx > w || map[ny][nx] == '#' || map[ny][nx] == '*') continue;

                map[ny][nx] = '*';
                tmpQ.add(new Point(ny, nx));
            }
        }
        fireQ = tmpQ;
    }
    static boolean BFS(){

        Queue<Point> tmpQ = new LinkedList<>();

        while(!Q.isEmpty()){
            Point p = Q.poll();

            if(p.y == 1 || p.y == h || p.x == 1 || p.x == w) flag = true;

            for(int a=0; a<4; a++){
                int ny = p.y+Y[a];
                int nx = p.x+X[a];

                if(ny < 1 || nx < 1 || ny > h || nx > w || map[ny][nx] != '.' || visit[ny][nx]) continue;

                visit[ny][nx] = true;
                tmpQ.add(new Point(ny, nx));
            }
        }
        Q = tmpQ;

        return tmpQ.size() != 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        for(int p=0; p<k; p++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            visit = new boolean[N][N];
            map = new char[N][N];
            fireQ = new LinkedList<>();
            Q = new LinkedList<>();
            flag = false;
            move = 0;

            for(int i=1; i<=h; i++){
                char[] arr = br.readLine().toCharArray();
                for(int j=1; j<=w; j++){
                    map[i][j] = arr[j-1];

                    if(map[i][j] == '@'){
                        visit[i][j] = true;
                        Q.add(new Point(i, j));
                    }
                    else if(map[i][j] == '*')
                        fireQ.add(new Point(i, j));
                }
            }

            while(!flag){
                move++;
                fireBFS();
                if(!BFS()) break;
            }

            System.out.println(flag? move : "IMPOSSIBLE");
        }
    }
}
