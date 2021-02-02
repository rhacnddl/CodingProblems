package org.gorany.backjoon.미확인도착지;

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
    static int[] distance[], arrive;
    static int v, e;

    static void Dijkstra(int start, int idx) {
        //시작정점에서의 최단거리
        distance[idx][start] = 0;
        Q.add(new Edge(start, 0));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            int current = edge.tg;
            int d = edge.d;

            if(distance[idx][current] < d) continue;

            for(Edge e : list[current]){
                int next = e.tg;
                int nextD = d + e.d;

                if(distance[idx][next] > nextD){
                    distance[idx][next] = nextD;
                    Q.add(new Edge(next, nextD));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for(int a=0; a<t; a++) {

            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            int arrival = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            distance = new int[2][v+1];
            arrive = new int[arrival];
            list = new ArrayList[v+1];
            for(int i=1; i<=v;i++)
                list[i] = new ArrayList<>();

            for(int i=0; i<2; i++)
                for(int j=1; j<=v; j++)
                    distance[i][j] = INF;

            for(int i=0; i<e; i++){
                st = new StringTokenizer(br.readLine());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                list[b].add(new Edge(c, d));
                list[c].add(new Edge(b, d));
            }

            for(int i=0; i<arrival; i++)
                arrive[i] = Integer.parseInt(br.readLine());

            int min = INF;
            int bridge = -1;
            for (Edge e:list[g])
                if(e.tg == h) bridge = e.d;

            Dijkstra(start, 0);

            if(distance[0][g] < distance[0][h]){
                min = g;
                Dijkstra(h, 1);
            }

            else{
                min = h;
                Dijkstra(g, 1);
            }

            Arrays.sort(arrive);
            for(int idx:arrive)
                if(distance[0][idx] == (distance[0][min] + bridge + distance[1][idx]))
                    System.out.print(idx + " ");
            System.out.println();


            /*System.out.println("# 시작 정점에서의 최단 거리");
            Arrays.stream(distance[0]).skip(1).forEach(i->System.out.print(i + " "));
            System.out.println();
            System.out.println("# 중간 정점에서의 최단 거리");
            Arrays.stream(distance[1]).skip(1).forEach(i->System.out.print(i + " "));
            System.out.println();
            System.out.println("# Bridge: " + bridge);*/
        }
    }
}
