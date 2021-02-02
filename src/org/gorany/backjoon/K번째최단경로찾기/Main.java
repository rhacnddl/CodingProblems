package org.gorany.backjoon.K번째최단경로찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int tg, d;
        public Edge(int tg, int d){
            this.tg=tg;
            this.d=d;
        }
        @Override
        public int compareTo(Edge o) {
            return d-o.d;
        }
    }
    static final int INF = 1000000000;
    static Queue<Integer>[] distance;
    static List<Edge>[] list;
    static Queue<Edge> Q = new PriorityQueue<>();

    static void Dijkstra(int start, int k){

        distance[start].add(0);
        Q.add(new Edge(start, 0));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            int current = edge.tg;
            int d = edge.d;

            for(int a=0; a<list[current].size(); a++){
                Edge nextEdge = list[current].get(a);
                int next = nextEdge.tg;
                int nextDistance = d + nextEdge.d;

                if(distance[next].size() < k){
                    distance[next].add(nextDistance * -1);
                    Q.add(new Edge(next, nextDistance));
                }
                else if((distance[next].peek() * -1) > nextDistance){
                    distance[next].poll();
                    distance[next].add(nextDistance * -1);
                    Q.add(new Edge(next, nextDistance));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        distance = new PriorityQueue[n+1];
        list = new ArrayList[n+1];

        for(int i=1; i<=n; i++) {
            distance[i] = new PriorityQueue<>(k);
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());

            int og = Integer.parseInt(st.nextToken());
            int tg = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list[og].add(new Edge(tg, d));
        }

        Dijkstra(1, k);

        for (int i = 1; i <= n; ++i){
            if (distance[i].size() == k) System.out.println(distance[i].peek() * -1);
            else System.out.println(-1);
        }

    }
}
