package org.gorany.backjoon.탈출홍대;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int t, g;
    static boolean[] visit = new boolean[100000];
    static int[] click = new int[100000];

    static int B(int x){

        int result = 0, idx = 10;
        int[] arr = new int[5];

        for(int i=0, k=10000; i<5; i++, k /= 10) {
            arr[i] = (x / k) % 10;

            if(arr[i] != 0)
                idx = Math.min(idx, i);
        }

        arr[idx]--;

        for(int i=0, k=10000; i<5; i++, k/=10)
            result += arr[i] * k;

        return result;
    }

    static int BFS(int n){

        int result = -1;
        Queue<Integer> Q = new LinkedList<>();
        visit[n] = true;
        Q.add(n);

        while(!Q.isEmpty()){
            int cur = Q.poll();

            if(cur == g)
                result = click[cur];

            if(click[cur] + 1 > t) continue;

            int a = cur + 1;
            if(a < 100000 && !visit[a]){
                visit[a] = true;
                click[a] = click[cur] + 1;
                Q.add(a);
            }

            int b = cur * 2;

            if(b > 0 && b < 100000){
                b = B(b);
                if(!visit[b]) {
                    visit[b] = true;
                    click[b] = click[cur] + 1;
                    Q.add(b);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());

        int res = BFS(n);
        System.out.println(res != -1? res : "ANG");
    }
}