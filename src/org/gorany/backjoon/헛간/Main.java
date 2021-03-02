package org.gorany.backjoon.헛간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int N = 20001;
    static List<Integer>[] list = new ArrayList[N];
    static int[] dist = new int[N];
    static boolean[] visit = new boolean[N];
    static int max = 0;

    static void BFS(){

        Queue<Integer> Q = new LinkedList<>();

        visit[1] = true;
        Q.add(1);

        while(!Q.isEmpty()){
            int cur = Q.poll();

            max = Math.max(dist[cur], max);

            for(Integer next:list[cur])
                if(!visit[next]){
                    dist[next] = dist[cur] + 1;
                    visit[next] = true;
                    Q.add(next);
                }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++) list[i] = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            list[h].add(t);
            list[t].add(h);
        }

        BFS();

        int cnt = 0;
        int idx = N;
        for(int i=1; i<=n; i++)
            if(dist[i] == max){
                cnt++;
                idx = Math.min(idx, i);
            }

        System.out.println(idx + " " + max + " " + cnt);
    }
}