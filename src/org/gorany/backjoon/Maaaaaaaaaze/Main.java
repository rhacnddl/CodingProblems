package org.gorany.backjoon.Maaaaaaaaaze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x, z, move;
        public Point(int zz, int yy, int xx, int m){
            z=zz;
            y=yy;
            x=xx;
            move=m;
        }
    }
    static final int INF = 1000000000;
    static int min = INF;
    static int[] Y={-1,1,0,0,0,0}, X={0,0,-1,1,0,0}, Z={0,0,0,0,-1,1};
    static int[][][][] map = new int[4][6][6][6];

    static void BFS(int[][][] mat){

        Queue<Point> Q = new LinkedList<>();
        boolean[][][] visit = new boolean[6][6][6];

        visit[1][1][1] = true;
        Q.add(new Point(1,1,1,0));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int z = p.z;
            int y = p.y;
            int x = p.x;
            int move = p.move;

            if(move > min) break;
            if(z == 5 && y == 5 && x == 5) min = move;

            for(int a=0; a<6; a++){
                int nz = z+Z[a];
                int ny = y+Y[a];
                int nx = x+X[a];

                if(nz < 1 || ny < 1 || nx < 1 || nz > 5 || ny > 5 || nx > 5 || visit[nz][ny][nx] || mat[nz][ny][nx] == 0) continue;

                visit[nz][ny][nx] = true;
                Q.add(new Point(nz, ny, nx, move + 1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=1; i<6; i++)
            for(int j=1; j<6; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                for(int z=1; z<6; z++) {
                    int val = Integer.parseInt(st.nextToken());

                    map[0][i][j][z] = val;
                    map[1][i][6-z][j] = val;
                    map[2][i][6-j][6-z] = val;
                    map[3][i][z][6-j] = val;
                }
            }

        for(int first=1; first<6; first++) {
            for (int a = 0; a < 4; a++) {
                if(map[a][first][1][1] == 0) continue;

                for (int second = 1; second < 6; second++) {
                    if (second == first) continue;

                    for (int b = 0; b < 4; b++)
                        for (int third = 1; third < 6; third++) {
                            if (third == first || third == second) continue;

                            for (int c = 0; c < 4; c++)
                                for (int fourth = 1; fourth < 6; fourth++) {
                                    if (fourth == first || fourth == second || fourth == third) continue;

                                    for (int d = 0; d < 4; d++)
                                        for (int fifth = 1; fifth < 6; fifth++) {

                                            if (fifth == first || fifth == second || fifth == third || fifth == fourth)
                                                continue;

                                            for (int e = 0; e < 4; e++) {
                                                if (map[e][fifth][5][5] == 0) continue;

                                                int[][][] mat = new int[6][][];

                                                mat[1] = map[a][first].clone();
                                                mat[2] = map[b][second].clone();
                                                mat[3] = map[c][third].clone();
                                                mat[4] = map[d][fourth].clone();
                                                mat[5] = map[e][fifth].clone();

                                                /*for (int q = 1; q < 6; q++) {
                                                    for (int w = 1; w < 6; w++) {
                                                        for (int r = 1; r < 6; r++)
                                                            System.out.print(mat[q][w][r] + " ");
                                                        System.out.println();
                                                    }
                                                    System.out.println();
                                                }*/

                                                BFS(mat);
                                            }
                                        }
                                }
                        }
                }
            }
        }
        System.out.println(min == INF? -1 : min);
    }
}