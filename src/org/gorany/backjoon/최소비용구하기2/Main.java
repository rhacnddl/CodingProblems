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
    static Queue<Edge> Q = new PriorityQueue<>();
    static List<Edge>[] list;
    static final int INF = 1000000000;
    static int[] distance, from;

    static void Dijkstra(int start, int end, int n, int m) {
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

                if(distance[next] >= nextDistance){
                    distance[next] = nextDistance;
                    from[next] = current;
                    //System.out.println("current: " + current + " from["+next+"] : " + current);
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

        from = new int[n+1];
        distance = new int[n+1];
        list = new ArrayList[n+1];
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

        Dijkstra(start, end, n, m);

        List<Integer> l = new ArrayList<>();
        int a=end;
        l.add(end);
        while(from[a] != start){
            l.add(from[a]);
            a = from[a];
        }
        l.add(start);

        System.out.println(distance[end]);
        System.out.println(l.size());
        for(int i=l.size()-1; i>=0; i--)
            System.out.print(l.get(i) + " ");
        System.out.println();

        Arrays.stream(distance).skip(1).forEach(i->System.out.print(i + " "));
        System.out.println();
        Arrays.stream(from).skip(1).forEach(i->System.out.print(i + " "));
        System.out.println();
    }
}
