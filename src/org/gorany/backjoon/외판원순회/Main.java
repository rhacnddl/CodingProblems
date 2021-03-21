package org.gorany.backjoon.외판원순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = 1000000000;
    static int[][] map = new int[16][16];
    static int[][] dp = new int[16][1 << 16];
    static int n;

    static int DFS(int x, int visit){

        if(dp[x][visit] != 0) //방문한 적이 있다면 값을 반환
            return dp[x][visit];

        if(visit == (Math.pow(2, n) - 1)){ //모든 정점을 방문했을 때
            if(map[x][0] == 0) //시작점으로 못간다면 X
                return INF;
            else               //시작점으로 갈 수 있다면 O
                return map[x][0];
        }

        int tmp = INF;

        for(int i=0; i<n; i++){
            if((visit & (1 << i)) > 0 || map[x][i] == 0) continue; //i번 마을을 방문해왔거나, i번 마을로 가는 길이 없거나

            int result = DFS(i, visit | (1 << i)) + map[x][i]; //현재 정점에서 다음 정점으로 가는 길이 + 다음 DFS로 토스

            if(tmp > result) //result가 INF가 아닌 값을 받아왔다면
                tmp = result;
        }
        dp[x][visit] = tmp; //dp[x][visit] == 0 이라면 tmp가 들어가는 프로세스, 사실상 tmp = result, result의 의미?

        return tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(DFS(0, 1));
    }
}