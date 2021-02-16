package org.gorany.backjoon.트리의부모찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int N = 100001;
    static List<Integer>[] list = new ArrayList[N];
    static int[] parent = new int[N];
    static boolean[] visit = new boolean[N];

    static void DFS(int v){

        visit[v] = true;

        for(int next : list[v])
            if(!visit[next]){
                parent[next] = v;
                DFS(next);
            }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++)
            list[i] = new ArrayList<>();

        for(int i=1; i<n; i++){
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            list[v1].add(v2);
            list[v2].add(v1);
        }
        DFS(1);

        for(int i=2; i<=n; i++)
            System.out.println(parent[i]);
    }
}
