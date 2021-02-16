package org.gorany.backjoon.양;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x;
        public Point(int yy, int xx){ y=yy; x=xx; }
    }
    static int r, c, wolves = 0, sheeps = 0;
    static boolean[][] visit;
    static char[][] map;
    static int[] Y = {-1,1,0,0}, X = {0,0,1,-1};

    static void BFS(){
        Queue<Point> Q = new LinkedList<>();

        for(int i=1; i<=r; i++)
            for(int j=1; j<=c; j++){
                int wolf = 0, sheep = 0;

                if(!visit[i][j] && map[i][j] != '#'){
                    visit[i][j] = true;

                    if(map[i][j] == 'v') wolf++;
                    else if(map[i][j] == 'o') sheep++;

                    Q.add(new Point(i, j));
                }

                while(!Q.isEmpty()){
                    Point p = Q.poll();
                    int y = p.y;
                    int x = p.x;

                    for(int a=0; a<4; a++){
                        int ny = y+Y[a];
                        int nx = x+X[a];

                        if((ny > 0 && ny <= r) && (nx > 0 && nx <= c) && !visit[ny][nx] && map[ny][nx] != '#'){
                            char c = map[ny][nx];

                            if(c == 'v') wolf++;
                            else if(c == 'o') sheep++;

                            visit[ny][nx] = true;
                            Q.add(new Point(ny, nx));
                        }
                    }
                }
                if(sheep > wolf) wolf = 0;
                else sheep = 0;
                wolves += wolf;
                sheeps += sheep;
            }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = 251;

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        visit = new boolean[N][N];
        for(int i=1; i<=r; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=c; j++)
                map[i][j] = str[j-1];
        }
        BFS();

        System.out.print(sheeps + " " + wolves);
    }
}
