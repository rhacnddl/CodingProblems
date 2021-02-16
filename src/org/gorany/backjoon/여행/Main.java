package org.gorany.backjoon.여행;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Edge{
        int h, tg;
        public Edge(int home, int target){
            h=home;
            tg=target;
        }
    }
    static List<Edge> list = new ArrayList<>();
    static int[] trip = new int[1001], parent = new int[201];

    static int getParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }
    static boolean findParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        return a == b? true : false;
    }
    static void unionParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++)
            parent[i] = i;

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                String s = st.nextToken();
                if(!s.equals("0"))
                    list.add(new Edge(i, j));
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=m; i++)
            trip[i] = Integer.parseInt(st.nextToken());

        for(Edge e : list){
            if(!findParent(e.h, e.tg))
                unionParent(e.h, e.tg);
        }

        boolean flag = true;

        for(int a=2; a<=m; a++)
            if(!findParent(trip[1], trip[a]))
                flag = false;


        for(int i=1; i<=m; i++)
            System.out.print(parent[i] + " ");
        System.out.println();

        System.out.println(flag?"YES":"NO");
    }
}