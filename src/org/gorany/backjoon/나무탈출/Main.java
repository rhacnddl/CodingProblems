package org.gorany.backjoon.나무탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] list = new ArrayList[500001];
    static boolean[] visit = new boolean[500001];
    static int heightSum = 0;

    static void DFS(int x, int height){

        visit[x] = true;
        boolean isLeaf = true;

        for(int next : list[x])
            if(!visit[next]) {
                isLeaf = false;
                DFS(next, height + 1);
            }

        if(isLeaf)
            heightSum += height;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++)
            list[i] = new ArrayList<>();

        for(int i=1; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            list[v1].add(v2);
            list[v2].add(v1);
        }
        DFS(1, 0);
        System.out.println(heightSum % 2 != 0? "Yes":"No");
    }
}