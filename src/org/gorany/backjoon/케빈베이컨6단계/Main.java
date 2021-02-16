package org.gorany.backjoon.케빈베이컨6단계;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge{
        int tg, d;
        public Edge(int target, int dist){
            tg = target; d=dist;
        }
    }
    static List<Edge>[] list = new ArrayList[101];
    static boolean[] visit;
    static final int INF = 1000000000;

    static int BFS(int start){
        int sum = 0;
        Queue<Edge> Q = new LinkedList<>();

        visit[start] = true;
        Q.add(new Edge(start, 1));

        while(!Q.isEmpty()){
            Edge edge = Q.poll();
            int current = edge.tg;
            int layer = edge.d;

            for(Edge next : list[current])
                if(!visit[next.tg]){
                    visit[next.tg] = true;
                    sum += layer;
                    Q.add(new Edge(next.tg, layer + 1));
                }
        }
        System.out.println("현재 "+start +" 이며, sum = " + sum);
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=1 ; i<=n; i++)
            list[i] = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int home = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            list[home].add(new Edge(target, 0));
            list[target].add(new Edge(home, 0));
        }

        int min = INF, minIdx = -1;
        for(int i=1; i<=n; i++){
            visit = new boolean[101];
            int result = BFS(i);

            if(result < min){
                min = result;
                minIdx = i;
            }
        }
        System.out.println(minIdx);
    }
}
