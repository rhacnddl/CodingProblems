package org.gorany.backjoon.적록색약;

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
            y=yy; x=xx;
        }
    }
    static int n, common = 0, uncommon = 0;
    static Queue<Point> Q = new LinkedList<>();
    static int[] Y = {1, -1, 0, 0};
    static int[] X = {0, 0, 1, -1};
    static String[][] map = new String[101][101];
    static boolean[][] visit;

    static void BFS(int select){
        visit = new boolean[101][101];

        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++){
                if(!visit[i][j]){
                    if(select == 0)
                        common++;
                    else
                        uncommon++;
                    visit[i][j] = true;
                    Q.add(new Point(i, j));
                }

                while(!Q.isEmpty()){
                    Point p = Q.poll();
                    int y = p.y;
                    int x = p.x;

                    for(int a=0; a<4; a++){
                        int ny = y+Y[a];
                        int nx = x+X[a];

                        if((ny>0 && ny<=n) && (nx>0 && nx<=n) && !visit[ny][nx]) {
                            switch (select) {
                                case 0: //정상
                                    if (map[ny][nx].equals(map[y][x])) {
                                        visit[ny][nx] = true;
                                        Q.add(new Point(ny, nx));
                                    }
                                    break;
                                case 1: //적록색맹
                                    if (map[y][x].equals("R") || map[y][x].equals("G")) {
                                        if (map[ny][nx].equals("R") || map[ny][nx].equals("G")) {
                                            visit[ny][nx] = true;
                                            Q.add(new Point(ny, nx));
                                        }
                                    }
                                    else if(map[ny][nx].equals(map[y][x])) {
                                        visit[ny][nx] = true;
                                        Q.add(new Point(ny, nx));
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++) {
            String[] str = br.readLine().split("");
            for (int j=1; j<=n; j++)
                map[i][j] = str[j-1];
        }
        BFS(0);
        BFS(1);

        System.out.print(common + " " + uncommon);
    }
}