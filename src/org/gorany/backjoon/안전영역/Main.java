package org.gorany.backjoon.안전영역;

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
    static int n, cnt;
    static int[][] map = new int[101][101];
    static boolean[][] visit;
    static int[] Y = {1, -1, 0, 0};
    static int[] X = {0, 0, 1, -1};
    static Queue<Point> Q = new LinkedList<>();

    static int BFS(int k){

        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++){
                if(!visit[i][j] && map[i][j] >= k){
                    cnt++;
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

                        if((ny>0 && ny<=n) && (nx>0 && nx <=n) && !visit[ny][nx] && map[ny][nx] >= k){
                            visit[ny][nx] = true;
                            Q.add(new Point(ny, nx));
                        }
                    }
                }
            }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        int k = 0;
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                k = Math.max(k, map[i][j]);
            }
        }
        int max = 0;
        for(int i=0; i<=k; i++){
            cnt = 0;
            visit = new boolean[101][101];
            max = Math.max(max, BFS(i));
            System.out.println(i + " : BFS(i) = " + BFS(i));
        }
        System.out.println(max);
    }
}
