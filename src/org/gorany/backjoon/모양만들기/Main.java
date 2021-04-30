package org.gorany.backjoon.모양만들기;

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
    static int r, c, max;
    static int[] Y = {-1,1,0,0}, X = {0,0,-1,1};
    static int[][] map = new int[1001][1001];
    static boolean[][] visit = new boolean[1001][1001];

    static Map<Integer, Integer> hashMap = new HashMap<>(); //각 덩어리의 id와 size를 갖음
    static Queue<Point> Q = new LinkedList<>();

    static int DFS(int y, int x, int id){

        visit[y][x] = true;
        map[y][x] = id;
        int result = 1;

        for(int a=0; a<4; a++){
            int ny = y+Y[a];
            int nx = x+X[a];

            if(ny < 1 || nx < 1 || ny > r || nx > c || visit[ny][nx] || map[ny][nx] == 0) continue;
            result += DFS(ny, nx, id);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for(int i=1; i<=r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)
                    Q.add(new Point(i, j));
            }
        }
        /* DFS를 통한 각 덩어리(모양)의 인덱스를 부여하고, 그 크기를 구한다. */
        int id = 1;
        for(int i=1; i<=r; i++)
            for(int j=1; j<=c; j++)
                if(map[i][j] != 0 && !visit[i][j]) {
                    int size = DFS(i, j, id);
                    hashMap.put(id++, size);
                }
        /* Queue에 넣은 0을 하나씩 꺼내 1로 변경했을 때 인접한 덩어리들과 크기를 합쳐본다. */
        Set<Integer> set = new HashSet<>();//set을 이용해 동일한 덩어리의 중복 방지
        while(!Q.isEmpty()){
            Point p = Q.poll();

            int value = 1;
            for(int a=0; a<4; a++){
                int ny = p.y+Y[a];
                int nx = p.x+X[a];

                if(ny < 1 || nx < 1 || ny > r || nx > c || map[ny][nx] == 0) continue;

                if(set.add(map[ny][nx]))
                    value += hashMap.get(map[ny][nx]);
            }
            max = Math.max(max, value);
            set.clear();
        }
        System.out.println(max);
    }
}