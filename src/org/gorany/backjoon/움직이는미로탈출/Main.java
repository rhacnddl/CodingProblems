package org.gorany.backjoon.움직이는미로탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static class Point{
        int y, x, turn;
        public Point(int yy, int xx, int t){
            y=yy; x=xx; turn = t;
        }
    }
    static boolean escape;
    static int[] Y = {-1, 1, 0, 0, -1, -1, 1, 1, 0}, X = {0, 0, -1, 1, -1, 1, 1, -1, 0};
    static char[][] map = new char[9][9];
    static boolean[][][] visit = new boolean[9][9][9];
    static Queue<Point> wallQ = new LinkedList<>(), personQ = new LinkedList<>();

    static void wallBFS(){
        boolean[][] check = new boolean[9][9];
        Queue<Point> tmpQ = new LinkedList<>();

        while(!wallQ.isEmpty()){
            Point p = wallQ.poll();

            if(p.y < 8){

                if(!check[p.y][p.x]) {
                    check[p.y + 1][p.x] = true;
                    map[p.y][p.x] = '.';
                }

                map[p.y + 1][p.x] = '#';
                tmpQ.add(new Point(p.y + 1, p.x, 0));
            }
            else
                if(!check[p.y][p.x])
                    map[p.y][p.x] = '.';

        }
        wallQ = tmpQ;
    }
    static boolean personBFS(){
        Queue<Point> tmpQ = new LinkedList<>();

        while(!personQ.isEmpty()){
            Point p = personQ.poll();

            int y = p.y;
            int x = p.x;
            int turn = p.turn;

            if(map[y][x] == '#') continue;
            else if((y == 1 && x == 8) || turn >= 8) {
                escape = true;
                break;
            }

            for(int a=0; a<9; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                if(ny < 1 || nx < 1 || ny > 8 || nx > 8 || map[ny][nx] == '#' || visit[turn + 1][ny][nx]) continue;
                if(map[ny-1][nx] == '#') continue;

                visit[turn + 1][ny][nx] = true;
                tmpQ.add(new Point(ny, nx, turn + 1));
            }
        }
        personQ = tmpQ;
        return personQ.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=1; i<=8; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=8; j++){
                map[i][j] = str[j-1];

                if(map[i][j] == '#')
                    wallQ.add(new Point(i, j, 0));
            }
        }
        visit[0][8][1] = true;
        personQ.add(new Point(8, 1, 0));

        while(!escape){
            if(personBFS()) break;
            wallBFS();

            for(int i=1; i<=8; i++){
                for(int j=1; j<=8; j++)
                    System.out.print(map[i][j]);
                System.out.println();
            }
            System.out.println("----------------------------");
        }
        System.out.println(escape? 1 : 0);
    }
}