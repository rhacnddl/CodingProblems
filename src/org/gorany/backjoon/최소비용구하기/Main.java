package org.gorany.backjoon.최소비용구하기;

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
    static int[] distance;
    static Queue<Edge> Q = new PriorityQueue<>();
    static List<Edge>[] list;

    static void dijkstra(int start){

        distance[start] = 0;
        Q.add(new Edge(start, 0));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();

            int current = edge.target;
            int dist = edge.dist;

            if(distance[current] < dist) continue;
            for(int j=0; j<list[current].size(); j++){
                Edge edge_ = list[current].get(j);

                int next = edge_.target;
                int nextDist = edge_.dist + dist;
                if(distance[next] > nextDist){
                    distance[next] = nextDist;
                    Q.add(new Edge(next, nextDist));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int v = Integer.parseInt(br.readLine());
        int e = Integer.parseInt(br.readLine());

        distance = new int[v+1];
        list = new ArrayList[v+1];

        for(int i=1; i<=v; i++)
            list[i] = new ArrayList<>();

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            list[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        for(int i=1; i<=v; i++)
            distance[i] = INF;

        dijkstra(start);

        System.out.print(distance[end]);
    }
}
