package org.gorany.backjoon.그림;

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
    static int[][] map = new int[501][501];
    static boolean[][] visit = new boolean[501][501];
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static int area = 0, cnt = 1, max = 0;

    static void BFS(int r, int c){

        Queue<Point> Q = new LinkedList<>();

        for(int i=1; i<=r; i++)
            for(int j=1; j<=c; j++){
                area = 0;
                if(!visit[i][j] && map[i][j] == 1){
                    cnt++;
                    area++;

                    map[i][j] = cnt;
                    visit[i][j] = true;
                    Q.add(new Point(i, j));
                }

                while(!Q.isEmpty()){
                    Point p = Q.poll();
                    int y = p.y;
                    int x = p.x;

                    for(int a=0; a<4; a++){
                        int ny = y+Y[a];
                        int nx = x+X[a];

                        if(ny < 1 || nx < 1 || ny > r || nx > c || visit[ny][nx]) continue;

                        if(map[ny][nx] == 1){
                            area++;
                            map[ny][nx] = cnt;
                            Q.add(new Point(ny, nx));
                        }
                    }
                }
                max = Math.max(max, area);
            }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        for(int i=1; i<=r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=c; j++){
                int val = Integer.parseInt(st.nextToken());
                map[i][j] = val;
            }
        }
        BFS(r, c);

        System.out.println((cnt-1) + "\n" + max);
    }
}
