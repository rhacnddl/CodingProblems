package org.gorany.backjoon.토마토3차원;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static class Point{
        int h, y, x;
        public Point(int h, int y, int x){
            this.h = h;
            this.y = y;
            this.x = x;
        }
    }
    static int[] arrH = {0, 0, 0, 0, 1, -1};
    static int[] arrY = {1, -1, 0, 0, 0, 0};
    static int[] arrX = {0, 0, 1, -1, 0, 0};

    static Queue<Point> Q = new LinkedList<>();
    static int[][][] table;

    static void BFS(int height, int rows, int cols){

        int max = 0;

        for(int h=1; h<=height; h++)
            for(int i=1; i<=rows; i++)
                for(int j=1; j<=cols; j++)
                    if(table[h][i][j] == 1) Q.add(new Point(h, i, j)); //Enqueue

        while(!Q.isEmpty()){
            Point p = Q.poll(); //Dequeue
            int h = p.h;
            int y = p.y;
            int x = p.x;

            for(int a=0; a<6; a++)
                if(table[h+arrH[a]][y+arrY[a]][x+arrX[a]] == 0){
                    table[h+arrH[a]][y+arrY[a]][x+arrX[a]] = table[h][y][x] + 1;
                    max = (table[h+arrH[a]][y+arrY[a]][x+arrX[a]] > max)?table[h+arrH[a]][y+arrY[a]][x+arrX[a]]:max; //max값 비교
                    Q.add(new Point(h+arrH[a], y+arrY[a], x+arrX[a])); //Enqueue
                }
        }
        for(int h=1; h<=height; h++)
            for(int i=1; i<=rows; i++)
                for(int j=1; j<=cols; j++)
                    if(table[h][i][j] == 0) {
                        System.out.println(-1);
                        System.exit(0);
                    }

        System.out.println(max-1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cols = sc.nextInt();
        int rows = sc.nextInt();
        int height = sc.nextInt();
       /* String s = sc.nextLine();
        String[] str = s.split(" ");
        int cols = Integer.parseInt(str[0]);
        int rows = Integer.parseInt(str[1]);
        int height = Integer.parseInt(str[2]);*/

        table = new int[height+2][rows+2][cols+2];

        for(int h=1; h<=height; h++) {
            for(int i=0; i<=rows+1; i++){
                table[h][i][0] = -1;
                table[h][i][cols+1] = -1;
            }
            for(int j=0; j<=cols+1; j++){
                table[h][0][j] = -1;
                table[h][rows+1][j] = -1;
            }
        }
        for(int i=0; i<=rows+1; i++)
            for(int j=0; j<=cols+1; j++){
                table[0][i][j] = -1;
                table[height+1][i][j] = -1;
            }

        boolean zero = false; //0이 없으면 false 유지
        for(int h=1; h<=height; h++)
            for(int i=1; i<=rows; i++)
                for(int j=1; j<=cols; j++) {
                    table[h][i][j] = sc.nextInt();
                    if(table[h][i][j] == 0)
                        zero = true;
                }

        if(!zero) System.out.println(0);
        else BFS(height, rows, cols);

        for(int h=1; h<=height; h++){
            for(int i=1; i<=rows; i++) {
                for (int j = 1; j <= cols; j++)
                    System.out.print(table[h][i][j] + " ");
                System.out.println();
            }
            System.out.println("---------------------------------------");
        }
    }
}
