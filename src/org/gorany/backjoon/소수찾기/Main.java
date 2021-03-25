package org.gorany.backjoon.소수찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int cnt = 0;
    static boolean isPrime(int x){
        if(x == 1) return false;

        boolean prime = true;

        for(int i=2; i<=(int)Math.sqrt(x); i++)
            if(x % i == 0)
                prime = false;

        return prime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[100];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if(isPrime(arr[i])) cnt++;
        }
        System.out.println(cnt);
    }
}