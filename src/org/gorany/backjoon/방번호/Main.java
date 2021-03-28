package org.gorany.backjoon.방번호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] cost;
    static String[] dp = new String[51];
    static int[] rest = new int[51];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        cost = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
            cost[i] = Integer.parseInt(st.nextToken());

        int money = Integer.parseInt(br.readLine());


    }
}