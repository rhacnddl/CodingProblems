package org.gorany.backjoon.상범빌딩;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int z, y, x;
        public Point(int zz, int yy, int xx){
            z=zz; y=yy; x=xx;
        }
    }
    static int l, r, c;
    static char[][][] map;
    static boolean[][][] visit;
    static int[][][] move;
    static int[] Z = {-1, 1, 0, 0, 0, 0}, Y = {0, 0, -1, 1, 0, 0}, X = {0, 0, 0, 0, -1, 1};

    static boolean BFS(Point s, Point e){

        Queue<Point> Q = new LinkedList<>();
        boolean flag = false;

        visit[s.z][s.y][s.x] = true;
        Q.add(new Point(s.z, s.y, s.x));

        while(!Q.isEmpty()){
            Point p = Q.poll();

            if(p.z == e.z && p.y == e.y && p.x == e.x) flag = true;

            int cnt = move[p.z][p.y][p.x];

            for(int a=0; a<6; a++){
                int nz = p.z+Z[a];
                int ny = p.y+Y[a];
                int nx = p.x+X[a];

                if(nz < 1 || ny < 1 || nx < 1 || nz > l || ny > r || nx > c || visit[nz][ny][nx] || map[nz][ny][nx] == '#') continue;

                visit[nz][ny][nx] = true;
                move[nz][ny][nx] = cnt + 1;
                Q.add(new Point(nz, ny, nx));
            }
        }
        return flag;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            Point[] p = new Point[2];
            StringTokenizer st = new StringTokenizer(br.readLine());

            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(l+r+c == 0) break;

            map = new char[31][31][31];
            visit = new boolean[31][31][31];
            move = new int[31][31][31];

            for(int i=1; i<=l; i++){
                for(int j=1; j<=r; j++){
                    char[] str = br.readLine().toCharArray();
                    for(int z=1; z<=c; z++){
                        map[i][j][z] = str[z-1];

                        if(map[i][j][z] == 'S') p[0] = new Point(i, j, z);
                        else if(map[i][j][z] == 'E') p[1] = new Point(i, j, z);
                    }
                }
                br.readLine();
            }

            if(BFS(p[0], p[1]))
                System.out.println("Escaped in " + move[p[1].z][p[1].y][p[1].x] + " minute(s).");
            else
                System.out.println("Trapped!");
        }
    }
}