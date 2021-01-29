package org.gorany.backjoon.파티;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int target, dist;
        public Edge(int t, int d){
            target=t;
            dist=d;
        }
        @Override
        public int compareTo(Edge o) {
            return dist-o.dist;
        }
        @Override
        public String toString() {
            return "Edge{" +
                    "target=" + target +
                    ", dist=" + dist +
                    '}';
        }
    }

    static Queue<Edge> Q = new PriorityQueue<>();
    static int v, e;
    static int[][] distance;
    static final int INF = 1000000000;
    static List<Edge>[] list;

    static void dijkstra(int start){

        distance[start][start] = 0;

        Q.add(new Edge(start, 0));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();

            int current = edge.target;
            int dist = edge.dist;

            if(distance[start][current] < dist) continue;

            for(int i=0; i<list[current].size(); i++){
                Edge n_edge = list[current].get(i);

                int next = n_edge.target;
                int nextDistance = dist + n_edge.dist;
                if(distance[start][next] > nextDistance){
                    distance[start][next] = nextDistance;
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
        int end = Integer.parseInt(st.nextToken());

        distance = new int[v+1][v+1];
        list = new ArrayList[v+1];

        for(int i=1; i<=v; i++) {
            list[i] = new ArrayList<>();
            for (int j = 1; j <= v; j++)
                distance[i][j] = INF;
        }

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int home = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list[home].add(new Edge(target, dist));
        }

        for(int i=1; i<=v; i++)
            dijkstra(i);

        /*for(int i=1; i<=v; i++){
            for(int j=1; j<=v; j++)
                System.out.print(distance[i][j] + " ");
            System.out.println();
        }*/

        int max = 0;
        for(int i=1; i<=v; i++)
            max = Math.max(max, distance[i][end] + distance[end][i]);

        System.out.println(max);
    }
}
