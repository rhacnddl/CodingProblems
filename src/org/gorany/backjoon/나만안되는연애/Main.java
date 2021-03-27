package org.gorany.backjoon.나만안되는연애;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge{
        int h, tg, d;
        public Edge(int home, int target, int dist){
            h=home;
            tg=target;
            d=dist;
        }
    }

    static List<Edge> list = new ArrayList<>();
    static boolean[] univ = new boolean[1001];
    static int[] parent = new int[1001];

    static int getParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }
    static boolean findParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        return a == b;
    }
    static void union(int a, int b){
        a = getParent(a);
        b = getParent(b);

        if(b < a)
            parent[a] = b;
        else
            parent[b] = a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            univ[i] = st.nextToken().equals("M");
            parent[i] = i;
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if (univ[h] != univ[t])
                list.add(new Edge(h, t, d));
        }

        Collections.sort(list, (a, b) -> a.d - b.d);

        int sum = 0;
        for(Edge e : list)
            if(!findParent(e.h, e.tg)){
                sum += e.d;
                union(e.h, e.tg);
            }

        boolean flag = true;
        for(int i=1; i<=n; i++)
            if(parent[1] != getParent(parent[i])) {
                flag = false;
                break;
            }

        System.out.println(flag? sum : -1);
    }
}