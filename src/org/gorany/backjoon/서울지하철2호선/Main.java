package org.gorany.backjoon.서울지하철2호선;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge{
        int v, d;
        public Edge(int vv, int dd){
            v=vv; d=dd;
        }
    }
    static List<Integer>[] list = new ArrayList[3001];
    static boolean[] visit = new boolean[3001], cycle = new boolean[3001];
    static int[] cycle_ = new int[3001], visit_ = new int[3001];
    static boolean flag = false;

    static int BFS(int x){
        Queue<Edge> Q = new LinkedList<>();
        visit[x] = true;
        Q.add(new Edge(x, 0));

        while(!Q.isEmpty()){
            Edge e = Q.poll();

            if(cycle[e.v]) return e.d;

            for(int next:list[e.v])
                if(!visit[next]){
                    visit[next] = true;
                    Q.add(new Edge(next, e.d + 1));
                }
        }
        return 0;
    }


    static int dfs(int x, int before){

        if(visit_[x] == 1) return x;

        visit_[x] = 1;

        for(int next : list[x]){
            if(next == before) continue;

            int result = dfs(next, x);

            if(result == -2) return -2;
            if(result >= 0){
                visit_[next] = 2;
                return (x == result)? -2 : result;
            }
        }
        return -1;
    }

    static void DFS(int x, int before, String str){

        visit[x] = true;

        StringBuilder sb = new StringBuilder(str);
        sb.append(x).append(" ");

        for(int next : list[x]){ //다음 방문할 정점 탐색

            if(sb.indexOf(Integer.toString(next)) != -1 && next != before){ //다음 정점이 before이 아니고 str에 있다면 cycle이 형성되었다고 판단
                String[] strs = sb.substring(sb.indexOf(Integer.toString(next))).split(" ");

                //System.out.println(Arrays.toString(strs));
                for (String s : strs) {
                    cycle[Integer.parseInt(s)] = true;
                }
                flag = true; //flag = true -> Cycle을 발견했음을 알림
            }

            if(!visit[next] && !flag){
                DFS(next, x, sb.toString());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++) list[i] = new ArrayList<>();

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int home = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            list[home].add(target);
            list[target].add(home);
        }

        DFS(1, 0, "");

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++) {
            visit = new boolean[3001];
            //sb.append(!cycle[i] ? BFS(i) + " " : "0 ");
            sb.append(BFS(i)).append(" ");
        }
        System.out.println(sb.toString());
    }
}