package org.gorany.backjoon.그대그머가되어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point{
        int tg, d;
        public Point(int tg, int d){
            this.tg = tg;
            this.d = d;
        }
    }
    static final int INF = 1000000000;
    static List<Integer>[] list = new ArrayList[1001];
    static int[] dist = new int[1001];

    static void Dijkstra(int start){

        Queue<Point> pq = new PriorityQueue<>((a, b) -> a.d - b.d);
        dist[start] = 0;
        pq.add(new Point(start, 0));

        while(!pq.isEmpty()){
            Point p = pq.poll();
            int cur = p.tg;
            int d = p.d;

            if(dist[cur] < d) continue;

            for(int next:list[cur]){
                if(dist[next] > d + 1){
                    dist[next] = d + 1;
                    pq.add(new Point(next, d + 1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int target = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
            dist[i] = INF;
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int tg = Integer.parseInt(st.nextToken());

            list[h].add(tg);
            list[tg].add(h);
        }

        Dijkstra(target);
        System.out.println(dist[goal] == INF ? -1 : dist[goal]);
    }
}