package org.gorany.backjoon.결혼식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Element{
        int v, floor;
        public Element(int vv, int f){
            v=vv; floor = f;
        }
    }
    static List<Integer>[] list = new ArrayList[501];
    static boolean[] visit = new boolean[501];
    static int cnt = 0;

    static void BFS(int v){

        Queue<Element> Q = new LinkedList<>();
        visit[v] = true;
        Q.add(new Element(v, 0));

        while(!Q.isEmpty()){
            Element e = Q.poll();
            int current = e.v;
            int floor = e.floor;

            for(int next : list[current])
                if(!visit[next] && floor + 1 <= 2){
                    cnt++;
                    visit[next] = true;
                    System.out.println("나는 "+next+"이다. floor: "+ (floor+1));
                    Q.add(new Element(next, floor + 1));
                }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++) list[i] = new ArrayList<>();

        for(int i=1; i<=m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            list[h].add(t);
            list[t].add(h);
        }

        BFS(1);
        System.out.println(cnt);
    }
}