package org.gorany.backjoon.미로탐색;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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
    static short[][] table;

    static void BFS(int rows, int cols){

        table[1][1] = 2;
        Q.add(new Point(1, 1));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;

            System.out.printf("(%d, %d) 방문\n", y, x);

            for(int i=0; i<4; i++)
                if(table[y+arrY[i]][x+arrX[i]] == 1){
                    table[y+arrY[i]][x+arrX[i]] = (short) (table[y][x] + 1);
                    Q.add(new Point(y+arrY[i], x+arrX[i]));
                }
        }
        if(table[rows][cols] != 1) System.out.print(table[rows][cols]-1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = sc.nextInt();
        int cols = sc.nextInt();

        table = new short[rows+2][cols+2];

        for(int i=1; i<=rows; i++){
            String[] str = sc.next().split("");
            for(int j=1; j<=cols; j++)
                table[i][j] = Short.parseShort(str[j-1]);
        }
        BFS(rows, cols);
    }
}
