package org.gorany.backjoon.운동;

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
    static Queue<Edge> Q = new PriorityQueue<>();
    static List<Edge>[] list;
    static final int INF = 1000000000;
    static int[][] dist;
    static int v, e;

    static void Dijkstra(int start){

        Q.add(new Edge(start, 0));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            int current = edge.tg;
            int d = edge.d;

            if(dist[start][current] < d) continue;

            for(Edge e:list[current]){
                int next = e.tg;
                int nextD = d + e.d;

                if(dist[start][next] > nextD){
                    dist[start][next] = nextD;
                    Q.add(new Edge(next, nextD));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        list = new ArrayList[v+1];
        dist = new int[v+1][v+1];

        for(int i=1; i<=v; i++) {
            list[i] = new ArrayList<>();
            for(int j=1; j<=v; j++)
                dist[i][j] = INF;
        }

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list[h].add(new Edge(t, d));
        }

        for(int i=1; i<=v; i++)
            Dijkstra(i);

        int min = INF;
        for(int i=1; i<=v; i++)
            for(int j=1; j<=v; j++)
                if(i == j)
                    min = Math.min(min, dist[i][j]);

        if(min == INF)
            System.out.println(-1);
        else
            System.out.println(min);
    }
}
