package org.gorany.backjoon.숫자고르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] arr = new int[101];
    static int[] result = new int[101];
    static boolean[] visit = new boolean[101];
    static boolean flag;

    static void DFS(int x, int start){

        if(!visit[x]){
            visit[x] = true;
            DFS(arr[x], start);
        }
        else
            flag = (x == start);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++)
            arr[i] = Integer.parseInt(br.readLine());

        int idx = 0;
        visit = new boolean[101];
        for(int i=1; i<=n; i++) {
            flag = false;

            DFS(i, i);
            if(flag)
                result[idx++] = i;
        }

        System.out.println(idx);
        for(int i=0; i<idx; i++)
            System.out.println(result[i]);
    }
}