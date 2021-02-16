package org.gorany.backjoon.스타트링크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int F, S, G, U, D, result = 1000000000;
    static int[] floor, visit;

    static void DFS(int start, int count){

        visit[start]++;
        if(start == G){
            result = Math.min(result, count);
            return;
        }

        if(floor[start] > count){
            floor[start] = count++;

            int up = start + U;
            int down = start - D;

            if(up <= F && visit[up] < 2) DFS(up, count);
            if(down > 0 && visit[down] < 2) DFS(down, count);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //F:정점개수 S:Start G:Goal U: Up D:Down
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        floor = new int[F+1];
        visit = new int[F+1];
        Arrays.fill(floor, 1000000000);

        DFS(S, 0);

        System.out.println(result == 1000000000? "use the stairs":result);
    }
}