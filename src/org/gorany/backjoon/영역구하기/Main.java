package org.gorany.backjoon.영역구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point{
        int y, x;
        public Point(int y, int x){
            this.y=y; this.x=x;
        }
    }
    static int n,m,k, cnt = 0;
    static int[][] map = new int[101][101];
    static Queue<Point> Q = new LinkedList<>();
    static boolean[][] visit = new boolean[101][101];
    static List<Integer> list = new ArrayList<>();
    static int[] Y = {1, -1, 0, 0};
    static int[] X = {0, 0, 1, -1};

    static void BFS(){

        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++){
                int area = 0;
                if(!visit[i][j] && map[i][j] == 0){
                    cnt++; //영역 개수 증가
                    area++; //면적 증가
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

                        if ((ny >= 0 && ny < m) && (nx >= 0 && nx < n) && !visit[ny][nx] && map[ny][nx] == 0) {
                            visit[ny][nx] = true;
                            area++;
                            Q.add(new Point(ny, nx));
                        }
                    }
                }
                if(area != 0)
                    list.add(area);
            }//.2for
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for(int a=y1; a<y2; a++)
                for(int b=x1; b<x2; b++)
                    map[a][b] = 1;

        }
        BFS();
        Collections.sort(list);

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }

        System.out.println(cnt);
        for(int a:list)
            System.out.print(a + " ");
    }
}
