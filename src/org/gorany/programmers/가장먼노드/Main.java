package org.gorany.programmers.가장먼노드;

import java.util.*;

class Solution {

    static class Edge{
        int tg, d;
        public Edge(int target, int dist){
            tg=target;
            d=dist;
        }
    }

    static final int INF = 1000000000;
    static List<Integer>[] list = new ArrayList[20001];
    static int[] dist = new int[20001];

    static void Dijkstra(){
        Queue<Edge> Q = new PriorityQueue<>((a, b) -> a.d - b.d);
        dist[1] = 0;
        Q.add(new Edge(1, 0));

        while(!Q.isEmpty()){
            Edge e = Q.poll();
            int cur = e.tg;
            int d = e.d;

            if(dist[cur] < d) continue;

            for(int next:list[cur])
                if(dist[next] > d + 1){
                    dist[next] = d + 1;
                    Q.add(new Edge(next, d + 1));
                }
        }
    }

    public int solution(int n, int[][] edge) {

        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
            dist[i] = INF;
        }

        for(int i=0; i<edge.length; i++){
            list[edge[i][0]].add(edge[i][1]);
            list[edge[i][1]].add(edge[i][0]);
        }

        Dijkstra();

        int answer = 0, max = 0;

        for(int i=1; i<=n; i++)
            if(dist[i] > max){
                answer = 1;
                max = dist[i];
            }
            else if(max == dist[i])
                answer++;

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {

        Solution sol = new Solution();

        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        System.out.println(sol.solution(n, edge));
    }
}