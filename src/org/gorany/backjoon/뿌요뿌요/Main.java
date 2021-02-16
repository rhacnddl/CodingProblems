package org.gorany.backjoon.뿌요뿌요;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static class Point{
        int y, x;
        public Point(int yy, int xx){
            y=yy; x=xx;
        }
    }
    static final int r = 13, c = 7;
    static char[][] map = new char[r][c];
    static boolean[][] visit;
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};

    //R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑
    static boolean BFS(){

        Queue<Point> q = new LinkedList<>();
        List<Point> list = new ArrayList<>();
        boolean flag = false;

        for(int i=1; i<r; i++)
            for(int j=1; j<c; j++){
                char current = map[i][j];
                int size = 1;

                if(!visit[i][j] && current != '.'){
                    list.add(new Point(i, j));
                    current = map[i][j];
                    visit[i][j] = true;
                    q.add(new Point(i, j));
                }

                while(!q.isEmpty()){
                    Point p = q.poll();

                    for(int a=0; a<4; a++){
                        int ny = p.y+Y[a];
                        int nx = p.x+X[a];

                        if(ny < 1 || nx < 1 || ny > r-1 || nx > c-1 || visit[ny][nx] || map[ny][nx] != current) continue;

                        visit[ny][nx] = true;
                        size++;
                        list.add(new Point(ny, nx));
                        q.add(new Point(ny, nx));
                    }
                }

                if(size >= 4) {
                    flag = true;
                    for (Point p : list)
                        map[p.y][p.x] = '.';
                }

                list.clear();
            }



        return flag;
    }
    static void down(){

        Queue<Point> Q = new LinkedList<>();

        for(int i=r-2; i>0; i--)
            for(int j=1; j<c; j++) {
                if (map[i][j] != '.')
                    Q.add(new Point(i, j));

                while(!Q.isEmpty()){
                    Point p = Q.poll();

                    char tmp = map[p.y][p.x];
                    int ny = p.y+1;
                    if(ny > r-1 || map[ny][p.x] != '.') continue;

                    map[p.y][p.x] = '.';
                    map[ny][p.x] = tmp;
                    Q.add(new Point(ny, p.x));
                }
            }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=1; i<r; i++){
            char[] arr = br.readLine().toCharArray();
            for(int j=1; j<c; j++)
                map[i][j] = arr[j-1];
        }

        int score = 0;
        while(true){
            visit = new boolean[r][c];
            if(!BFS()) break;
            score++;
            down();

            for(int i=1; i<r; i++){
                for(int j=1; j<c; j++)
                    System.out.print(map[i][j]);
                System.out.println();
            }
            System.out.println("-------------");
        }
        System.out.println(score);
    }
}
