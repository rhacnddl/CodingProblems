package org.gorany.backjoon.서울지하철2호선;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main1 {

    static List<Integer>[] list = new ArrayList[3001];
    static int[] visit = new int[3001], dist = new int[3001];
/*
visit
0 : 방문하지 않음
1 : 방문, 사이클 X
2 : 방문, 사이클 O
*/
static int DFS(int x, int before){
/*
DFS
-1 : 사이클을 못 찾음
-2 : 사이클을 찾음 but, 사이클에 포함 X
1 ~ n : 사이클을 찾음, 사이클에 포함 O
결과적으로 visit는 0, 1, 2중 하나를 갖게 된다. 이때 0과 1은 중요치 않고 2가 중요하다.
찾은 사이클에서 BFS를 실행해 지선으로 뻗어나갈 것 이기 때문
*/
    if(visit[x] == 1)
        return x;

    visit[x] = 1;

    for(int next:list[x]){

        if(next == before) continue;

        int result = DFS(next, x);

        if(result == -2) return -2;
        if(result > 0){
            visit[x] = 2;
            if(x == result) return -2;
            else return result;
        }
    }
    return -1;
}

    static boolean[] check = new boolean[3001];
    static void BFS(int x){

        Queue<Integer> Q = new LinkedList<>();

        check[x] = true;
        Q.add(x);

        while(!Q.isEmpty()){
            int cur = Q.poll();

            for(int next:list[cur])
                if(visit[next] != 2 && !check[next]){

                    check[next] = true;
                    dist[next] = dist[cur] + 1;
                    Q.add(next);
                }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int home = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            list[home].add(target);
            list[target].add(home);
        }

        DFS(1, 0);

        for(int i=1; i<=n; i++)
            if(visit[i] == 2)
                BFS(i);

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++)
            sb.append(dist[i]).append(' ');

        System.out.println(sb);
    }
}