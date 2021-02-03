package org.gorany.backjoon.거의최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static class Edge implements Comparable<Edge>{
        int tg, d;
        public Edge(int t, int dist){
            tg=t; d=dist;
        }
        @Override
        public int compareTo(Edge o) {
            return d - o.d;
        }
    }

    static final int INF = 100000000, N = 500;
    static ArrayList<Edge>[] list = new ArrayList[N], shortest = new ArrayList[N];
    static int[] distance;

    static void Dijkstra(int start){
        PriorityQueue<Edge> Q = new PriorityQueue<>();

        distance[start] = 0;
        Q.add(new Edge(start, 0));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            int current = edge.tg;
            int d = edge.d;

            if(distance[current] < d) continue;

            for(int a=0; a<list[current].size(); a++){
                Edge nextEdge = list[current].get(a);

                int next = nextEdge.tg;
                int nextD = nextEdge.d + d;

                if (distance[next] > nextD) {
                    shortest[next].clear();
                    shortest[next].add(new Edge(current, a));

                    distance[next] = nextD;
                    Q.add(new Edge(next, nextD));
                }
                else if (distance[next] == nextD)
                    shortest[next].add(new Edge(current, a)); //Edge의 2번째 멤버변수로 list[current]의 몇 번째 인덱스인지 저장

            }
        }
    }
    static boolean[] visit;
    static void DFS(int current, int start){

        if(current == start || visit[current]) return;
        visit[current] = true;

        for(Edge edge:shortest[current]){
            int next = edge.tg;
            int idx = edge.d;

            list[next].set(idx, new Edge(0, INF));

            DFS(next, start);
        }
    }

    static void BFS(int end){
        LinkedList<Integer> q = new LinkedList<>();
        q.add(end);

        while(!q.isEmpty()){
            /*list[current] -> [next], shortest[next] -> [current]*/
            int next = q.poll();

            for(Edge edge:shortest[next]){
                int current = edge.tg;
                int idx = edge.d;

                list[current].set(idx, new Edge(0, INF));

                q.add(current);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if(v == 0 && e == 0) break;

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            distance = new int[v];
            visit = new boolean[v];
            IntStream.range(0,v).forEach(i->{
                distance[i]=INF;
                list[i] = new ArrayList<>();
                shortest[i] = new ArrayList<>();
            });

            for(int i=0; i<e; i++){
                st = new StringTokenizer(br.readLine());

                int h = Integer.parseInt(st.nextToken());
                int tg = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                list[h].add(new Edge(tg, d));
            }

            Dijkstra(start);
            //BFS(end);
            DFS(end, start);

            IntStream.range(0,v).forEach(i->distance[i]=INF);

            Dijkstra(start);
            System.out.println(distance[end] == INF? -1 : distance[end]);
        }
    }
}