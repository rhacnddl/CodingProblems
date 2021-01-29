package org.gorany.backjoon.행성터널;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class EdgeComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge e1, Edge e2) {
            int w1 = e1.w;
            int w2 = e2.w;

            if(w1 < w2) return -1;
            else if(w1 > w2) return 1;
            else return 0;
        }
    }

    static class IntComparator implements Comparator<int[]> {

        int xyz;
        public IntComparator(int xyz){
            this.xyz = xyz;
        }

        @Override
        public int compare(int[] o1, int[] o2) {
            if(o1[xyz] > o2[xyz]) return 1;
            else if(o1[xyz] < o2[xyz]) return -1;
            return -1;
        }
    }
    static class Edge{
        int[] v = new int[2];
        int w;

        public Edge(int v1, int v2, int w){
            this.v[0] = v1;
            this.v[1] = v2;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "v=" + Arrays.toString(v) +
                    ", w=" + w +
                    '}';
        }
    }
    static int getParent(int[] parent, int x){
        if(parent[x] == x) return x;
        return getParent(parent, parent[x]);
    }
    static void unionParent(int[] parent, int a, int b){
        a = getParent(parent, a);
        b = getParent(parent, b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }
    static boolean findParent(int[] parent, int a, int b){
        a = getParent(parent, a);
        b = getParent(parent, b);
        return a == b?true:false;
    }

    static int min(int a, int b, int c){
        int min = Math.min(a, b);
        return min < c?min:c;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int v = Integer.parseInt(br.readLine());

        Edge[] edges = new Edge[3*(v-1)];
        int[][] vertex = new int[v+1][4];
        int[] parent = new int[v+1];

        for(int i=1; i<=v; i++)
            parent[i] = i;

        for(int i=1; i<=v; i++){
            st = new StringTokenizer(br.readLine());

            vertex[i][0] = Integer.parseInt(st.nextToken());
            vertex[i][1] = Integer.parseInt(st.nextToken());
            vertex[i][2] = Integer.parseInt(st.nextToken());
            vertex[i][3] = i;
        }

        int idx = 0;

        IntComparator x_comp = new IntComparator(0); //x
        Arrays.sort(vertex, 1, v+1, x_comp);
        for(int i=1; i<v; i++) edges[idx++] = new Edge(vertex[i][3], vertex[i+1][3], vertex[i+1][0] - vertex[i][0]);

        IntComparator y_comp = new IntComparator(1); //y
        Arrays.sort(vertex, 1, v+1, y_comp);
        for(int i=1; i<v; i++) edges[idx++] = new Edge(vertex[i][3], vertex[i+1][3], vertex[i+1][1] - vertex[i][1]);

        IntComparator z_comp = new IntComparator(2); //z
        Arrays.sort(vertex, 1, v+1, z_comp);
        for(int i=1; i<v; i++) edges[idx++] = new Edge(vertex[i][3], vertex[i+1][3], vertex[i+1][2] - vertex[i][2]);

        List<Edge> list = Arrays.asList(edges.clone());

        EdgeComparator edgeComparator = new EdgeComparator();
        list.sort(edgeComparator);

        int sum = 0;
        for(int i=0; i<3*(v-1); i++){

            Edge edge = list.get(i);

            if(!findParent(parent, edge.v[0], edge.v[1])){
                sum += edge.w;
                unionParent(parent, edge.v[0], edge.v[1]);
            }
        }
        System.out.println(sum);

/*
        int sum = 0;
        for(int i=1; i<=v; i++){
            int min = 999999999;
            int index = 0;
            for(int j=i+1; j<=v; j++){

                int tmp = min(Math.abs(vertex[i][0] - vertex[j][0]), Math.abs(vertex[i][1] - vertex[j][1]), Math.abs(vertex[i][2] - vertex[j][2]));
                System.out.println("i : " + i + "  j : " + j + " 일 때 tmp = " + tmp);
                if (tmp < min) {
                    index = j;
                    min = tmp;
                }
            }
            if(index == 0) continue;
            if(!findParent(parent, i, index)){
                System.out.println("i와 index는 : " + i + " " + index + " 그리고 min : " + min);
                sum += min;
                unionParent(parent, i, index);
            }
        }
        Arrays.stream(parent).skip(1).forEach(i->System.out.print(i + " "));
        System.out.println();
        System.out.println(sum);
*/
    }
}
