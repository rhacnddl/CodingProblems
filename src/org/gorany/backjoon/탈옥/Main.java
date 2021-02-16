package org.gorany.backjoon.탈옥;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y, x;
        public Point(int yy, int xx){
            y=yy; x=xx;
        }
    }
    static class Edge implements Comparable<Edge>{
        Point p;
        int d, from;

        public Edge(Point p, int dist, int from){
            this.p=p;
            d=dist;
            this.from = from;
        }
        @Override
        public int compareTo(Edge o) {
            return d-o.d;
        }
    }

    static boolean[][][] visit;

    static char[][] map;
    static int[][][] distance;

    static Point[] prisoner;
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};

    static final int INF = 1000000000;
    static int h, w;

    static void Dijkstra(Point p1, Point p2){

        Queue<Edge> Q = new PriorityQueue<>();

        visit[0][0][0] = true;
        visit[p1.y][p1.x][1] = true;
        visit[p2.y][p2.x][2] = true;

        distance[0][0][0] = 0;
        distance[p1.y][p1.x][1] = 0;
        distance[p2.y][p2.x][2] = 0;

        Q.add(new Edge(new Point(0, 0) ,0, 0));
        Q.add(new Edge(p1, 0, 1));
        Q.add(new Edge(p2, 0, 2));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            Point p = edge.p;
            int y = p.y;
            int x = p.x;
            int d = edge.d;
            int from = edge.from;

            if(distance[y][x][from] < d) continue;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                if(ny < 0 || nx < 0 || ny > h+1 || nx > w+1 || visit[ny][nx][from] || map[ny][nx] == '*') continue;

                int nextD = d;

                if(map[ny][nx] == '#')
                    nextD++;

                if(distance[ny][nx][from] > nextD){
                    visit[ny][nx][from] = true;
                    distance[ny][nx][from] = nextD;
                    Q.add(new Edge(new Point(ny, nx), nextD, from));
                }
            }
        }
        for(int i=0; i<=h+1; i++){
            for(int j=0; j<=w+1; j++)
                System.out.print(distance[i][j][0]==INF?"* ":distance[i][j][0] + " ");
            System.out.println();
        }
        System.out.println("------------------------------------------------");
        for(int i=0; i<=h+1; i++){
            for(int j=0; j<=w+1; j++)
                System.out.print(distance[i][j][1]==INF?"* ":distance[i][j][1] + " ");
            System.out.println();
        }
        System.out.println("------------------------------------------------");
        for(int i=0; i<=h+1; i++){
            for(int j=0; j<=w+1; j++)
                System.out.print(distance[i][j][2]==INF?"* ":distance[i][j][2] + " ");
            System.out.println();
        }
        System.out.println("------------------------------------------------");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        for(int i=0; i<k; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = 102;
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            visit = new boolean[N][N][3];
            map = new char[N][N];
            distance = new int[N][N][3];
            prisoner = new Point[2];

            int s = 0;
            for(int j=1; j<=h; j++){
                char[] str = br.readLine().toCharArray();
                for(int z=1; z<=w; z++){
                    map[j][z] = str[z-1];

                    if(map[j][z] == '$')
                        prisoner[s++] = new Point(j, z);
                }
            }
            for(int j=0; j<=h+1; j++)
                for(int z=0; z<=w+1; z++)
                    for(int x=0; x<3; x++)
                        distance[j][z][x] = INF;

            Dijkstra(prisoner[0], prisoner[1]);

            int min = INF;

            for(int a=1; a<=h; a++)
                for(int b=1; b<=w; b++){
                    if(map[a][b] == '*') continue;
                    int total = 0;
                    for(int c=0; c<3; c++)
                        total += distance[a][b][c];

                    if(total < 0) continue;

                    if(map[a][b] == '#') total -= 2;
                    min = Math.min(min, total);
                }

            System.out.println(min);
        }
    }
}
