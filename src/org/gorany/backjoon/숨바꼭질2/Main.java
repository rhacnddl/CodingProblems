package org.gorany.backjoon.숨바꼭질2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    static int n, k;
    static final int MAX = 100000;
    static Queue<Integer> Q = new LinkedList<>();
    static int[] visit = new int[MAX+1];
    static int[] X = {-1, 1, 0};
    static final int INF = 1000000000;
    static int cnt = 0;

    static void BFS(int start){

        visit[start] = 0;
        Q.add(start);

        while(!Q.isEmpty()){
            int current = Q.poll();

            X[2] = current;
            for(int i=0; i<3; i++){
                int next = current + X[i];

                //1) 범위를 반드시 만족하고
                //2) 다음 방문할 정점에 기록된 값이 현재 정점까지의 시간 + 1초 보다 작거나 같으면
                //3) 값을 갱신하고 큐에 넣는다.
                //4) 이 때 다음 방문할 정점이 k(도착점)이면 cnt++;
                if(next >= 0 && next <= MAX)
                    if(visit[next] >= visit[current] + 1){

                        if(next == k) cnt++;
                        visit[next] = visit[current] + 1;
                        Q.add(next);
                    }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        IntStream.rangeClosed(0, MAX).forEach(i->visit[i] = INF);

        if(n != k) {
            BFS(n);
            System.out.printf("%d\n%d\n", visit[k], cnt);
        }
        //만약 n == k 일 때 출력값은 0 1이 되어야 한다.
        else
            System.out.printf("%d\n%d\n", 0, 1);
    }
}
