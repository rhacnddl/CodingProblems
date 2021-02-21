package org.gorany.backjoon.음식물피하기;

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
    static int r, c, k;
    static boolean[][] visit = new boolean[101][101];
    static char[][] map = new char[101][101];
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};

    static int BFS(){

        int max = 0;
        Queue<Point> Q = new LinkedList<>();

        for(int i=1; i<=r; i++)
            for(int j=1; j<=c; j++){
                int size = 1;
                if(!visit[i][j] && map[i][j] == '#'){
                    visit[i][j] = true;
                    Q.add(new Point(i, j));
                }

                while(!Q.isEmpty()){
                    Point p = Q.poll();

                    for(int a=0; a<4; a++){
                        int ny = p.y+Y[a];
                        int nx = p.x+X[a];

                        if(ny < 1 || nx < 1 || ny > r || nx > c || visit[ny][nx] || map[ny][nx] != '#') continue;

                        visit[ny][nx] = true;
                        size++;
                        Q.add(new Point(ny, nx));
                    }
                }

                max = Math.max(max, size);
            }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            map[y][x] = '#';
        }

        System.out.println(BFS());
    }
}