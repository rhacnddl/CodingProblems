package org.gorany.programmers.순위;

class Solution {

    static boolean[][] dist = new boolean[101][101];

    public int solution(int n, int[][] results) {

        for(int[] arr:results)
            dist[arr[0]][arr[1]] = true;

        for(int i=1; i<=n; i++) {
            for (int j = 1; j <= n; j++)
                System.out.print(dist[i][j]? "T " : "F ");
            System.out.println();
        }
System.out.println();
        for(int k=1; k<=n; k++)
            for(int i=1; i<=n; i++)
                for(int j=1; j<=n; j++)
                    if(dist[i][k] && dist[k][j])
                        dist[i][j] = true;

        int answer = 0;
        for(int i=1; i<=n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++)
                if (dist[i][j] || dist[j][i])
                    cnt++;

                if(cnt == n-1)
                    answer++;
        }

        for(int i=1; i<=n; i++) {
            for (int j = 1; j <= n; j++)
                System.out.print(dist[i][j]? "T " : "F ");
            System.out.println();
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) {

        Solution sol = new Solution();

        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        System.out.println(sol.solution(n, results));
    }
}