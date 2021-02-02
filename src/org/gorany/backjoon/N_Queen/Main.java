package org.gorany.backjoon.N_Queen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;

    static boolean[] X = new boolean[40];
    static boolean[] used1 = new boolean[40]; //x+y
    static boolean[] used2 = new boolean[40]; //x-y+n-1

    static int cnt = 0;
    static void bt(int k){

        if(k == n){
            cnt++;
            return;
        }

        for(int x=0; x<n; x++){
            if(X[x] || used1[x+k] || used2[k-x+n-1]) continue;
                X[x] = true;
                used1[x+k] = true;
                used2[k-x+n-1] = true;

                bt(k+1);

                X[x] = false;
                used1[x+k] = false;
                used2[k-x+n-1] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        bt(0);
        System.out.println(cnt);
    }
}
