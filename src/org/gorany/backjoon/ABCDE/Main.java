package org.gorany.backjoon.ABCDE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] list = new ArrayList[2000];
    static boolean[] visit;
    static boolean flag = false;

    static void DFS(int start, int depth){

        visit[start] = true;
        depth++;

        if(depth == 5){
            flag = true;
            return;
        }

        for(int next : list[start])
            if(!visit[next])
                DFS(next, depth);

        visit[start] = false;
        depth--;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) list[i] = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            list[v1].add(v2);
            list[v2].add(v1);
        }

        for(int i=0; i<n; i++){
            visit = new boolean[2000];
            if(flag) break;
            DFS(i, 0);
        }
        if (flag) System.out.println(1);
        else System.out.println(0);
    }
}