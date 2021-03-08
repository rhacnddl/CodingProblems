package org.gorany.backjoon.열쇠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point{
        int y, x;
        public Point(int yy, int xx){
            y=yy; x=xx;
        }
    }
    static char[][] map;
    static int[][] visit;
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static int max, r, c, key;

    static void BFS(){

        Queue<Point> Q = new LinkedList<>();

        visit[0][0] = key;
        Q.add(new Point(0, 0));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                if(ny < 0 || nx < 0 || ny > r+1 || nx > c+1 || map[ny][nx] == '*' || visit[ny][nx] == key) continue;

                char c = map[ny][nx];
                visit[ny][nx] = key;

                if(c >= 'A' && c <= 'Z') {
                    if((key & (1 << c - 65) ) == 0) continue;
                }
                else if(c >= 'a' && c <= 'z')
                    key |= (1 << c - 97);
                else if(c == '$')
                    max++;

                map[ny][nx] = '.';
                Q.add(new Point(ny, nx));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            key = 0;
            max = 0;
            map = new char[102][102];
            visit = new int[102][102];

            for(int a=0; a<=r+1; a++)
                for(int b=0; b<=c+1; b++)
                    visit[a][b] = -1;

            for(int a=1; a<=r; a++){
                char[] str = br.readLine().toCharArray();
                for(int b=1; b<=c; b++)
                    map[a][b] = str[b - 1];
            }

            String keys = br.readLine();
            if(!keys.equals("0")) {
                char[] keys_ = keys.toCharArray();
                for(int a=0; a<keys_.length; a++)
                    key |= (1 << keys_[a] - 97);
            }

            BFS();
            System.out.println(max);
        }
    }
}