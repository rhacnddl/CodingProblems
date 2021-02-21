package org.gorany.backjoon.중량제한;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int tg, d;
        public Edge(int target, int dist){
            tg=target; d=dist;
        }

        @Override
        public int compareTo(Edge o) {
            return d-o.d;
        }
    }
    static List<Edge>[] list = new ArrayList[10001];
    static boolean[] visit = new boolean[10001];
    static int v1, v2;
    static int max = 0;

    static void Dijkstra(){

    }
    /*static void DFS(int v, int val){

        if(v == v2)
            max = Math.max(max, val);
        else
            visit[v] = true;

        System.out.println("현재 정점: " + v + " 값은 : " + val + " max : " + max);

        for(Edge next:list[v])
            if(!visit[next.tg])
                DFS(next.tg, Math.min(val, next.d));
    }*/
    /*static void BFS(){
        Queue<Edge> Q = new LinkedList<>();
        visit[v1] = true;
        Q.add(new Edge(v1, 1000000001));

        while(!Q.isEmpty()){
            Edge e = Q.poll();

            int current = e.tg;
            int val = e.d;

            if(current == v2)
                max = Math.max(val, max);

            for(Edge edge : list[current]){
                if(!visit[edge.tg]){

                    if(edge.tg != v2)
                        visit[edge.tg] = true;

                    Q.add(new Edge(edge.tg, Math.min(val, edge.d)));
                }
            }
        }
    }*/

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++) list[i] = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Edge(b, c));
            list[b].add(new Edge(a, c));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        //DFS(v1, 1000000001);
        //BFS();

        System.out.println(max);
    }
}
