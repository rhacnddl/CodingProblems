package org.gorany.programmers.네트워크;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static class Point{
        int y, x;
        public Point(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
    static int cnt = 1;
    static int[] visit;

    static void DFS(int n, int[][] computers, int idx){

        visit[idx] = cnt;

        for(int i=0;i<n;i++){
            if(visit[i] == 0 && computers[idx][i] == 1)
                DFS(n, computers, i);
        }
    }

    static int solution(int n, int[][] computers) {

        visit = new int[n];
        DFS(n, computers, 0);
        for(int i=0; i<n; i++){
            if(visit[i] == 0)
                cnt++;
            DFS(n, computers, i);
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int computers[][] = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
                computers[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, computers));
    }
}
