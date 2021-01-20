package org.gorany.backjoon.단지번호붙이기;

import java.util.*;

public class Main {
    static class Point {
        int x, y;

        Point(int y, int x) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }
    }

    static short[][] table;
    static Queue<Point> Q = new LinkedList<>();
    static List<Short> list = new ArrayList<>();

    static short size = 0;

    static void BFS(int n) {

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++) {

                short cnt = 0;
                if (table[i][j] == 1) { //No visit And value == 1
                    size++;
                    table[i][j] = -1; //방문 o
                    Q.add(new Point(i, j));

                    while (!Q.isEmpty()) {
                        Point p = Q.poll();
                        int y = p.getY();
                        int x = p.getX();

                        cnt++;

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
                    list.add(cnt);
                }
            }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        table = new short[n+2][n+2];

        for(int i=1; i<=n; i++) {
            String[] arr = sc.next().split("");
            for (int j = 1; j <= n; j++) {
                table[i][j] = Short.parseShort(arr[j-1]);
            }
        }

        BFS(n);

        System.out.println(size);
        list.stream().sorted().forEach(System.out::println);
    }
}
