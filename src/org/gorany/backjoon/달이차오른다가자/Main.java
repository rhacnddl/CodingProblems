package org.gorany.backjoon.달이차오른다가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point{
        int y, x, cnt, key;
        public Point(int yy, int xx, int c, int k){
            y=yy; x=xx; cnt=c; key = k;
        }
    }
    static int r, c, min = 1000000000;
    static boolean[][][] visit = new boolean[64][51][51];
    static char[][] map = new char[51][51];
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};

    static void BFS(Point start){

        Queue<Point> Q = new LinkedList<>();

        visit[0][start.y][start.x] = true;
        Q.add(new Point(start.y, start.x, 0, 0));

        while(!Q.isEmpty()){
            Point p = Q.poll();

            int cnt = p.cnt;

            for(int a=0; a<4; a++){
                int ny = p.y+Y[a];
                int nx = p.x+X[a];
                int nKey = p.key;

                if(ny < 1 || nx < 1 || ny > r || nx > c || visit[nKey][ny][nx] || map[ny][nx] == '#') continue;

                if(map[ny][nx] >= 'A' && map[ny][nx] <= 'F'){
                    if((nKey & (1 << map[ny][nx] - 'A')) != (1 << map[ny][nx] - 'A')) continue;
                    System.out.println(map[ny][nx] + "가 열렸습니다." + (cnt+1));
                }
                else if(map[ny][nx] >= 'a' && map[ny][nx] <= 'f') {
                    //몇번째 비트를 set 시켜야하는지?
                    int whatKey = map[ny][nx] - 'a'; // map[ny][nx] - 97
                    nKey |= (1 << whatKey);
                    System.out.println("key " + map[ny][nx] + "를 습득하엿습니다." + (cnt+1) + " next key : " + Integer.toBinaryString(nKey));
                }
                else if(map[ny][nx] == '1') {
                    min = Math.min(min, cnt + 1);
                }

                visit[p.key][ny][nx] = true;
                visit[nKey][ny][nx] = true;
                Q.add(new Point(ny, nx, cnt + 1, nKey));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Point start = null;

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for(int i=1; i<=r; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=c; j++){
                map[i][j] = str[j-1];

                if(map[i][j] == '0')
                    start = new Point(i, j, 0, 0);
            }
        }
        BFS(start);
        System.out.println(min != 1000000000? min:-1);
    }
}