package org.gorany.backjoon.경로찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 1000000000;
    static int[][] map = new int[101][101];

    static void FloydWarshall(int n){

        for(int k=1; k<=n; k++)
            for(int i=1; i<=n; i++)
                for(int j=1; j<=n; j++)
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                int a = Integer.parseInt(st.nextToken());
                map[i][j] = a == 0? INF:a;
            }
        }

        FloydWarshall(n);

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++)
                System.out.print(map[i][j] != INF? 1+" ":0+" ");
            System.out.println();
        }
    }
}