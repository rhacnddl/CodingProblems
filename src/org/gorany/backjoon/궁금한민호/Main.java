package org.gorany.backjoon.궁금한민호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map = new int[21][21];
    static boolean[][] origin = new boolean[21][21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }


        for(int k=1; k<=n; k++)
            for(int i=1; i<=n; i++)
                for (int j=1; j<=n; j++) {

                    if (i == j || i == k || j == k) continue;

                    if (map[i][j] == (map[i][k] + map[k][j]))
                        origin[i][j] = true;

                    else if (map[i][j] > (map[i][k] + map[k][j])) {
                        System.out.println(-1);
                        System.exit(0);
                    }
                }

        int sum = 0;

        for(int i=1; i<=n; i++)
            for (int j = 1; j <= n; j++)
                if(!origin[i][j]) sum += map[i][j];

        for(int i=1; i<=n; i++) {
            for (int j = 1; j <= n; j++)
                System.out.print(origin[i][j]? "* " : map[i][j] + " ");
            System.out.println();
        }

        System.out.println(sum / 2);
    }
}