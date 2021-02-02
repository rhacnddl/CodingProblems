package org.gorany.backjoon.빙산;

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
    static Queue<Point> Q = new LinkedList<>();
    static int[] Y = {0, 0, 1, -1}, X = {1, -1, 0, 0};
    static int[][] map = new int[301][301], sea = new int[301][301];
    static boolean[][] visit;
    static int n, m, time = 0, cnt;

    //map[i][j]의 주변 바다의 개수 반환
    static int getSea(int i, int j){
        int sea = 0;
        for (int a = 0; a < 4; a++) {
            int ny = i + Y[a];
            int nx = j + X[a];

            if (map[ny][nx] == 0)
                sea++;
        }
        return sea;
    }

    static void DFS(int y, int x){

        visit[y][x] = true;

        for(int a=0; a<4; a++){
            int ny = y+Y[a];
            int nx = x+X[a];

            if(!visit[ny][nx] && map[ny][nx] > 0)
                DFS(ny,nx);
        }
    }

    static int func(){
        while(true){
            boolean flag = false;
            visit = new boolean[301][301];
            cnt = 0;

            //시간을 증가시키고 map = map - sea
            time++;

            for(int i=1; i<n-1; i++)
                for(int j=1; j<m-1; j++)
                    if(map[i][j] > 0) sea[i][j] = getSea(i,j);

            for(int i=1; i<n-1; i++)
                for(int j=1; j<m-1; j++)
                    if(map[i][j] > 0) {
                        flag = true;

                        if (map[i][j] - sea[i][j] > 0)
                            map[i][j] -= sea[i][j];
                        else
                            map[i][j] = 0;
                    }
            if(!flag) break; //flag == false 이면 얼음이 모두 녹은 것

            //빙산이 나누어졌는지 cnt 계산(DFS)
            /*for(int i=1; i<n-1; i++)
                for(int j=1; j<m-1; j++)
                    if(!visit[i][j] && map[i][j] > 0){
                        cnt++;
                        if(cnt >= 2) return time;
                        DFS(i, j);
                    }*/

            //빙산이 나누어졌는지 cnt 계산(BFS)
            for(int i=1; i<n-1; i++)
                for(int j=1; j<m-1; j++){
                    if(!visit[i][j] && map[i][j] > 0){
                        cnt++;
                        if(cnt >= 2) return time;

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

                            if(!visit[ny][nx] && map[ny][nx] > 0){
                                visit[ny][nx] = true;
                                Q.add(new Point(ny, nx));
                            }
                        }
                    }
                } //.BFS
        }//.while
        return 0;
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

        System.out.println(func());
    }
}
