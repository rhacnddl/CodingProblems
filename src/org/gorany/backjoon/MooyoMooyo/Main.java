package org.gorany.backjoon.MooyoMooyo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int y, x;
        public Point(int yy, int xx){
            y=yy;
            x=xx;
        }
    }

    static Stack<Point> stack = new Stack<>();
    static int n, k, cnt;
    static int[] Y = {-1,1,0,0}, X = {0,0,-1,1};
    static char[][] map = new char[101][11];
    static boolean[][] visit;

    static void downward(){

        for(int i=n-1; i > 0; i--)
            for(int j=1; j<=10; j++) {
                //현재 정점이 0 또는 아래 정점이 !0
                if (map[i][j] == '0' || map[i + 1][j] != '0') continue;

                int z = i+1;
                while(z <= n && map[z][j] == '0'){
                    map[z][j] = map[z-1][j];
                    map[z-1][j] = '0';
                    z++;
                }
            }
    }
    static void DFS(int y, int x, int value){

        cnt++;
        visit[y][x] = true;
        stack.push(new Point(y, x));

        for(int a=0; a<4; a++){
            int ny = y+Y[a];
            int nx = x+X[a];

            if(ny < 1 || nx < 1 || ny > n || nx > 10 || visit[ny][nx] || map[ny][nx] != value) continue;

            DFS(ny, nx, value);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=10; j++)
                map[i][j] = str[j-1];
        }

        while(true){
            visit = new boolean[101][11];
            boolean flag = false;

            downward();
            for(int i=n; i>0; i--)
                for(int j=1; j<=10; j++){
                    if(visit[i][j] || map[i][j] == '0') continue;
                    stack.clear();
                    cnt = 0;
                    DFS(i, j, map[i][j]);

                    if(cnt >= k) {
                        flag = true;
                        for (Point p : stack)
                            map[p.y][p.x] = '0';
                    }
                }

            if(!flag) break;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            for(int j=1; j<=10; j++)
                sb.append(map[i][j]);
            sb.append('\n');
        }
        System.out.print(sb);
    }
}