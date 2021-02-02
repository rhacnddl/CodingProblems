package org.gorany.backjoon.KCMTravel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int tg, dist, cost;
        public Edge(int t, int c, int d){
            tg=t;
            dist=d;
            cost=c;
        }
        @Override
        public int compareTo(Edge o) {
            return dist-o.dist;
        }
    }
    static int n,m,k;
    static final int INF = 1000000000;
    static Queue<Edge> Q = new PriorityQueue<>();
    static List<Edge>[] list;
    static int[][] distance = new int[101][10001];
    static boolean[][] visit = new boolean[101][10001];

    static void Dijkstra(){
        distance[1][0] = 0;
        Q.add(new Edge(1, 0, 0));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            int current = edge.tg;
            int cost = edge.cost;
            int dist = edge.dist;

            if(distance[current][cost] < dist) continue;

            for(Edge e : list[current]){
                int next = e.tg;
                int nextDist = dist + e.dist;
                int nextCost = cost + e.cost;

                if(nextCost > m) continue;
                if(!visit[next][nextCost] || distance[next][nextCost] > nextDist){
                    visit[next][nextCost] = true;
                    distance[next][nextCost] = nextDist;
                    Q.add(new Edge(next, nextCost, nextDist));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for(int a=0; a<t; a++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            list = new ArrayList[n + 1];

            for (int i = 1; i <= n; i++)
                list[i] = new ArrayList<>();


            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int h = Integer.parseInt(st.nextToken());
                int tg = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                list[h].add(new Edge(tg, c, d));
            }
            Dijkstra();

            int res = INF;
            for (int i = 0; i <= m; i++)
                if(visit[n][i] && res > distance[n][i])
                    res = distance[n][i];

            System.out.println(res == INF?"Poor KCM":res);

            /*for (int i = 0; i <= m; i++)
                System.out.print(distance[n][i]+ " ");
            System.out.println();*/
        }
    }
}
