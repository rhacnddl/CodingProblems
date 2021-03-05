package org.gorany.backjoon.회장뽑기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Elem{
        int tg, score;
        public Elem(int t, int sc){
            tg=t; score=sc;
        }
    }
    static List<Integer>[] list = new ArrayList[51];
    static boolean[] visit;
    static int[] score = new int[51];
    static int min = 1000000000;

    static void BFS(int s, int n){

        Queue<Elem> Q = new LinkedList<>();
        visit = new boolean[n+1];
        int result = 0;

        visit[s] = true;
        Q.add(new Elem(s, 0));

        while(!Q.isEmpty()){
            Elem e = Q.poll();
            int cur = e.tg;
            int score = e.score;

            result = Math.max(score, result);

            for(int next:list[cur])
                if(!visit[next]){
                    visit[next] = true;
                    Q.add(new Elem(next, score + 1));
                }
        }
        min = Math.min(min, result);
        score[s] = result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            if(v1 == -1) break;

            list[v1].add(v2);
            list[v2].add(v1);
        }

        for(int i=1; i<=n; i++)
            BFS(i, n);

        int cnt = 0;
        int[] person = new int[n+1];
        for(int i=1, j=0; i<=n; i++)
            if(score[i] == min) {
                cnt++;
                person[j++] = i;
            }

        System.out.println(min + " " + cnt);
        for(int i=0; person[i] != 0; i++)
            System.out.print(person[i] + " ");
    }
}
