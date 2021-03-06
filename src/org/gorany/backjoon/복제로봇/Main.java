package org.gorany.backjoon.복제로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point{
        int y, x, move, idx;
        public Point(int yy, int xx, int m, int i){
            y=yy;
            x=xx;
            move = m;
            idx=i;
        }
    }
    static class Edge implements Comparable<Edge>{
        int h, tg, move;
        public Edge(int home, int t, int m){
            h=home;
            tg=t;
            move=m;
        }

        @Override
        public int compareTo(Edge o) {
            return move - o.move;
        }
    }

    static boolean[][][] visit = new boolean[251][251][251];
    static List<Edge> list = new ArrayList<>();
    static char[][] map = new char[51][51];
    static int[][] idxTable = new int[51][51];
    static int n;
    static boolean flag;

    static int[] parent = new int[251], Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};

    static int getParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }
    static boolean findParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        return a == b? true:false;
    }
    static void union(int a, int b){
        a = getParent(a);
        b = getParent(b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    static void BFS(Point key){

        Queue<Point> Q = new LinkedList<>();

        int idx = key.idx;
        visit[key.y][key.x][idx] = true;
        Q.add(new Point(key.y, key.x, key.move, idx));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;
            int move = p.move;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                if(ny < 1 || nx < 1 || ny > n || nx > n || visit[ny][nx][idx] || map[ny][nx] == '1') continue;

                visit[ny][nx][idx] = true;

                if(map[ny][nx] == 'S') {
                    list.add(new Edge(idx, 0, move + 1));
                    flag = true;
                }
                else if(map[ny][nx] == 'K')
                    list.add(new Edge(idx, idxTable[ny][nx], move + 1)); //idxTable은 방문한 K가 몇번 째 K인지 저장

                Q.add(new Point(ny, nx, move + 1, idx));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Point[] keys = new Point[m+1];

        for(int i=1, k=1; i<=n; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=n; j++){
                map[i][j] = str[j-1];

                if(map[i][j] == 'K') {
                    keys[k] = new Point(i, j, 0, k);
                    idxTable[i][j] = k++;
                }
            }
        }

        for(int i=1; i<=m; i++) {
            parent[i] = i;
            flag = false;

            BFS(keys[i]);
            if(!flag){ //K -> S 못가면 모든 키를 못찾는 것 이다.
                System.out.println(-1);
                System.exit(0);
            }
        }

        Collections.sort(list);

        int sum = 0;
        for(Edge e:list)
            if(!findParent(e.h, e.tg)){
                sum += e.move;
                union(e.h, e.tg);
            }
        System.out.println(sum);
    }
}