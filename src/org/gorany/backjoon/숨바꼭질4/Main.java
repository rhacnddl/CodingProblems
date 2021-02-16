package org.gorany.backjoon.숨바꼭질4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Element{
        int next, dist;
        public Element(int n, int d){
            next = n;
            dist = d;
        }
    }
    static final int N = 100001;
    static boolean[] visit = new boolean[N];
    static int[] arr = new int[N], from = new int[N], move = {-1, 1, 0};

    static void BFS(int start, int end){

        Queue<Element> Q = new LinkedList<>();
        visit[start] = true;
        Q.add(new Element(start, 0));

        while(!Q.isEmpty()){
            Element e = Q.poll();

            int current = e.next;
            int dist = e.dist;

            if(current == end) break;

            move[2] = current;
            for(int a=0; a<3; a++){
                int next = current + move[a];

                if(next < 0 || next > N-1 || visit[next]) continue;

                visit[next] = true;
                arr[next] = dist + 1;
                from[next] = current;
                Q.add(new Element(next, dist + 1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        BFS(start, end);

        list.add(end);
        int v = end;
        while(v != start){
            list.add(from[v]);
            v = from[v];
        }

        /*for(int i=0; i<=end; i++)
            System.out.print(from[i] + " ");
        System.out.println();
        for(int i=0; i<=end; i++)
            System.out.print(arr[i] + " ");
        System.out.println();*/

        System.out.println(arr[end]);
        for(int i=list.size()-1; i>=0; i--)
            System.out.print(list.get(i) + " ");
    }
}
