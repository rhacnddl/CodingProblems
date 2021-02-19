package org.gorany.backjoon.지름길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int tg, d;
        public Edge(int tar, int dt){
            tg=tar; d=dt;
        }
        @Override
        public int compareTo(Edge o) {
            return d - o.d;
        }
    }
    static final int N = 10001, INF = 1000000000;
    static List<Edge>[] list = new ArrayList[N];
    static int[] distance = new int[N];

    static void Dijkstra(){

        Queue<Edge> Q = new PriorityQueue<>();
        Q.add(new Edge(0, 0));

        while(!Q.isEmpty()){
            Edge e = Q.poll();
            int cur = e.tg;
            int d = e.d;

            if(distance[cur] < d) continue;

            for(Edge edge : list[cur]){
                int next = edge.tg;
                int nextD = d + edge.d;

                if(distance[next] > nextD){
                    distance[next] = nextD;
                    Q.add(new Edge(next, nextD));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        list[0] = new ArrayList<>();
        for(int i=1; i<N; i++){
            list[i] = new ArrayList<>();
            list[i-1].add(new Edge(i, 1));
            distance[i] = INF;
        }

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int tg = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list[h].add(new Edge(tg, d));
        }

        Dijkstra();

        System.out.println(distance[m]);
    }
}