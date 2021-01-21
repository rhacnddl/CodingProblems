package org.gorany.backjoon.토마토;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    static class Point{
        int y, x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    static int[] arrY = {1, 0, -1, 0};
    static int[] arrX = {0, 1, 0, -1};
    static Queue<Point> Q = new LinkedList<>();
    static int[][] table;

    static void BFS(int rows, int cols){

        int max = 0;

        for(int i=1; i<=rows; i++)
            for(int j=1; j<=cols; j++)
                if(table[i][j] == 1) Q.add(new Point(i, j)); //Enqueue

        while(!Q.isEmpty()){
            Point p = Q.poll(); //Dequeue
            int y = p.y;
            int x = p.x;

            for(int a=0; a<4; a++)
                if(table[y+arrY[a]][x+arrX[a]] == 0){
                    table[y+arrY[a]][x+arrX[a]] = table[y][x] + 1;
                    max = (table[y+arrY[a]][x+arrX[a]] > max)?table[y+arrY[a]][x+arrX[a]]:max; //max값 비교
                    Q.add(new Point(y+arrY[a], x+arrX[a])); //Enqueue
                }
        }
        for(int i=1; i<=rows; i++)
            for(int j=1; j<=cols; j++)
                if(table[i][j] == 0) {
                    System.out.println(-1);
                    System.exit(0);
                }
        /*IntStream.rangeClosed(1, rows).forEach(i->{
            if(Arrays.stream(table[i]).anyMatch(n->n==0)){
                System.out.println(-1);
                System.exit(0);
            }
        });*/
        System.out.println(max-1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cols = sc.nextInt();
        int rows = sc.nextInt();

        if(cols < 2 || rows > 1000) System.exit(0);

        table = new int[rows+2][cols+2];
        IntStream.rangeClosed(0,rows+1).forEach(y->{
            table[y][0] = -1;
            table[y][cols+1] = -1;
        });
        IntStream.rangeClosed(0,cols+1).forEach(x->{
            table[0][x] = -1;
            table[rows+1][x] = -1;
        });

        boolean zero = false; //0이 없으면 false 유지
        for(int i=1; i<=rows; i++)
            for(int j=1; j<=cols; j++) {
                table[i][j] = sc.nextInt();
                if(table[i][j] == 0)
                    zero = true;
            }

        if(!zero) System.out.println(0);
        else BFS(rows, cols);

        for(int i=1; i<=rows; i++) {
            for (int j = 1; j <= cols; j++)
                System.out.print(table[i][j] + " ");
            System.out.println();
        }
    }
}
