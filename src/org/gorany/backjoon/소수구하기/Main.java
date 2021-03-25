package org.gorany.backjoon.소수구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean isPrime(int x){
        if(x == 1) return false;

        for(int i=2; i<=Math.sqrt(x); i++)
            if(x % i == 0)
                return false;

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=n; i<=m; i++)
            if(isPrime(i))
                sb.append(i).append('\n');

        System.out.println(sb.toString());
    }
}