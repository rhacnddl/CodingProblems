package org.gorany.backjoon.중복제거;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        int idx = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            int val = Integer.parseInt(st.nextToken());

            if(!map.containsKey(val)) {
                map.put(val, idx++);
                sb.append(val).append(' ');
            }
        }
        System.out.println(sb.toString());
    }
    /*public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] visit = new boolean[1 << 25];
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            int val = Integer.parseInt(st.nextToken());

            if(!visit[val]) {
                visit[val] = true;
                sb.append(val).append(' ');
            }
        }
        System.out.println(sb.toString());
    }*/
    /*public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            int val = Integer.parseInt(st.nextToken());

            if(set.add(val))
                sb.append(val).append(' ');
        }
        System.out.println(sb.toString());
    }*/
}