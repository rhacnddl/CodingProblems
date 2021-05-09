package org.gorany.programmers.GPS;

import java.util.ArrayList;
import java.util.List;

class Solution {

    static int N, K, result = Integer.MAX_VALUE;
    static int[] gps;
    static List<Integer>[] list;

    static void DFS(int cur, int idx, int change){

        if(idx == K) {
            if(cur == gps[idx])
                result = Math.min(result, change);

            return;
        }

        for(int next: list[cur]){
            if(next == gps[idx + 1]){
                DFS(next, idx + 1, change);
            }
            else{
                DFS(next, idx + 1, change + 1);
            }
        }
    }

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {

        N = n;
        K = k - 1;
        gps = gps_log;

        list = new ArrayList[201];

        /* 자기 자신에 머무를 수 있음*/
        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
            list[i].add(i);
        }

        /* 이동 가능한 도로 */
        for(int[] c: edge_list){
            list[c[0]].add(c[1]);
            list[c[1]].add(c[0]);
        }

        //System.out.println("K : " + K + ", N : " + N);

        DFS(gps[0], 0, 0);

        return result == Integer.MAX_VALUE? -1 : result;
    }
}

class Main{
    public static void main(String[] args) {
        Solution sol = new Solution();

        int n = 7;
        int m = 10;
        int[][] edge_list = {{1,2}, {1,3}, {2,3}, {2,4}, {3,4}, {3,5}, {4,6}, {5,6}, {5,7}, {6,7}};
        int k = 6;
        int[] gps_log = {1,2,3,3,6,7};

        System.out.println(sol.solution(n, m, edge_list, k, gps_log));
    }
}
