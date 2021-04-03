package org.gorany.backjoon.회사문화1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] list = new ArrayList[100001];
    static int[] weight = new int[100001];

    static void DFS(int x, int praise){

        weight[x] += praise;

        for(int next : list[x])
                DFS(next, weight[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++)
            list[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            int boss = Integer.parseInt(st.nextToken());

            if(boss != -1)
                list[boss].add(i);
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int employee = Integer.parseInt(st.nextToken());
            int praise = Integer.parseInt(st.nextToken());

            weight[employee] += praise;
        }

        DFS(1, 0);

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++)
            sb.append(weight[i]).append(' ');

        System.out.println(sb);
    }
}