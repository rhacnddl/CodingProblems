package org.gorany.backjoon.역사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.*;

public class Main {

    static class Edge{
        int tg, dir;
        public Edge(int tg, int dir){
            this.tg=tg;
            this.dir=dir;
        }
    }
    static List<Edge>[] list = new ArrayList[501];
    static boolean[] visit = new boolean[501];
    static int result=0;

    static void DFS(int v, int end, int opt){

        if(v == end)
            if(opt == -1) result = -1;
            else if(opt == 1) result = 1;

        visit[v] = true;

        for(Edge e : list[v]){
            int next = e.tg;
            int dir = e.dir;

            if(!visit[next])
                DFS(next, end, dir);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++)
            list[i] = new ArrayList<>();

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            list[h].add(new Edge(t, -1));
            list[t].add(new Edge(h, 1));
        }

        int s = Integer.parseInt(br.readLine());

        for(int i=0; i<s; i++){
            result = 0;
            visit = new boolean[501];
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            DFS(h, t, 0);

            System.out.println(result);
        }

    }
}