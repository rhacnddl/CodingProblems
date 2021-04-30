package org.gorany.backjoon.전단지돌리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n, s, d;
    static List<Integer>[] list = new ArrayList[100001], reList = new ArrayList[100001];
    static int[] dp = new int[100001];
    static boolean[] visit = new boolean[100001];

    static void DFS(int x, int depth){

        dp[x] = depth;

        for(int next:list[x])
            DFS(next, depth + 1);
    }

    static int DFS_(int x, int depth){

        if(x == s || visit[x])
            return depth + 1;

        int result = 0;
        visit[x] = true;

        for(int next:reList[x])
            result = DFS_(next, depth + 1);


        return result;
    }
//depth - d < 0 : cnt++
//depth - d >=0 : visit[depth - d]? "":visit[depth-d] true, cnt++;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
            reList[i] = new ArrayList<>();
        }

        for(int i=1; i<n; i++){
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            list[v1].add(v2);
            reList[v2].add(v1);
        }

        DFS(s, 0);

        int cnt = 0;

        for(int i=1; i<=n; i++){
            int val = dp[i] - d;

            if(val < 0) continue;
            else{
                if(!visit[val]) {
                    cnt += DFS_(i, 0);
                }
            }
        }

        System.out.println(cnt * 2);

        for(int i=1; i<=n; i++)
            System.out.print(dp[i] + " ");
    }
}