package org.gorany.backjoon.나이트의이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x;
        public Point(int y, int x){ this.y=y; this.x=x;}
    }
    static final int[] X = {-2, -1,  1,  2, 2, 1,-1,-2};
    static final int[] Y = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int l;
    static int[][] graph;
    /*static int[] result;
    static int cnt = 0;*/
    static Queue<Point> Q = new LinkedList<>();
    static void BFS(int startY, int startX, int endY, int endX){

        graph[startY][startX] = 1;
        Q.add(new Point(startY, startX));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;

            if(y == endY && x == endX) {
                System.out.println(graph[endY][endX] - 1);
                /*result[cnt++] = graph[endY][endX] - 1;*/
                Q.clear();
                break;
            }

            for(int a=0; a<8; a++) {
                int nextY = y + Y[a];
                int nextX = x + X[a];

                if ((nextY >= 0 && nextY < l) && (nextX >= 0 && nextX < l))
                    if(graph[nextY][nextX] == 0) {
                        graph[nextY][nextX] = graph[y][x] + 1;
                        Q.add(new Point(nextY, nextX));
                    }

            }
        } //.while()
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        //테스트 케이스 수
        int n = Integer.parseInt(br.readLine());
        /*result = new int[n];*/

        for(int i=0; i<n; i++){
            l = Integer.parseInt(br.readLine());
            graph = new int[l][l]; //0 ~ l - 1

            int[] arr = new int[4];
            for(int j=0; j<2; j++){
                st = new StringTokenizer(br.readLine());
                arr[j] = Integer.parseInt(st.nextToken());
                arr[j+2] = Integer.parseInt(st.nextToken());
            }
            BFS(arr[0], arr[2], arr[1], arr[3]);
        }
        /*for(int i=0; i<n; i++)
            System.out.println(result[i]);*/
    }
}
