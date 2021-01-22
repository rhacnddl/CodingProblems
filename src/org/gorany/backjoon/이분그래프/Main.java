package org.gorany.backjoon.이분그래프;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Vertex{
        int index; //1~v
        int state = 0;//-1, 0, 1
        List<Integer> list = new ArrayList<>();

        public Vertex(int index){
            this.index=index;
        }
    }
    static Vertex[] vertices;
    static boolean[] visit;
    static boolean bipartite; //이분 그래프의 조건에 적합하지 않으면 false

    static void DFS(int start){

        visit[start] = true;
        //System.out.println(start+" 방문 & state: " + vertices[start].state);

        vertices[start].list.stream().forEach(i->{
            if(!visit[i] && vertices[i].state == 0){
                vertices[i].state = vertices[start].state * (-1);
                DFS(i);
            }
            else if(vertices[i].state == vertices[start].state){
                //System.out.println("vertices[i].state : " + vertices[i].state + " vertices[start].state : " +  vertices[start].state);
                bipartite = false;
            }
        });
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int k = Integer.parseInt(br.readLine()); //test case 개수

        for(int i=0; i<k; i++){
            bipartite = true;
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()); //정점 개수
            int e = Integer.parseInt(st.nextToken()); //간선 개수

            visit = new boolean[v+1]; //1~v
            vertices = new Vertex[v+1];

            for(int j=1; j<=v; j++)
                vertices[j] = new Vertex(j);

            for(int j=1; j<=e; j++){
                st = new StringTokenizer(br.readLine());
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());

                vertices[left].list.add(right);
                vertices[right].list.add(left);
            }
            vertices[1].state = 1;
            DFS(1);

            for(int j=1; j<=v; j++)
                if(!visit[j]) {
                    vertices[j].state = 1;
                    DFS(j);
                }

            if(bipartite) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
