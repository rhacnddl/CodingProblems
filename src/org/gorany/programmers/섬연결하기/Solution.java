package org.gorany.programmers.섬연결하기;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    static class Edge implements Comparable<Edge>{
        int h, tg, d;
        public Edge(int home, int target, int dist){
            h=home;
            tg=target;
            d=dist;
        }
        @Override
        public int compareTo(Edge o) {
            return d-o.d;
        }
    }
    static List<Edge> list = new ArrayList<>();

    static int[] parent = new int[101];

    static int getParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }
    static boolean findParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        return a == b? true: false;
    }
    static void union(int a, int b){
        a = getParent(a);
        b = getParent(b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static int solution(int n, int[][] costs) {

        for(int i=1; i<=n; i++)
            parent[i] = i;

        for(int i=0; i<costs.length; i++){
            int home = costs[i][0];
            int target = costs[i][1];
            int dist = costs[i][2];
            list.add(new Edge(home, target, dist));
            list.add(new Edge(target, home, dist));
        }

        Collections.sort(list);
        int answer = 0;
        for(Edge e : list)
            if(!findParent(e.h, e.tg)){
                answer += e.d;
                union(e.h, e.tg);
            }

        return answer;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};

        System.out.println(solution(n, costs));
    }
}