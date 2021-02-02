package org.gorany.backjoon.N과M_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] arr;
    static boolean[] visit;
    static int cnt = n;
    static void bt(int k, int idx){
        if(k == m){
            for(int i=0; i<m; i++) {
                visit[arr[0]] = true;
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i=idx; i<=n; i++) {
            if(!visit[i]){
                visit[i] = true;
                arr[k] = i;
                bt(k+1, idx + 1);
                visit[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new boolean[n + 1];
        arr = new int[m];

        bt(0, 1);
    }
}
