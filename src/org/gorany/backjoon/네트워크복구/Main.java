package org.gorany.backjoon.네트워크복구;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int tg, d;
        public Edge(int t, int dist){
            tg=t; d=dist;
        }
        @Override
        public int compareTo(Edge o) {
            return d-o.d;
        }
    }
    static final int INF = 1000000000;
    static int[] distance = new int[1001], from = new int[1001];
    static Queue<Edge> Q = new PriorityQueue<>();
    static List<Edge>[] list;
    static int n, m, cnt=0;

    static void Dijkstra(){
        distance[1] = 0;
        Q.add(new Edge(1, 0));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            int current = edge.tg;
            int d = edge.d;

            if(distance[current] < d) continue;
            cnt++;
            for(Edge e : list[current]){
                int next = e.tg;
                int nextD = d + e.d;

                if(distance[next] > nextD){

                    distance[next] = nextD;
                    from[next] = current;
                    //System.out.println("current: " + current + "  next: " + next + "   cnt: " + cnt+ "  from["+next+"]:"+from[next]);
                    Q.add(new Edge(next, nextD));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];

        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
            distance[i] = INF;
            from[i] = i;
        }

        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int tg = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list[h].add(new Edge(tg, d));
            list[tg].add(new Edge(h, d));
        }
        Dijkstra();
        System.out.println(cnt-1);

        for(int i=2; i<=n; i++)
            System.out.println(from[i] + " " + i);

        /*for(int i=1; i<=n; i++)
            System.out.print(distance[i] + " ");
        System.out.println();
        for(int i=1; i<=n; i++)
            System.out.print(from[i] + " ");*/
    }
}
