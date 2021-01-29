package org.gorany.backjoon.숨바꼭질3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Edge implements Comparable<Edge>{
        int target, dist;
        public Edge(int t, int d){
            target=t; dist=d;
        }
        @Override
        public int compareTo(Edge o) {
            return dist-o.dist;
        }
    }
    static final int MAX = 100000;
    static final int INF = 1000000000;
    static int n,k;
    static int[] distance = new int[MAX+1];
    static Queue<Edge> Q = new PriorityQueue<>();
    static int[] X = {-1, 1, 0};

    static int Dijkstra(int start){
        distance[start] = 0;
        Q.add(new Edge(start, 0));

        while(!Q.isEmpty()){

            Edge edge = Q.poll();
            int current = edge.target;
            int dist = edge.dist;

            if(current == k) break;
            if(distance[current] < dist) continue;

            X[2] = current;
            for(int i=0; i<3; i++){
                int next = current + X[i];
                int nextDistance = (i<2)? dist+1: dist;

                if(next >= 0 && next <= MAX && distance[next] > nextDistance){
                     distance[next] = nextDistance;
                     Q.add(new Edge(next, nextDistance));
                }
            }
        }
        return distance[k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=0; i<=MAX; i++)
            distance[i] = INF;

        System.out.println(Dijkstra(n));
    }
}
