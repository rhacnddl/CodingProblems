package org.gorany.backjoon.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int target, dist;
        public Edge(int t, int d){
            target=t; dist=d;
        }

        @Override
        public int compareTo(Edge o) {
            return dist - o.dist;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "target=" + target +
                    ", dist=" + dist +
                    '}';
        }
    }

    static List<Edge>[] list;
    static Queue<Edge> Q = new PriorityQueue<>();
    static int[] distance;
    static final int INF = 1000000000;

    static void dijkstra(int start){

        distance[start] = 0;

        Q.add(new Edge(start, distance[start]));
        /*for(int i=0; i<list[start].size(); i++){
            Edge edge = list[start].get(i);
            distance[edge.target] = edge.dist;
            Q.add(edge);
        }*/

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            System.out.println(edge);

            int current = edge.target;
            int dist = edge.dist;

            if(distance[current] < dist) continue;

            for(int j=0; j<list[current].size(); j++){
                Edge nextE = list[current].get(j);

                int next = nextE.target;
                int nextDistance = dist + nextE.dist;
                if(distance[next] > nextDistance){
                    distance[next] = nextDistance;
                    Q.add(new Edge(next, nextDistance));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        distance = new int[v+1];
        list = new ArrayList[v+1];
        for(int i=1; i<=v; i++)
            list[i] = new ArrayList<>();

        int start = Integer.parseInt(br.readLine());

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int home = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[home].add(new Edge(target, weight));
        }

        for(int i=1; i<=v; i++)
            distance[i] = INF;

        dijkstra(start);

        for(int i=1; i<=v; i++)
            if(distance[i] != INF)
                System.out.println(distance[i]);
            else
                System.out.println("INF");
    }
}
/*
5 6
        1
        5 1 1
        1 2 2
        1 3 3
        2 3 4
        2 4 5
        3 4 6*/
