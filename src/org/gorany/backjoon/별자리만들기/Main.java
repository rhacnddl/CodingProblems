package org.gorany.backjoon.별자리만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        double y, x;
        public Point(double yy, double xx){
            y=yy; x=xx;
        }
    }
    static class Edge implements Comparable<Edge>{
        double d;
        int h, tg;
        public Edge(int home, int t, double dist){
            h=home; tg=t; d=dist;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "d=" + d +
                    ", h=" + h +
                    ", tg=" + tg +
                    '}';
        }

        @Override
        public int compareTo(Edge o) {
            if(d > o.d) return 1;
            else if(d < o.d) return -1;
            return 0;
        }
    }
    static final int N = 102;
    static int n;

    static Point[] v = new Point[N];
    static List<Edge> list = new ArrayList<>();

    /* Union-Find */
    static int[] parent = new int[N];
    static int getParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }
    static boolean findParent(int a, int b){
        a = getParent(a);
        b = getParent(b);
        return a == b?true:false;
    }
    static void unionParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        if(a > b) parent[a] = b;
        else parent[b] = a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());

            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            v[i] = new Point(y, x);
            parent[i] = i;
        }

        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++)
                if(i != j)
                    list.add(new Edge(i, j, Math.sqrt( Math.pow((v[i].y - v[j].y), 2) + Math.pow((v[i].x - v[j].x), 2) )));

        Collections.sort(list);

        list.stream().forEach(System.out::println);

        double sum = 0.0;
        for(Edge edge:list)
            if(!findParent(edge.h, edge.tg)){
                sum += edge.d;
                unionParent(edge.h, edge.tg);
            }
        System.out.println(Math.round(sum * 100)/100.0);
    }
}