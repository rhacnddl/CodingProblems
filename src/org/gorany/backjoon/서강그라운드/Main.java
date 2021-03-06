package org.gorany.backjoon.서강그라운드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int tg, dist;
        public Edge(int t, int d){
            tg=t;
            dist=d;
        }

        @Override
        public int compareTo(Edge o) {
            return dist - o.dist;
        }
    }

    static final int INF = 1000000000;
    static List<Edge>[] list = new ArrayList[101];
    static int[] item = new int[101], result = new int[101];
    static int[][] d = new int[101][101];
    static int n, m, r;

    //1. N * Dijkstra
    static void Dijkstra(int s){

        Queue<Edge> Q = new PriorityQueue<>();
        d[s][s] = 0;

        Q.add(new Edge(s, 0));

        while(!Q.isEmpty()){
            Edge e = Q.poll();
            int cur = e.tg;
            int dist = e.dist;

            if(d[cur][s] < dist) continue;

            result[s] += item[cur];

            for(Edge next:list[cur]){
                int nDist = dist + next.dist;

                if(d[next.tg][s] > nDist && nDist <= m){
                    d[next.tg][s] = nDist;
                    Q.add(new Edge(next.tg, nDist));
                }
            }
        }
    }

    //2. Floyd Warshall

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); //지역개수
        m = Integer.parseInt(st.nextToken()); //수색범위
        r = Integer.parseInt(st.nextToken()); //길의개수

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            Arrays.fill(d[i], INF);
            list[i] = new ArrayList<>();
            item[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int tg = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list[h].add(new Edge(tg, d));
            list[tg].add(new Edge(h, d));
        }

        int max = 0;
        for(int i=1; i<=n; i++) {
            Dijkstra(i);
            max = Math.max(max, result[i]);
        }
        System.out.println(max);
    }
}