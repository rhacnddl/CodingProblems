package org.gorany.backjoon.최소스패닝트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class EdgeComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge e1, Edge e2) {
            int w1 = e1.w;
            int w2 = e2.w;

            if(w1 < w2) return -1;
            else if(w1 > w2) return 1;
            else return 0;
        }
    }
    static class Edge{
        int[] v = new int[2];
        int w;

        public Edge(int v1, int v2, int w){
            this.v[0] = v1;
            this.v[1] = v2;
            this.w = w;
        }
    }

    static int getParent(int[] parent, int x){
        if(parent[x] == x) return x;
        return getParent(parent, parent[x]);
    }
    static void unionParent(int[] parent, int a, int b){
        a = getParent(parent, a);
        b = getParent(parent, b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }
    static boolean findParent(int[] parent, int a, int b){
        a = getParent(parent, a);
        b = getParent(parent, b);
        return a == b?true:false;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[e];
        int[] parent = new int[v+1];

        for(int i=1; i<=v; i++)
            parent[i] = i;

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(v1, v2, w);
        }
        EdgeComparator comparator = new EdgeComparator();
        List<Edge> list = Arrays.asList(edges.clone());
        list.sort(comparator);

        int sum = 0;
        for(int i=1; i<=e; i++){
            Edge edge = list.get(i-1);

            if(!findParent(parent, edge.v[0], edge.v[1])) {
                sum += edge.w;
                unionParent(parent, edge.v[0], edge.v[1]);
            }
        }
        System.out.println(sum);
    }
}
