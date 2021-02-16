package org.gorany.backjoon.전력난;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    static class Edge implements Comparable<Edge>{
        int h, tg, d;
        public Edge(int home, int t, int di){
            h=home; tg=t; d=di;
        }
        @Override
        public int compareTo(Edge o) {
            return d-o.d;
        }
    }
    static final int N = 200000;
    static List<Edge> list;

    static int[] parent;
    static int getParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }
    static boolean findParent(int a, int b){
        a = getParent(a);
        b = getParent(b);
        return (a==b)?true:false;
    }
    static void unionParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if(v == 0 && e == 0) break;

            parent = new int[N+1];
            list = new ArrayList<>();
            IntStream.range(0, v).forEach(i-> parent[i] = i);

            int cost = 0;
            for(int i=0; i<e; i++){
                st = new StringTokenizer(br.readLine());

                int home = Integer.parseInt(st.nextToken());
                int target = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());
                cost += dist;

                list.add(new Edge(home, target, dist));
            }

            Collections.sort(list);

            int sum = 0;
            for(Edge edge : list)
                if(!findParent(edge.h, edge.tg)){
                    sum += edge.d;
                    unionParent(edge.h, edge.tg);
                }

            System.out.println("cost: " + cost + " sum : " + sum);
            System.out.println(cost - sum);
        }
    }
}
