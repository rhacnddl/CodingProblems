package org.gorany.backjoon.치즈;

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
            y=yy;
            x=xx;
        }
    }
    static final int N = 101;
    static int n, m;
    static boolean flag;

    static int[][] map = new int[N][N];
    static int[][] state = new int[N][N];
    static boolean[][] visit = new boolean[N][N];

    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};

    static boolean airBFS(){
        Queue<Point> Q = new LinkedList<>();
        visit[0][0] = true;
        Q.add(new Point(0,0));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                if(ny < 0 || nx < 0 || ny > n || nx > m) continue;

                if(map[ny][nx] == 0 && !visit[ny][nx]){ //공기이고, 방문하지 않았으면
                    visit[ny][nx] = true;
                    Q.add(new Point(ny, nx));
                }

                if(map[ny][nx] == 1) { //치즈이면
                    flag = true; //녹일 치즈가 한 칸이라도 존재하면 true, 치즈가 다 녹았다면 false를 유지한다
                    state[ny][nx]++; //state[ny][nx] >= 2 이면 cheeseBFS에서 녹게된다.
                }
            }
        }
        return flag;
    }

    static void cheeseBFS(){

        Queue<Point> Q = new LinkedList<>();

        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++){
                if(state[i][j] > 1 && !visit[i][j]){
                    map[i][j] = 0;
                    visit[i][j] = true;
                    Q.add(new Point(i, j));
                }

                while(!Q.isEmpty()){
                    Point p = Q.poll();
                    int y = p.y;
                    int x = p.x;

                    for(int a=0; a<4; a++) {
                        int ny = y + Y[a];
                        int nx = x + X[a];

                        if (ny < 0 || nx < 0 || ny > n || nx > m || visit[ny][nx]) continue;

                        if(state[ny][nx] > 1) {
                            visit[ny][nx] = true;
                            map[ny][nx] = 0;
                            Q.add(new Point(ny, nx));
                        }
                    }
                }
            }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int time = 0;
        while(true){
            /* init */
            visit = new boolean[N][N];
            state = new int[N][N];
            flag = false;
            /* init */

            if(!airBFS()) break;

            time++;
            visit = new boolean[N][N];

            cheeseBFS();
        }

        System.out.println(time);
    }
}