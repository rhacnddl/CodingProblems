package org.gorany.backjoon.아맞다우산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x, move, item;
        public Point(int yy, int xx, int m, int it){
            y=yy;
            x=xx;
            move=m;
            item=it;
        }
    }

    static int n, m, k;
    static int[] Y = {-1,1,0,0}, X={0,0,-1,1};
    static char[][] map = new char[51][51];
    static boolean[][][] visit = new boolean[51][51][(1 << 6)];

    static int BFS(int sy, int sx) {

        Queue<Point> Q = new LinkedList<>();
        visit[sy][sx][0] = true;

        Q.add(new Point(sy, sx, 0, 0));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;
            int move = p.move;
            int item = p.item;

            if(map[y][x] == 'E' && item == ((1 << k) - 1))
                return move;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];
                int nItem = 0;

                if(ny < 1 || nx < 1 || ny > n || nx > m || visit[ny][nx][item] || map[ny][nx] == '#') continue;

                if(map[ny][nx] >= '0' && map[ny][nx] <= '5'){
                    int num = map[ny][nx] - '0';

                    if( (item & (1 << num)) == 0){
                        nItem = item | (1 << num);

                        visit[ny][nx][nItem] = true;
                        Q.add(new Point(ny, nx, move + 1, nItem));
                        continue;
                    }
                }

                visit[ny][nx][item] = true;
                Q.add(new Point(ny, nx, move + 1, item));
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        int sY = 0, sX = 0;
        char idx = '0';

        for(int i=1; i<=n; i++) {
            char[] str = br.readLine().toCharArray();
            for (int j = 1; j <= m; j++) {
                map[i][j] = str[j - 1];

                if (map[i][j] == 'X') {
                    map[i][j] = idx++;
                    k++;
                } else if (map[i][j] == 'S') {
                    sY = i;
                    sX = j;
                }
            }
        }
        System.out.println(BFS(sY, sX));
    }
}