package org.gorany.backjoon.로또;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int k;
    static int[] arr = new int[14];
    static boolean[] visit = new boolean[14];
    static StringBuilder sb;

    static void DFS(int cur, int depth){

        if(depth == 5){
            for(int i=0; i<k; i++)
                if(visit[i])
                    sb.append(arr[i]).append(' ');

            sb.append('\n');
            return;
        }

        for(int i=cur; i<k; i++)
            if(!visit[i]) {
                visit[i] = true;
                DFS(i, depth + 1);
                visit[i] = false;
            }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            sb = new StringBuilder();

            k = Integer.parseInt(st.nextToken());

            if(k == 0) break;

            for(int i=0; i<k; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            for(int i=0; i<k; i++) {
                visit[i] = true;
                DFS(i, 0);
                visit[i] = false;
            }

            System.out.println(sb);
        }
    }
}