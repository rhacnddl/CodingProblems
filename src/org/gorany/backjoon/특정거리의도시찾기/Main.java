package org.gorany.backjoon.특정거리의도시찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge{
        int tg, d;
        public Edge(int t, int dist){
            tg=t; d=dist;
        }
    }
    static final int INF = 1000000000;
    static int n, m, k;
    static int[] distance = new int[300001];
    static List<Edge>[] list = new ArrayList[300001];

    static void Dijkstra(int start){
        Queue<Edge> Q = new LinkedList<>();

        distance[start] = 0;
        Q.add(new Edge(start, 0));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            int current = edge.tg;
            int d = edge.d;

            if(distance[current] < d || d > k) continue;

            for(Edge e:list[current]){
                int next = e.tg;
                int nextD = d + e.d;

                if(distance[next] > nextD){
                    distance[next] = nextD;
                    Q.add(new Edge(next, nextD));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> l = new ArrayList<>();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
            distance[i] = INF;
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            list[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()), 1));
        }

        Dijkstra(x);

        for(int i=1; i<=n; i++)
            if(distance[i] == k) l.add(i);
        Collections.sort(l);

        if(l.size() == 0) System.out.println(-1);
        else
            for(int a : l)
                System.out.println(a);

    }
}
