package org.gorany.backjoon.제곱ㄴㄴ수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        Set<Long> set = new HashSet<>();

        long x = 1;
        while(x * x <= max){
            set.add(x * x);
            x++;
        }

        long cnt = 0L;
        for(long i=min; i<=max; i++){
            for(long n : set);

        }


        System.out.println(cnt);
    }
}