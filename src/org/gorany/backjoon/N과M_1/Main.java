package org.gorany.backjoon.N과M_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] arr;
    static boolean[] used;

    static void BackTracking(int k){
        if(k == m){
            for(int i=0; i<m; i++)
                System.out.printf("%d ", arr[i]);
            System.out.println();
            return;
        }

        for(int i=1; i<=n; i++)
            if(!used[i]){
                arr[k] = i;
                used[i] = true;
                BackTracking(k + 1);
                used[i] = false;
            }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        used = new boolean[n + 1];

        BackTracking(0);
    }
}
