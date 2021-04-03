package org.gorany.backjoon.등수찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[][] list = new ArrayList[100001][2];
    static boolean[] visit = new boolean[100001];

    static int DFS(int x, int idx){

        visit[x] = true;
        int rank = 1;

        for(int next:list[x][idx])
            if(!visit[next])
                rank += DFS(next, idx);

        return rank;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++) {
            list[i][0] = new ArrayList<>();
            list[i][1] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            list[v2][0].add(v1); //가장 높은
            list[v1][1].add(v2); //가장 낮은
        }

        int u = DFS(x, 0);

        //visit = new boolean[100001];

        int v = n - DFS(x, 1) + 1;

        System.out.println(u + " " + v);
    }
}
/*11 10 1
1 2
2 6
6 7
7 8
3 2
4 2
5 2
9 1
10 5
11 10*/