package org.gorany.backjoon.인간대포;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Vertex{
        float y, x;
        int idx;

        public Vertex(float y, float x, int idx){
            this.y=y;
            this.x=x;
            this.idx=idx;
        }
    }
    static class Edge{
        int target;
        float dist, sec;

        public Edge(int tg, float d, float sec){
            target = tg;
            dist = d;
            this.sec = sec;
        }
    }
    static final int INF = 1000000000;
    static List<Edge>[] list = new ArrayList[102];
    static final float[] time = new float[102];

    static float getDist(Vertex a, Vertex b){

        return (float) Math.sqrt( Math.pow(a.y - b.y, 2) + Math.pow(a.x - b.x, 2) );
    }

    static void Dijkstra(int n){

        Queue<Edge> Q = new PriorityQueue<>((a, b) -> (int) (a.sec - b.sec));
        time[0] = 0;

        Q.add(new Edge(0, 0, 0));

        while(!Q.isEmpty()){
            Edge e = Q.poll();

            int cur = e.target;
            float sec = e.sec;

            if(time[cur] < sec) continue;

            for(Edge ne : list[cur]){
                int next = ne.target;
                float dist = ne.dist;

                //대포를 이용한다(출발점과 도착점에서는 대포를 이용못한다)
                //대포와 다음 도착점의 거리가 50보다 작으면 대포를 타고 도착점까지 되돌아간다.
                if(cur > 0 && cur <= n){
                    if(dist >= 50) {
                        float nSec = sec + 2 + (dist - 50) / 5.0f;

                        if (time[next] > nSec) {
                            time[next] = nSec;
                            Q.add(new Edge(next, 0, nSec));
                        }
                    }
                    else{
                        float nSec = sec + 2 + (50 - dist) / 5.0f;

                        if (time[next] > nSec) {
                            time[next] = nSec;
                            Q.add(new Edge(next, 0, nSec));
                        }
                    }
                }

                //오직 걸어간다
                float nSec = sec + (dist / 5.0f);
                if(time[next] > nSec){
                    time[next] = nSec;
                    Q.add(new Edge(next, 0, nSec));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Vertex[] vertices = new Vertex[102];
        //출발점, 도착점
        for(int i=0; i<2; i++){
            st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());
            vertices[i] = new Vertex(y, x, i);
        }

        int n = Integer.parseInt(br.readLine());

        vertices[n+1] = new Vertex(vertices[1].y, vertices[1].x, n+1);
        //대포의 위치와 인덱스 (출발점 인덱스: 0, 도착점 인덱스: n+1)
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());
            vertices[i] = new Vertex(y, x, i);
        }

        //인접리스트 구현
        for(int i=0; i<=n+1; i++){
            time[i] = INF;
            list[i] = new ArrayList<>();
            for(int j=0; j<=n+1; j++){

                if(i == j) continue;
                float dist = getDist(vertices[i], vertices[j]);
                list[i].add(new Edge(j, dist, 0));
            }
        }

        Dijkstra(n);

        System.out.println(time[n+1]);
    }
}