package org.gorany.backjoon.다리만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x, d;
        public Point(int yy, int xx){
            y=yy; x=xx;
        }
        public Point(int yy, int xx, int dd){
            this(yy, xx);
            d=dd;
        }
    }
    static final int N = 101;
    static boolean[][] visit = new boolean[N][N];
    static int[][] map = new int[N][N];

    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static int min = 1000000000;

    static void labeling(int n){
        Queue<Point> Q = new LinkedList<>();
        int area = 0;
        int[][] label = new int[N][N];

        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++){
                if(!visit[i][j] && map[i][j] == 1){
                    area++;
                    visit[i][j] = true;
                    label[i][j] = area;
                    Q.add(new Point(i, j));
                }
                while(!Q.isEmpty()){
                    Point p = Q.poll();

                    for(int a=0; a<4; a++){
                        int ny = p.y + Y[a];
                        int nx = p.x + X[a];

                        if(ny < 1 || nx < 1 || ny > n || nx > n || visit[ny][nx] || map[ny][nx] == 0) continue;

                        label[ny][nx] = area;
                        visit[ny][nx] = true;
                        Q.add(new Point(ny, nx));
                    }
                }
            }
        map = label.clone();
        /*for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }*/
    }
    static void BFS(int n){

        Queue<Point> Q = new LinkedList<>();

        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++) {

                int current = map[i][j];

                if (map[i][j] != 0) {
                    visit = new boolean[N][N];
                    visit[i][j] = true;
                    Q.add(new Point(i, j, 0));
                }

                while(!Q.isEmpty()){
                    Point p = Q.poll();

                    int y = p.y;
                    int x = p.x;
                    int d = p.d;

                    if(d > min) continue;

                    for(int a=0; a<4; a++){
                        int ny = y + Y[a];
                        int nx = x + X[a];

                        if(ny < 1 || nx < 1 || ny > n || nx > n || visit[ny][nx] || map[ny][nx] == current) continue;

                        visit[ny][nx] = true;

                        if(map[ny][nx] == 0)
                            Q.add(new Point(ny, nx, d + 1));
                        else
                            min = Math.min(min, d);
                    }
                }
            }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        labeling(n);
        BFS(n);

        System.out.println(min);
    }
}