package org.gorany.backjoon.뱀과사다리게;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point{
        int x, m;
        public Point(int xx, int move){
            x=xx;
            m=move;
        }
    }
    static final int INF = 1000000000;
    static int[] map = new int[101], move = new int[101];

    static int Dijkstra(){
        Queue<Point> Q = new PriorityQueue<>((a, b) -> a.m - b.m);

        move[1] = 0;
        Q.add(new Point(1, 0));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int cur = p.x;
            int m = p.m;

            if(move[cur] < m) continue;

            for(int i=1; i<7; i++){
                int next = cur + i;

                if(next > 100) continue;

                if(move[ map[next] ] > m+1) {
                    move[map[next]] = move[cur] + 1;
                    Q.add(new Point(map[next], m + 1));
                }
            }
        }
        return move[100];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=100; i++) {
            map[i] = i;
            move[i] = INF;
        }

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());

            int target = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            map[target] = dest;
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int target = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            map[target] = dest;
        }

        System.out.println(Dijkstra());
    }
}