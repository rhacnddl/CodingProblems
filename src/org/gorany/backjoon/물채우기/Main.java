package org.gorany.backjoon.물채우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Edge implements Comparable<Edge>{
        int y, x, w;
        public Edge(int y, int x, int w){
            this.y=y;
            this.x=x;
            this.w=w;
        }
        @Override
        public int compareTo(Edge o) {
            return w-o.w;
        }
    }
    static boolean[][] visit;
    static int[][] table;
    static int m, n;
    static Queue<Edge> Q = new PriorityQueue<>();
    static int[] Y = {-1, 1, 0, 0};
    static int[] X = {0, 0, -1, 1};

    static int water = 0;
    static void Dijkstra(){

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            int y = edge.y;
            int x = edge.x;
            int w = edge.w; //현재 위치한 곳의 높이

            for(int a=0; a<4; a++) {
                int ny = y + Y[a];
                int nx = x + X[a];

                if ((ny >= 0 && ny < n) && (nx >= 0 && nx < m) && !visit[ny][nx]){
                    visit[ny][nx] = true;

                    //내 주변에 나보다 높은 곳이 있다면, 그 곳을 새로운 울타리라고 생각한다.
                    if(table[ny][nx] > w)
                        Q.add(new Edge(ny, nx, table[ny][nx]));

                    //w >= table[ny][nx]
                    //내 주변에 나와 같거나 낮은 곳이 있다면, 그 곳으로 물을 채워주고(현재 높이 - 그 곳의 높이), 채워준 만큼 water에 합한다.
                    //그리고 그곳에서 더 물을 채울 곳이 있나 탐색
                    else if(table[ny][nx] <= w){
                        water += w - table[ny][nx];
                        Q.add(new Edge(ny, nx, w));
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        table = new int[n][m];
        visit = new boolean[n][m];

         for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());

                if(i == 0 || j == 0 || i == n-1 || j == m-1){
                    visit[i][j] = true;
                    Q.add(new Edge(i, j, table[i][j]));
                }
            }
        }

         Dijkstra();
         System.out.println(water);
    }
}
