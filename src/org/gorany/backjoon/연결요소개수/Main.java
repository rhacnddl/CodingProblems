package org.gorany.backjoon.연결요소개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int cnt = 0;
    static List<Integer>[] list;
    static void DFS(int s, boolean[] v){

        v[s] = true;

        for(Integer a : list[s])
            if(!v[a])
                DFS(a, v);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        list = new ArrayList[v+1];
        boolean[] visit = new boolean[v+1];

        for(int i=1; i<=v; i++) list[i] = new ArrayList<>();

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        for(int i=1; i<=v; i++)
            if(!visit[i]) {
                cnt++;
                DFS(i, visit);
            }
        System.out.println(cnt);
    }
}
