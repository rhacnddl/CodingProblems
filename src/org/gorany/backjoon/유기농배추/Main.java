package org.gorany.backjoon.유기농배추;

import java.util.*;

public class Main {
    static class Point {
        int x, y;

        Point(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }
    static short[] arr;
    static byte[][] table;
    static Queue<Point> Q = new LinkedList<>();

    static short size;

    static short BFS(int rows, int cols) {

        size = 0;

        for (int i = 1; i <= rows; i++)
            for (int j = 1; j <= cols; j++) {

                if (table[i][j] == 1) { //No visit And value == 1
                    size++;
                    table[i][j] = -1; //방문 o
                    Q.add(new Point(i, j));

                    while (!Q.isEmpty()) {
                        Point p = Q.poll();
                        int y = p.y;
                        int x = p.x;

                        if (table[y - 1][x] == 1) {
                            table[y - 1][x] = -1;
                            Q.add(new Point(y - 1, x));
                        }
                        if (table[y][x - 1] == 1) {
                            table[y][x - 1] = -1;
                            Q.add(new Point(y, x - 1));
                        }
                        if (table[y + 1][x] == 1) {
                            table[y + 1][x] = -1;
                            Q.add(new Point(y + 1, x));
                        }
                        if (table[y][x + 1] == 1) {
                            table[y][x + 1] = -1;
                            Q.add(new Point(y, x + 1));
                        }
                    }
                }
            }
        return size;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt(); //test 개수
        arr = new short[t];

        for(int a=0; a<t; a++){
            int cols = sc.nextInt(); //가로
            int rows = sc.nextInt(); //세로
            int n = sc.nextInt(); //배추 수
            table = new byte[rows+2][cols+2];

            for(int i=0; i<n; i++) {
                int col = sc.nextInt();
                int row = sc.nextInt();
                table[row + 1][col + 1] = 1;
            }

            arr[a] = BFS(rows, cols);
        }
        for(int a=0; a<t; a++)
            System.out.println(arr[a]);

    }
}
