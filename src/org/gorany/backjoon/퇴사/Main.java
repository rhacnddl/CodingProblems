package org.gorany.backjoon.퇴사;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] term;
    static int[] pay;
    static int[] dp;
    static int n;

    static int DFS(int day){

        if(day > n) return 0;
        if(dp[day] != 0) return dp[day];
        if(day + term[day] > n + 1) return DFS(day+1);

        dp[day] = Math.max(DFS(day + term[day]) + pay[day], DFS(day+1));

        return dp[day];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        term = new int[n+1];
        pay = new int[n+1];
        dp = new int[n+1];

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            term[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(DFS(1));
        Arrays.stream(dp).forEach(i->System.out.print(i + " "));
    }
}