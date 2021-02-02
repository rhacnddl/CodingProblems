package org.gorany.backjoon.섬의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int y, x;
        public Point(int y, int x){
            this.y=y; this.x=x;
        }
    }
    static int cnt, w, h;
    static int[][] map = new int[51][51];
    static boolean[][] visit = new boolean[51][51];
    static int[] Y = {1, -1, 0, 0, 1, 1, -1, -1};
    static int[] X = {0, 0, 1, -1, 1, -1, 1, -1};
    //               아래, 위에, 우측, 좌측, 5시, 8시, 2시, 10시
    static void BFS(){

        Queue<Point> Q = new LinkedList<>();

        for(int i=1; i<=h; i++){
            for(int j=1; j<=w; j++){

                if(!visit[i][j] && map[i][j] == 1){
                    cnt++;
                    visit[i][j] = true;
                    Q.add(new Point(i, j));

                    while(!Q.isEmpty()){
                        Point p = Q.poll();
                        int y = p.y;
                        int x = p.x;

                        for(int a=0; a<8; a++){
                            int ny = y + Y[a];
                            int nx = x + X[a];

                            if((ny>0 && ny<=h) && (nx>0 && nx <=w) && !visit[ny][nx] && map[ny][nx] == 1){
                                visit[ny][nx] = true;
                                Q.add(new Point(ny, nx));
                            }
                        }
                    }
                    Q.clear();
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0) break;

            visit = new boolean[51][51];
            map = new int[51][51];

            for(int i=1; i<=h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            cnt = 0;
            BFS();
            System.out.println(cnt);
        }
    }
}
