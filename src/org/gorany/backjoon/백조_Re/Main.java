package org.gorany.backjoon.백조_Re;

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
    static Queue<Point> waterQ = new LinkedList<>();
    static int r, c;
    static boolean[][] visit;
    static char[][] map; //입력받은 지도
    static Point[] swan = new Point[2]; //백조의 위치를 저장
    static int[] Y = {-1,1,0,0}, X = {0,0,-1,1};
    static boolean find;

    static int[] parent;
    static int getParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }
    static boolean findParent(int a, int b){
        a = getParent(a);
        b = getParent(b);
        return a==b?true:false;
    }
    static void unionParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    static void melt(int y, int x){

        for(int a=0; a<4; a++){
            int ny = y+Y[a];
            int nx = x+X[a];

            if(ny <= 0 || nx <= 0 || ny > r || nx > c || visit[ny][nx]) continue;

            //union-find
            /*if(!findParent(c*(y-1)+x, c*(ny-1)+nx))
                unionParent(c*(y-1)+x, c*(ny-1)+nx);*/

            if(map[ny][nx] == 'X' && !visit[ny][nx]) {
                if(!findParent(c*(y-1)+x, c*(ny-1)+nx))
                    unionParent(c*(y-1)+x, c*(ny-1)+nx);

                map[ny][nx] = '.';
                waterQ.add(new Point(ny, nx));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = 1501, a = 0;

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        visit = new boolean[N][N];
        map = new char[N][N];
        parent = new int[(N+1) * (N+1)]; //union-Find

        for (int i = 1; i <= r; i++) {
            char[] str = br.readLine().toCharArray();
            for (int j = 1; j <= c; j++) {

                parent[(i-1)*c+j] = (i-1)*c+j;

                map[i][j] = str[j - 1];
                /*if((i==1&&j==1)||(i==r&&j==c)) map[i][j] = 'L';
                else map[i][j] = 'X';*/

                if (map[i][j] == 'L') {
                    swan[a++] = new Point(i, j);
                    waterQ.add(new Point(i, j));

                } else if (map[i][j] == '.') {
                    waterQ.add(new Point(i, j));
                }
            }
        }

        int swan0 = c * (swan[0].y - 1) + swan[0].x;
        int swan1 = c * (swan[1].y - 1) + swan[1].x;

        int day = 0;
        while(true){
            if(findParent(c * (swan[0].y - 1) + swan[0].x, c * (swan[1].y - 1) + swan[1].x)) break;
            int waterQSize = waterQ.size();

            for(int i=0; i<waterQSize; i++){
                Point p = waterQ.poll();
                visit[p.y][p.x] = true;
                melt(p.y, p.x);
            }

            //if(find) break;

            day++;
            for(int i=1; i<=r; i++){
                for(int j=1; j<=c; j++)
                    System.out.print(map[i][j]);
            System.out.println();
            }

            /*for(int i=1; i<=r; i++){
                for(int j=1; j<=c; j++)
                    System.out.print(parent[c*(i-1)+j] + " ");
                System.out.println();
            }*/
        }
        System.out.println(day);
    }
}
