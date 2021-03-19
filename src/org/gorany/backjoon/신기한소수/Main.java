package org.gorany.backjoon.신기한소수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static StringBuilder sb = new StringBuilder();
    static boolean isPrime(int x){

        if(x == 1) return false;

        for(int i=2; i<=Math.sqrt(x); i++)
            if(x % i == 0)
                return false;

        return true;
    }
    static void DFS(String str, int len){

        if(len == n)
            sb.append(str).append("\n");
        else{
            for(int i=1; i<=9; i++) {
                String s = str + i;
                if (isPrime(Integer.parseInt(s.toString())))
                    DFS(s, len + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        DFS("", 0);
        System.out.print(sb.toString());
    }
}