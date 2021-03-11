package org.gorany.backjoon.줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer>[] list = new ArrayList[32001];
    static StringBuilder sb = new StringBuilder();
    static boolean[] visit = new boolean[32001];

    static void BFS(int x){

        Queue<Integer> Q = new LinkedList<>();
        visit[x] = true;
        sb.append(x + " ");
        Q.add(x);

        while(!Q.isEmpty()){
            int cur = Q.poll();

            for(int next : list[cur])
                if(!visit[next]){
                    visit[next] = true;
                    sb.append(next + " ");
                    Q.add(next);
                }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++)
            list[i] = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[b].add(a);
        }

        for(int i=1; i<=n; i++)
            if(!visit[i])
                BFS(i);

        System.out.println(sb.toString());
    }
}