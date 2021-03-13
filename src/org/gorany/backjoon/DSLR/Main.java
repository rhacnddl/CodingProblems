package org.gorany.backjoon.DSLR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node{
        int v;
        String command;
        public Node(int vv, String c){
            v=vv; command=c;
        }
    }

    static int D(int x){
        return (2 * x) % 10000;
    }
    static int S(int x){
        return (x == 0)? 9999 : x - 1;
    }
    static int L(int x){

        StringBuilder str = new StringBuilder(Integer.toString(x));

        if(x < 1000) {
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < 4 - str.length(); i++)
                tmp.append(0);
            tmp.append(x);
            str = tmp;
        }

        char zero = str.toString().charAt(0);
        StringBuilder sb = new StringBuilder();
        sb.append(str.toString(), 1, 4).append(zero);

        return Integer.parseInt(sb.toString());
    }
    static int R(int x){

        StringBuilder str = new StringBuilder(Integer.toString(x));

        if(x < 1000) {
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < 4 - str.length(); i++)
                tmp.append(0);
            tmp.append(x);
            str = tmp;
        }

        char four = str.toString().charAt(3);
        StringBuilder sb = new StringBuilder();
        sb.append(four).append(str.toString(), 0, 3);

        return Integer.parseInt(sb.toString());
    }

    static boolean[] visit;

    static void BFS(int start, int end){

        Queue<Node> Q = new LinkedList<>();
        visit[start] = true;
        Q.add(new Node(start, ""));

        while(!Q.isEmpty()){
            Node n = Q.poll();
            int c = n.v;
            String command = n.command;

            if(c == end) {
                System.out.println(command);
                break;
            }

            int d = D(c);
            int s = S(c);
            int l = L(c);
            int r = R(c);

            if(d > -1 && d < 10000 && !visit[d]){
                visit[d] = true;
                Q.add(new Node(d, command + "D"));
            }
            if(s > -1 && s < 10000 && !visit[s]){
                visit[s] = true;
                Q.add(new Node(s, command + "S"));
            }
            if(l > -1 && l < 10000 && !visit[l]){
                visit[l] = true;
                Q.add(new Node(l, command + "L"));
            }
            if(r > -1 && r < 10000 && !visit[r]){
                visit[r] = true;
                Q.add(new Node(r, command + "R"));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        for(int i=0; i<k; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            visit = new boolean[10001];

            BFS(A, B);
        }
    }
}