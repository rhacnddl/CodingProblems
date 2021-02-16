package org.gorany.backjoon.도로포장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int tg, cnt;
        long d;
        public Edge(int t, long dt, int cnt){
            tg = t;
            d = dt;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(Edge o) {
            if(d > o.d) return 1;
            else if(d < o.d) return -1;
            return 0;
        }
    }
    static final long INF = Long.MAX_VALUE;
    static final int N = 10001;
    static List<Edge>[] list = new ArrayList[N];
    static long[][] distance = new long[N][21];

    static void Dijkstra(int start, int k){

        Queue<Edge> pq = new PriorityQueue<>();
        distance[start][0] = 0;
        pq.add(new Edge(start, 0, 0));

        while (!pq.isEmpty()){
            Edge edge = pq.poll();
            int current = edge.tg;
            long d = edge.d;
            int cnt = edge.cnt;

            if(distance[current][cnt] < d) continue;

            for(Edge e : list[current]){
                int next = e.tg;
                long nextD = d + e.d;

                if(distance[next][cnt] > nextD){
                    distance[next][cnt] = nextD;
                    pq.add(new Edge(next, nextD, cnt));
                }

                if(cnt < k && distance[next][cnt + 1] > d){
                    distance[next][cnt + 1] = d;
                    pq.add(new Edge(next, d, cnt + 1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
            Arrays.fill(distance[i], INF);
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int home = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list[home].add(new Edge(target, dist, 0));
            list[target].add(new Edge(home, dist, 0));
        }

        Dijkstra(1, k);

        long min = INF;
        for(int i=0; i<=k; i++)
            min = Math.min(min, distance[n][i]);

        System.out.println(min);

        for(int j=1; j<=n; j++){
            for(int i=1; i<=k; i++)
                System.out.print(distance[j][i] + " ");
            System.out.println();
        }

    }
}
/*
9 14 1
1 2 1
1 3 2
3 5 4
2 4 3
5 8 5
8 9 95
9 6 100
4 6 6
1 7 100
5 7 7
4 7 8
8 7 4
6 7 1
7 9 1000
 */