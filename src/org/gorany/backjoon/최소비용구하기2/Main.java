package org.gorany.backjoon.최소비용구하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int tg, d;
        public Edge(int t, int dt){
            tg=t;
            d=dt;
        }
        @Override
        public int compareTo(Edge o) {
            return d-o.d;
        }
    }
    static List<Edge>[] list = new ArrayList[1001];
    static final int INF = 1000000000;
    static int[] distance = new int[1001], from = new int[1001];

    static void Dijkstra(int start) {
        Queue<Edge> Q = new PriorityQueue<>();
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
                int nextDistance = d + nextEdge.d;

                if(distance[next] > nextDistance){
                    distance[next] = nextDistance;

                    from[next] = current;
                    Q.add(new Edge(next, nextDistance));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++) {
            distance[i] = INF;
            from[i] = i;
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int og = Integer.parseInt(st.nextToken());
            int tg = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list[og].add(new Edge(tg, d));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Dijkstra(start);

        int tmp = end, idx = 0, cnt = 0;
        int[] result = new int[1001];

        while(true) {
            result[idx++] = tmp;
            cnt++;

            if (tmp == start) break;
            tmp = from[tmp];
        }

        System.out.println(distance[end]);
        System.out.println(cnt);
        for(int i=idx-1; i>=0; i--)
            System.out.print(result[i] + " ");
    }
}