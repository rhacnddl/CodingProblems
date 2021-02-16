package org.gorany.backjoon.트리의지름;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Edge{
        int tg, d;
        public Edge(int target, int dist){
            tg=target; d=dist;
        }
    }
    static final int N = 100001;
    static boolean[] visit;
    static List<Edge>[] list = new ArrayList[N];
    static int max, maxVertex;

    static void DFS(int start, int sum, int dist){

        visit[start] = true;
        sum += dist;

        for(Edge e : list[start])
            if(!visit[e.tg])
                DFS(e.tg, sum, e.d);


        if(max < sum){
            maxVertex = start;
            max = sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(br.readLine());

        for(int i=1; i<=v; i++)
            list[i] = new ArrayList<>();

        for(int i=0; i<v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int home = Integer.parseInt(st.nextToken());

            while(true){
                int target = Integer.parseInt(st.nextToken());
                if(target == -1) break;
                int dist = Integer.parseInt(st.nextToken());

                list[home].add(new Edge(target, dist));
            }
        }

        visit = new boolean[N];
        max = 0;
        maxVertex = 0;
        DFS(1, 0, 0);

        visit = new boolean[N];
        max = 0;
        DFS(maxVertex, 0, 0);

        System.out.println(max);
    }
}
