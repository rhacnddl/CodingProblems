/*
package org.gorany.backjoon.양팔저울;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] w;
    static int[] biz;
    static int[][] dp;
    static boolean[] res;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        w = new int[n+1];
        dp = new int[n+1][n+1];

        st = new StringTokenizer(br.readLine());

        for(int a=1; a<=n; a++){
            w[a] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        biz = new int[m];
        res = new boolean[m];

        st = new StringTokenizer(br.readLine());

        for(int a=0; a<m; a++)
            biz[a]=Integer.parseInt(st.nextToken());

        for(int a=0; a<m; a++)
            for(int y=0; y<=n; y++) {
                for (int x = y; x <= n; x++) {
                    if (dp[y][x] == 0)
                        dp[y][x] = -w[y] + w[x];

                    if (dp[y][x] == biz[a]) {
                        res[a] = true;
                    }
                }
                if(res[a] == true) break;
            }

        for(int a=0; a<m; a++)
            System.out.print(res[a] + " ");
    }
}
*/
