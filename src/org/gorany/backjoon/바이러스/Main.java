package org.gorany.backjoon.바이러스;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    static int cnt = 0;
    static List<Integer>[] graph;
    static boolean[] visit;

    static void DFS(int start){

        visit[start] = true;
        cnt++;

        graph[start].stream().forEach(v->{
            if(!visit[v])
                DFS(v);
        });
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //v : 컴퓨터 개수,e : 간선의 개수
        int n = sc.nextInt(), target, dest;
        int e = sc.nextInt();

        graph = new ArrayList[n+1];
        visit = new boolean[n+1];
        IntStream.rangeClosed(1, n).forEach(i->graph[i] = new ArrayList<>());

        for(int i=1; i<=e; i++){
            target = sc.nextInt();
            dest = sc.nextInt();
            graph[target].add(dest);
            graph[dest].add(target);
        }

        DFS(1);
        System.out.print(cnt-1);
    }
}
