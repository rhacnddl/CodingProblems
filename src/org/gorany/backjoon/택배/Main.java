package org.gorany.backjoon.택배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int tg, d;
        public Edge(int t, int dt){
            tg = t;
            d = dt;
        }
        @Override
        public int compareTo(Edge o) {
            return d - o.d;
        }
    }
    static final int INF = 1000000000;
    static int[][] distance = new int[201][201], table = new int[201][201];
    static List<Edge>[] list = new ArrayList[201];

    static void Dijkstra(int start){

        Queue<Edge> pq = new PriorityQueue<>();
        distance[start][start] = 0;
        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()){
            Edge edge = pq.poll();

            int current = edge.tg;
            int d = edge.d;

            if(distance[start][current] < d) continue;

            for(Edge e : list[current]){
                int next = e.tg;
                int nextD = d + e.d;

                if(distance[start][next] > nextD){
                    distance[start][next] = nextD;
                    if(table[start][current] != 0)
                        table[start][next] = table[start][current];
                    else
                        table[start][next] = next;
                    pq.add(new Edge(next, nextD));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
            for(int j=1; j<=n; j++)
                distance[i][j] = INF;
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int home = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[home].add(new Edge(target, weight));
            list[target].add(new Edge(home, weight));
        }

        for(int i=1; i<=n; i++)
            Dijkstra(i);

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++)
                if(i != j)
                    System.out.print(table[i][j] + " ");
                else
                    System.out.print("- ");
            System.out.println();
        }
    }
}
