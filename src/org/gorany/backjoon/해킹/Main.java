package org.gorany.backjoon.해킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int tg, d;
        public Edge(int target, int dist){
            tg = target; d = dist;
        }
        @Override
        public int compareTo(Edge o) {
            return d-o.d;
        }
    }
    static List<Edge>[] list;
    static Queue<Edge> Q = new PriorityQueue<>();
    static final int INF = 1000000000;
    static int[] distance;
    static int v, e;

    static void Dijkstra(int start){

        int max = 0, cnt = 0;
        distance[start] = 0;
        Q.add(new Edge(start, 0));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            int current = edge.tg;
            int d = edge.d;

            if(distance[current] < d) continue;

            for(Edge e : list[current]){
                int next = e.tg;
                int nextD = d + e.d;

                if(distance[next] > nextD){
                    distance[next] = nextD;
                    Q.add(new Edge(next, nextD));
                }
            }
        }

        for(int i=1; i<=v; i++)
            if (distance[i] != INF) {
                max = Math.max(max, distance[i]);
                cnt++;
            }

        System.out.print(cnt + " " + max + "\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for(int a=0; a<t; a++){

            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            distance = new int[v+1];
            list = new ArrayList[v+1];
            for(int i=1; i<=v;i++) {
                list[i] = new ArrayList<>();
                distance[i] = INF;
            }

            for(int i=0; i<e; i++){
                st = new StringTokenizer(br.readLine());
                int tg = Integer.parseInt(st.nextToken());
                int h = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                list[h].add(new Edge(tg, d));
            }
            Dijkstra(start);
        }


    }
}
