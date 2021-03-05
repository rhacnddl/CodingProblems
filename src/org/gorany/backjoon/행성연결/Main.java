package org.gorany.backjoon.행성연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Edge implements Comparable<Edge>{
        int h, tg, c;
        public Edge(int home, int t, int cc){
            h=home; tg=t; c=cc;
        }
        @Override
        public int compareTo(Edge o) {
            return c - o.c;
        }
    }
    static List<Edge> list = new ArrayList<>();

    static int[] parent = new int[1001];
    static int getParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }
    static boolean findParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        return a == b? true:false;
    }
    static void unionParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        if(a<b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=1; j<=n; j++){
                int cost = Integer.parseInt(st.nextToken());

                if(i == j) continue;

                list.add(new Edge(i, j, cost));
            }
            parent[i] = i;
        }

        Collections.sort(list);
        long sum = 0L;

        for(Edge e : list){
            if(!findParent(e.h, e.tg)){
                sum += e.c;
                unionParent(e.h, e.tg);
            }
        }
        System.out.println(sum);
    }
}