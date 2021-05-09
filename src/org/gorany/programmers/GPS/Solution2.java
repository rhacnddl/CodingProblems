package org.gorany.programmers.GPS;

import java.util.*;

class Solution2 {

    static class Edge{
        int tg, idx, change;
        public Edge(int target, int index, int change){
            tg=target;
            idx=index;
            this.change=change;
        }
    }

    static int[] ch;
    static List<Integer>[] list;
    static final int INF = 1000000000;


    static boolean BFS(int start, int end, int n){
        boolean[] visit = new boolean[n+1];
        Queue<Integer> Q = new LinkedList<>();

        visit[start] = true;
        Q.add(start);

        while(!Q.isEmpty()){
            int cur = Q.poll();

            if(cur == end)
                return true;

            for(int next:list[cur])
                if(!visit[next]) {
                    visit[next] = true;
                    Q.add(next);
                }
        }
        return false;
    }

    static int Dijkstra(int start, int end, int K, int[] gps){
        Queue<Edge> Q = new PriorityQueue<>((a, b) -> a.change - b.change);

        ch[start] = 0;
        Q.add(new Edge(start, 0, 0));

        while(!Q.isEmpty()){
            Edge e = Q.poll();
            int cur = e.tg;
            int idx = e.idx;
            int change = e.change;

            if(idx == K && cur == end)
                return change;

            if(ch[cur] < change) continue;

            if(idx < K)
                for(int next:list[cur]){

                    if(next == gps[idx + 1]){
                        if(ch[next] >= change) {
                            ch[next] = change;
                            Q.add(new Edge(next, idx + 1, change));
                        }
                    }
                    else {
                        if(ch[next] > change + 1) {
                            ch[next] = change + 1;
                            Q.add(new Edge(next, idx + 1, change + 1));
                        }
                    }
                }
        }
        return -1;
    }

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {

        ch = new int[n + 1];
        list = new ArrayList[n + 1];

        /* 자기 자신에 머무를 수 있음*/
        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
            list[i].add(i);
            ch[i] = INF;
        }

        /* 이동 가능한 도로 */
        for(int[] c: edge_list){
            list[c[0]].add(c[1]);
            list[c[1]].add(c[0]);
        }

        int start = gps_log[0];
        int end = gps_log[k-1];

        if(!BFS(start, end, n))
            return -1;
        else
            return Dijkstra(start, end, k - 1, gps_log);
    }
}

class Main2{
    public static void main(String[] args) {
        Solution2 sol = new Solution2();

        int n = 8;
        int m = 10;
        int[][] edge_list = {{1,2}, {1,3}, {2,3}, {2,4}, {3,4}, {3,5}, {4,6}, {5,6}, {5,7}, {6,7}};
        int k = 6;
        //int[] gps_log = {1,2,3,3,6,7};
        int[] gps_log = {1,7,7,7,7,7};

        System.out.println(sol.solution(n, m, edge_list, k, gps_log));
    }
}
