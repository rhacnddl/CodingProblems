package org.gorany.backjoon.촌수계산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int tg, d;
        public Edge(int t, int dist){
            tg=t; d=dist;
        }
        @Override
        public int compareTo(Edge o) {
            return d-o.d;
        }
    }
    static final int INF = 1000000000;
    static Queue<Edge> Q = new PriorityQueue<>();
    static List<Edge>[] list = new ArrayList[101];
    static int[] distance = new int[101];
    static int n;

    static void Dijkstra(int start){

        distance[start] = 0;
        Q.add(new Edge(start, 0));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            int current = edge.tg;
            int d = edge.d;
            if(distance[current] < d) continue;

            for(Edge e:list[current]){
                int next = e.tg;
                int nextD = d + e.d;
                if(distance[next] > nextD){
                    distance[next] = nextD;
                    Q.add(new Edge(next, nextD));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
            distance[i] = INF;
        }

        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int tg = Integer.parseInt(st.nextToken());

            list[h].add(new Edge(tg, 1));
            list[tg].add(new Edge(h, 1));
        }
        Dijkstra(start);

        System.out.println(distance[end]!=INF?distance[end]:-1);
    }
}
