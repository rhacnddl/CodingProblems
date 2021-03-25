package org.gorany.backjoon.구슬탈출;

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
    static class Biz{
        Point red, blue;
        int move;
        public Biz(Point r, Point b, int m){
            red = r;
            blue = b;
            move = m;
        }
    }

    static int r, c;
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static char[][] map = new char[11][11];

    static int BFS(Biz start){

        int result = -1;
        Queue<Biz> Q = new LinkedList<>();
        Q.add(start);

        while(!Q.isEmpty()){
            Biz biz = Q.poll();
            Point red = biz.red;
            Point blue = biz.blue;
            int move = biz.move;

            //10번 이하의 이동 중 Red 구슬만 'O'에 들어가는 경우가 없을 땐 result = -1로 동일하다.
            if(map[red.y][red.x] == 'O' && map[blue.y][blue.x] != 'O') {
                result = move;
                break;
            }

            for(int a=0; a<4; a++){
                int nyR = red.y - Y[a];
                int nxR = red.x - X[a];
                int nyB = blue.y - Y[a];
                int nxB = blue.x - X[a];

                //move가 10이하일 때만 가능
                if(move <= 9) {
                    //Red구슬을 상하좌우로 기울여 더 움직일 수 없을 때 까지 혹은 'O'를 만날 때 까지
                    while (true) {
                        nyR += Y[a];
                        nxR += X[a];

                        int ny = nyR + Y[a];
                        int nx = nxR + X[a];

                        if(map[nyR][nxR] == 'O' || map[ny][nx] == '#')
                            break;
                    }

                    //Blue구슬을 상하좌우로 기울여 더 움직일 수 없을 때 까지 혹은 'O'를 만날 때 까지
                    while (true) {
                        nyB += Y[a];
                        nxB += X[a];

                        int ny = nyB + Y[a];
                        int nx = nxB + X[a];

                        if(map[nyB][nxB] == 'O' || map[ny][nx] == '#')
                            break;
                    }

                    //Red와 Blue의 위치가 동일 and 현재 위치가 'O'가 아닐 때
                    //Red와 Blue구슬은 한 칸에 동시에 위치할 수 없음!
                    if(nyR == nyB && nxR == nxB && map[nyR][nxR] != 'O'){
                        int gapY = red.y - blue.y;
                        int gapX = red.x - blue.x;

                        switch (a){
                            //상,하,좌,우
                            case 0:
                                if(gapY > 0) nyR -= Y[a];
                                else nyB -= Y[a];
                                break;
                            case 1:
                                if(gapY > 0) nyB -= Y[a];
                                else nyR -= Y[a];
                                break;
                            case 2:
                                if(gapX > 0) nxR -= X[a];
                                else nxB -= X[a];
                                break;
                            case 3:
                                if(gapX > 0) nxB -= X[a];
                                else nxR -= X[a];
                                break;
                        }
                    }
                    Point nRed = new Point(nyR, nxR);
                    Point nBlue = new Point(nyB, nxB);
                    Q.add(new Biz(nRed, nBlue, move + 1));
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Point[] biz = new Point[2];

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for(int i=1; i<=r; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=c; j++) {
                map[i][j] = str[j - 1];

                if(map[i][j] == 'R')
                    biz[0] = new Point(i, j);
                if(map[i][j] == 'B')
                    biz[1] = new Point(i, j);
            }
        }
        System.out.println(BFS(new Biz(biz[0], biz[1], 0)) == -1?0:1);
    }
}
