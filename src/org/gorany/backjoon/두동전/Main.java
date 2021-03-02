package org.gorany.backjoon.두동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x, cnt;
        public Point(int yy, int xx, int cnt){
            y=yy; x=xx; this.cnt=cnt;
        }
    }
    static Queue<Point>[] Q = new LinkedList[2];
    static char[][] map = new char[22][22];
    static boolean[][][] visit = new boolean[22][22][11];
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static boolean[] coin = new boolean[2];
    static boolean result = true;
    static int count;

    static void BFS(int r, int c, int s){

        Queue<Point> tmpQ = new LinkedList<>();

        while(!Q[s].isEmpty()){
            Point p = Q[s].poll();
            int y = p.y;
            int x = p.x;
            int d = p.cnt;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];
                int nd = d + 1;

                if(ny < 0 || nx < 0 || ny > r+1 || nx > c+1 || visit[ny][nx][nd]) continue;

                if(ny == 0 || nx == 0 || ny == r+1 || nx == c+1) {
                    coin[s] = true;
                    count = nd;
                    System.out.println("("+ny+", "+nx+") coin["+s+"] drop");
                }

                if(map[ny][nx] == '#'){
                    visit[y][x][nd] = true;
                    tmpQ.add(new Point(y, x, nd));
                }
                else{
                    visit[ny][nx][nd] = true;
                    tmpQ.add(new Point(ny, nx, nd));
                }
            }
        }
        if(tmpQ.size() == 0) result = false;
        Q[s] = tmpQ;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Q[0] = new LinkedList<>();
        Q[1] = new LinkedList<>();

        int k=0;
        for(int i=1; i<=n; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=m; j++) {
                map[i][j] = str[j - 1];

                if(map[i][j] == 'o') {
                    Q[k++].add(new Point(i, j, 0));
                    visit[i][j][0] = true;
                }
            }
        }

        while(result){
            count = 0;
            coin[0] = false;
            coin[1] = false;

            BFS(n, m, 0);
            BFS(n, m, 1);

            if(coin[0] ^ coin[1]) {
                System.out.println("코인이 하나만 떨어짐");
                System.out.println(count);
                break;
            }
        }

        if(!result) System.out.println(-1);
    }
}
