package org.gorany.backjoon.벽부수고이동하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

public class Main {

    static class Point{
        int y, x, wall;
        public Point(int y, int x, int wall){
            this.y=y;
            this.x=x;
            this.wall=wall;
        }
    }
    
    static int[] Y = {1,-1,0,0};
    static int[] X = {0,0,1,-1};
    static Queue<Point> Q = new LinkedList<>();
    static int[][] graph;
    static int[][][] table;

    static void BFS(int rows, int cols){

        //wall : 벽을 안부시면 1, 벽을 부시면 0
        table[1][1][1] = 1;
        Q.add(new Point(1,1,1));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;
            int wall = p.wall;

            if(y == rows && x == cols){
                System.out.println(table[y][x][wall]);
                return;
            }

            for(int a=0; a<4; a++){
                int nextY = y + Y[a];
                int nextX = x + X[a];

                if( (nextX >= 0 && nextX <= cols) && (nextY >= 0 && nextY <= rows) )
                    if(graph[nextY][nextX] == 1 && wall == 1){
                        table[nextY][nextX][wall-1] = table[y][x][wall] + 1;
                        Q.add(new Point(nextY, nextX, wall-1));
                    }
                    else if(graph[nextY][nextX] == 0 && table[nextY][nextX][wall] == 0){
                        table[nextY][nextX][wall] = table[y][x][wall] + 1;
                        Q.add(new Point(nextY, nextX, wall));
                    }
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        int m = Integer.parseInt(str[0]); //rows
        int n = Integer.parseInt(str[1]); //cols

        graph = new int[m+2][n+2];
        table = new int[m+2][n+2][2];

        IntStream.rangeClosed(0, n+1).forEach(i->{
            graph[0][i]=1; graph[m+1][i]=1;
        });
        IntStream.rangeClosed(0, m+1).forEach(j->{
            graph[j][0]=1; graph[j][n+1]=1;
        });

        for(int i=1; i<=m; i++){
            str = br.readLine().split("");
            for(int j=1; j<=n; j++)
                graph[i][j] = Integer.parseInt(str[j-1]);
        }

        BFS(m,n);

        //debug
        /*for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++)
                System.out.print(graph[i][j]+ " ");
            System.out.println();
        }
        System.out.println("---------------------------------------------");
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++)
                System.out.print(table[i][j][1]+ " ");
            System.out.println();
        }
        System.out.println("---------------------------------------------");
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++)
                System.out.print(table[i][j][0]+ " ");
            System.out.println();
        }*/
    }
}
