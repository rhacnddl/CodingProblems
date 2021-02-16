package org.gorany.backjoon.우주신과의교감;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point{
        int y, x;
        public Point(int yy, int xx){
            y=yy;
            x=xx;
        }
    }
    static class Edge implements Comparable<Edge>{
        int h, tg;
        double d;
        public Edge(int home, int target, double dist){
            h = home;
            tg = target;
            d = dist;
        }

        @Override
        public int compareTo(Edge o) {
            if(d > o.d) return 1;
            else if(d < o.d) return -1;
            return 0;
        }
    }
    static List<Point> vList = new ArrayList<>();
    static List<Edge> list = new ArrayList<>();
    static int[] parent = new int[1001];

    static int getParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }
    static boolean findParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        return a==b?true:false;
    }
    static void unionParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    static double getDist(int y1, int x1, int y2, int x2){

        return Math.sqrt( Math.pow(y1 - y2, 2.0) + Math.pow(x1 - x2, 2.0));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            vList.add(new Point(y, x));

            parent[i] = i;
        }

        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int home = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            unionParent(home, target);
        }

        for(int i=0; i<vList.size(); i++){
            Point p1 = vList.get(i);
            for(int j=0; j< vList.size(); j++){
                if(i == j) continue;
                Point p2 = vList.get(j);

                list.add(new Edge(i+1, j+1, getDist(p1.y, p1.x, p2.y, p2.x)));
            }
        }

        Collections.sort(list);

        double dist = 0.0;
        for(Edge edge : list){

            if(!findParent(edge.h, edge.tg)){
                dist += edge.d;
                unionParent(edge.h, edge.tg);
            }
        }

        System.out.printf("%.2f\n", dist);
    }
}
