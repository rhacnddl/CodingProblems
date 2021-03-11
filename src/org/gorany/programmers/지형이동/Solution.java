package org.gorany.programmers.지형이동;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    static class Edge implements Comparable<Edge>{
        int h, tg, d;
        public Edge(int home, int target, int dist){
            h=home;
            tg=target;
            d=dist;
        }
        @Override
        public int compareTo(Edge o) {
            return d - o.d;
        }
    }

    static List<Edge> list = new ArrayList<>();

    static int[] parent = new int[90001], Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static boolean[][] visit = new boolean[301][301];

    static int getParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }
    static boolean findParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        return a == b? true: false;
    }
    static void union(int a, int b){
        a = getParent(a);
        b = getParent(b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static int solution(int[][] land, int height) {

        int size = land[0].length;

        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++) {
                visit[i][j] = true;
                int curH = land[i][j];
                int home = (i * size) + j;

                for (int a = 0; a < 4; a++) {
                    int ny = i + Y[a];
                    int nx = j + X[a];

                    if (ny < 0 || nx < 0 || ny > size - 1 || nx > size - 1 || visit[ny][nx]) continue;

                    int gap = Math.abs(curH - land[ny][nx]);

                    list.add(new Edge(home, (ny * size) + nx, (gap > height) ? gap : 0));
                }
            }

        for(int i=0; i<size*size; i++)
            parent[i] = i;

        Collections.sort(list);

        int answer = 0;
        for(Edge e : list)
            if(!findParent(e.h, e.tg)){
                answer += e.d;
                union(e.h, e.tg);
            }

        return answer;
    }

    public static void main(String[] args) {
        int[][] land = {{10, 11, 10, 11}, {2, 21, 20, 10}, {1, 20, 21, 11}, {2, 1, 2, 1}};
        //int[][] land = {{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}};
        int height = 1;

        System.out.println(solution(land, height));
    }
}