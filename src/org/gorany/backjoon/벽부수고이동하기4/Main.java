package org.gorany.backjoon.벽부수고이동하기4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point {
        int y, x;
        public Point(int yy, int xx) {
            y = yy;
            x = xx;
        }
    }

    static int[][] map = new int[1001][1001], move = new int[1001][1001];
    static boolean[][] visit = new boolean[1001][1001], wall = new boolean[1001][1001];
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static int n, m;

    static void BFS() {

        Queue<Point> Q = new LinkedList<>();
        List<Point> list = new ArrayList<>();

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {

                int cnt = 1;
                if (!visit[i][j] && map[i][j] == 0) {
                    list.clear();
                    visit[i][j] = true;
                    Q.add(new Point(i, j));

                    while (!Q.isEmpty()) { //빈 공간의 개수 구하기
                        Point p = Q.poll();
                        int y = p.y;
                        int x = p.x;

                        for (int a = 0; a < 4; a++) {
                            int ny = y + Y[a];
                            int nx = x + X[a];

                            if (ny < 1 || nx < 1 || ny > n || nx > m || visit[ny][nx]) continue;

                            if(map[ny][nx] == 1){
                                if(!wall[ny][nx]) {
                                    list.add(new Point(ny, nx)); //주변 벽의 좌표 저장
                                    wall[ny][nx] = true;
                                }
                                continue;
                            }

                            cnt++;
                            visit[ny][nx] = true;
                            Q.add(new Point(ny, nx));
                        }
                    }
                    for(Point p:list) { //저장한 벽에 빈 공간 개수 더해줌
                        move[p.y][p.x] += cnt;
                        wall[p.y][p.x] = false;
                    }
                }
            }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=m ;j++) {
                map[i][j] = str[j - 1] - '0';

                if(map[i][j] > 0)
                    move[i][j] = 1;
            }
        }

        BFS();

        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++)
                System.out.print(move[i][j] % 10);
            System.out.println();
        }
    }
}