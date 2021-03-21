package org.gorany.backjoon.외판원순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    static final int INF = 1000000000;
    static int[][] map = new int[16][16];
    static int[][] dp = new int[16][1 << 16];
    static int n;

    static int TSM(int current, int visit){

        if(dp[current][visit] != 0)
            return dp[current][visit];

        if(visit == (1 << n) - 1){ //모든 정점을 방문하여서 다시 출발점으로 돌아가야 할 때
            if(map[current][0] == 0) //현재 정점에서 출발점으로 못간다면
                return INF;
            else
                return map[current][0]; //현재 정점에서 출발점으로 갈 수 있다면
        }

        int tmp = INF;

        for(int i=0; i<n; i++){
            if((visit & (1 << i)) != 0 || map[current][i] == 0) continue; //이미 다음 정점을 방문했거나, 다음 정점으로의 길이 없다면 skip

            int result = map[current][i] + TSM(i, visit | (1 << i)); //현재 -> 다음 정점으로의 길이와 다음 정점에서의 과정 재귀호출

            if(result < tmp) //result가 INF가 아닌 값을 받아왔다면
                tmp = result;
        }

        return dp[current][visit] = tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(TSM(0, 1));

        for(int i=0; i<n; i++) {
            for(int j=0; j<(1<<n); j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
    }
}
