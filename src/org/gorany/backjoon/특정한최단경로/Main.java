package org.gorany.backjoon.특정한최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int target, dist;
        public Edge(int t, int d){
            target = t;
            dist = d;
        }
        @Override
        public int compareTo(Edge o) {
            return dist - o.dist;
        }
    }
    static final int INF = 1000000000;
    static int v, e, v1, v2;
    static Queue<Edge> Q = new PriorityQueue<>();
    static List<Edge>[] list;
    static int[][] distance;

    static void Dijkstra(int start){

        distance[start][start] = 0;
        Q.add(new Edge(start, 0));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            int current = edge.target;
            int dist = edge.dist;

            if(distance[start][current] < dist) continue;

            for(int i=0; i<list[current].size(); i++){
                Edge edge_ = list[current].get(i);

                int next = edge_.target;
                int nextDist = dist + edge_.dist;
                if(distance[start][next] > nextDist){
                    distance[start][next] = nextDist;
                    Q.add(new Edge(next, nextDist));
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
        distance = new int[v+1][v+1];

        for(int i=1; i<=v; i++)
            list[i] = new ArrayList<>();

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());

            int home = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list[home].add(new Edge(target, dist));
            list[target].add(new Edge(home, dist));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        for(int i=1; i<=v; i++)
            for(int j=1; j<=v; j++)
                distance[i][j] = INF;

        Dijkstra(1);
        Dijkstra(v1);
        Dijkstra(v2);

        for(int i=1; i<=v; i++){
            for(int j=1; j<=v; j++)
                System.out.print(distance[i][j] + " ");
            System.out.println();
        }

        int min = Math.min(
                distance[1][v1] + distance[v1][v2] + distance[v2][v]
                , distance[1][v2] + distance[v2][v1] + distance[v1][v]);

        if(min >= INF) System.out.println(-1);
        else System.out.println(min);
    }
}