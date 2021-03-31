package org.gorany.backjoon.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean[] visit = new boolean[51];
    static int[][] mat = new int[51][51];
    static int n, cnt = 0;

    static void DFS(int x){

        visit[x] = true;
        boolean hasChild = false;

        for(int i=0; i<n; i++)
            if(mat[x][i] != 0 && !visit[i]) {
                hasChild = true;
                DFS(i);
            }

        if(!hasChild) cnt++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int root = 0;
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            int val = Integer.parseInt(st.nextToken());

            if(val != -1)
                mat[val][i] = 1;
            else
                root = i;
        }
        int rm = Integer.parseInt(br.readLine());

        visit[rm] = true;
        if(root != rm)
            DFS(root);
        System.out.println(cnt);
    }
}