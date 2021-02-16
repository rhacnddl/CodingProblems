package org.gorany.backjoon.인구이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x;
        public Point(int yy, int xx){
            y = yy; x = xx;
        }
    }
    static int[][] map = new int[50][50], visit;
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static int n, l, r, cnt = 0, sum = 0, area = 0;

    static boolean isUnion(int y, int x){
        boolean flag = false;

        for(int a=0; a<4; a++){
            int ny = y+Y[a];
            int nx = x+X[a];

            if(ny < 0 || nx < 0 || ny >= n || nx >= n || visit[ny][nx] != 0) continue;

            if(Math.abs(map[y][x] - map[ny][nx]) >= l && Math.abs(map[y][x] - map[ny][nx]) <= r) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    static boolean BFS(){
        boolean open = false;
        Queue<Point> Q = new LinkedList<>();

        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++) {
                cnt = 1;
                sum = map[i][j];

                if (visit[i][j] == 0 && isUnion(i, j)) {
                    open = true;
                    area++;
                    visit[i][j] = area;
                    Q.add(new Point(i, j));

                    while (!Q.isEmpty()) {
                        Point p = Q.poll();
                        int y = p.y;
                        int x = p.x;

                        for (int a = 0; a < 4; a++) {
                            int ny = y + Y[a];
                            int nx = x + X[a];

                            if (ny < 0 || nx < 0 || ny >= n || nx >= n || visit[ny][nx] != 0) continue;

                            if (Math.abs(map[y][x] - map[ny][nx]) >= l && Math.abs(map[y][x] - map[ny][nx]) <= r) {
                                cnt++;
                                sum += map[ny][nx];
                                visit[ny][nx] = area;
                                Q.add(new Point(ny, nx));
                            }
                        }
                    }
                    for (int a = 0; a < n; a++)
                        for (int b = 0; b < n; b++)
                            if (visit[a][b] == area)
                                map[a][b] = sum / cnt;

                    for (int a = 0; a < n; a++) {
                        for (int b = 0; b < n; b++)
                            System.out.print(visit[a][b] + " ");
                        System.out.println();
                    }
                    System.out.println("------------------------------");
                    for (int a = 0; a < n; a++) {
                        for (int b = 0; b < n; b++)
                            System.out.print(map[a][b] + " ");
                        System.out.println();
                    }
                }
            }

        return open;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int time = 0;
        while(true){
            area = 0;
            visit = new int[50][50];
            if(!BFS()) break;
            time++;
        }

        System.out.println(time);
    }
}
