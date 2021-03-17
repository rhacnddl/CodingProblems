package org.gorany.backjoon.전기가부족해;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int h, tg, d;
        public Edge(int home, int target, int dist){
            h=home;
            tg=target;
            d=dist;
        }
        @Override
        public int compareTo(Edge o) {
            return d - o.d;
        }
    }

    static Set<Integer> set = new HashSet<>();
    static List<Edge> list = new ArrayList<>();
    static boolean[] power = new boolean[1001];
    static int[] parent = new int[1001];

    static int getParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }
    static boolean findParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        if(set.contains(a) && set.contains(b)) return true;

        return a == b;
    }
    static void unionParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        if(!set.contains(a) && !set.contains(b)){
            if(a < b)
                parent[b] = a;
            else
                parent[a] = b;
        }
        else if(set.contains(a) && !set.contains(b))
            parent[b] = a;
        else if(!set.contains(a) && set.contains(b))
            parent[a] = b;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++) parent[i] = i;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++) set.add(Integer.parseInt(st.nextToken()));

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int tg = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list.add(new Edge(h, tg, d));
            list.add(new Edge(tg, h, d));
        }

        Collections.sort(list);

        int sum = 0;
        for(Edge e: list){
            if(!findParent(e.h, e.tg)){
                unionParent(e.h, e.tg);
                sum += e.d;
            }
        }
        System.out.println(sum);
    }
}