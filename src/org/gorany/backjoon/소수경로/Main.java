package org.gorany.backjoon.소수경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[] visit;
    static String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    static int[] move;
    static boolean isPrime(int x){

        for(int i=2; i<=Math.sqrt(x); i++)
            if(x % i == 0)
                return true;

        return false;
    }

    static void BFS(int s, int e){

        Queue<Integer> Q = new LinkedList<>();
        visit[s] = true;
        move[s] = 0;

        Q.add(s);

        while(!Q.isEmpty()){
            int cur = Q.poll();

            for(int a=0; a<10; a++)
                for(int b=0; b<4; b++){

                    StringBuilder sb = new StringBuilder(Integer.toString(cur));

                    sb.replace(b, b+1, nums[a]);

                    int next = Integer.parseInt(sb.toString());

                    if(next < 1000 || next > 9999 || visit[next] || isPrime(next)) continue;

                    visit[next] = true;
                    move[next] = move[cur] + 1;
                    if(next == e) return;

                    Q.add(next);
                }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            visit = new boolean[10000];
            move = new int[10000];
            Arrays.fill(move, -1);

            BFS(a, b);

            System.out.println(move[b] == -1? "impossible" : move[b]);
        }
    }
}
