package org.gorany.backjoon.달빛여우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int tg, d, rest;
        public Edge(int target, int dist){
            tg = target;
            d = dist;
        }
        public Edge(int target, int dist, int r){
            this(target, dist);
            rest = r;
        }

        @Override
        public int compareTo(Edge o) {
            return d - o.d;
        }
    }
    static int n, m;
    static final int INF = 2000000000;
    static List<Edge>[] list = new ArrayList[4001];
    static int[][] wolf = new int[4001][2];
    static int[] fox = new int[4001];

    //rest == 1? 짝수번째로 도착, rest == 0? 홀수번째로 도착
    static void Dijkstra_wolf(){
        Queue<Edge> pq = new PriorityQueue<>();

        wolf[1][0] = 0;
        pq.add(new Edge(1, 0, 0));

        while(!pq.isEmpty()){
            Edge e = pq.poll();
            int cur = e.tg;
            int dist = e.d;
            int rest = e.rest;

            if(wolf[cur][rest] < dist) continue;

            for(Edge nE : list[cur]){
                int next = nE.tg;
                int nDist = dist;
                int nRest = -1;

                if(rest == 1){
                    nDist += nE.d * 2;
                    nRest = 0;
                }
                else{
                    nDist += nE.d / 2;
                    nRest = 1;
                }

                if(wolf[next][nRest] > nDist){
                    wolf[next][nRest] = nDist;
                    pq.add(new Edge(next, nDist, nRest));
                }
            }
        }
    }

    static void Dijkstra_fox(){

        Queue<Edge> pq = new PriorityQueue<>();
        fox[1] = 0;

        pq.add(new Edge(1, 0));

        while(!pq.isEmpty()){
            Edge e = pq.poll();
            int cur = e.tg;
            int dist = e.d;

            if(fox[cur] < dist) continue;

            for(Edge ne:list[cur]){
                int next = ne.tg;
                int nDist = dist + ne.d;

                if(fox[next] > nDist){
                    fox[next] = nDist;

                    pq.add(new Edge(next, nDist));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
            fox[i] = INF;
            Arrays.fill(wolf[i], INF);
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[h].add(new Edge(t, w * 2));
            list[t].add(new Edge(h, w * 2));
        }
        Dijkstra_fox();
        Dijkstra_wolf();

/*        for(int i=1; i<=n; i++){
            System.out.print(fox[i] + " ");
        }
        System.out.println();

        for(int i=1; i<=n; i++){
            System.out.print(wolf[i][0] + " ");
        }
        System.out.println();
        for(int i=1; i<=n; i++){
            System.out.print(wolf[i][1] + " ");
        }
        System.out.println();*/
        int cnt = 0;
        for(int i=1; i<=n; i++)
            if(fox[i] < Math.min(wolf[i][1], wolf[i][0])) cnt++;

        System.out.println(cnt);
    }
}
