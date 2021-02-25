package org.gorany.backjoon.이모티콘;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static class Data{
        int idx, copy;
        public Data(int i, int c){
            idx=i; copy=c;
        }
    }
    static final int INF = 1000000000, N = 1000;
    static int[] arr[] = new int[N+1][N+1], motion = {0, 0, -1};
    static boolean[][] visit = new boolean[N+1][N+1];

    static void BFS(int end){
        Queue<Data> Q = new LinkedList<>();
        visit[1][0] = true;
        Q.add(new Data(1, 0));

        while(!Q.isEmpty()){
            Data data = Q.poll();
            int cur = data.idx;
            int cp = data.copy;

            motion[1] = cp;
            for(int a=0; a<3; a++){
                int next = cur + motion[a];
                int nextCp = cp;

                if(a == 0) nextCp = next;
                if(next < 0 || next > N || nextCp > N || visit[next][nextCp]) continue;

                visit[next][nextCp] = true;
                arr[next][nextCp] = arr[cur][cp] + 1;

                Q.add(new Data(next, nextCp));
            }
        }
    }

    public static void main(String[] args) throws IOException {
       /* BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int min = INF;

        for(int i=0; i<=N; i++) Arrays.fill(arr[n], INF);

        BFS(n);

        for(int i=0; i<=N; i++) min = Math.min(min, arr[n][i]);
        System.out.println(min);*/
        System.out.println(0b11111111111111111111111111111111);
    }
}
