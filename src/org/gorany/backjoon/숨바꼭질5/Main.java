package org.gorany.backjoon.숨바꼭질5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int cur, target, speed, even;
        public Point(int c, int t, int s, int e){
            cur=c;
            target=t;
            speed=s;
            even = e;
        }
        /*
        * even == 0 짝수
        * even == 1 홀수
        * */
    }
    static int n, k;
    static int[] time[] = new int[500001][2], dir = {-1, 1, 0};
    static boolean[][] visit = new boolean[500001][2];

    static int BFS(){
        Queue<Point> Q = new LinkedList<>();

        visit[n][0] = true;
        Q.add(new Point(n, k, 0, 0));

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int cur = p.cur;
            int target = p.target;
            int speed = p.speed;
            int even = p.even;

            if(cur == target)
                return time[target][even];

            dir[2] = cur;
            for(int a=0; a<3; a++){
                int next = cur + dir[a];
                int nTarget = target + (speed + 1);
                int nEven = even == 1? 0 : 1;

                if(next >= 0 && next <= 500000 && nTarget <= 500000) {
                    if(visit[nTarget][nEven]) //동생이 홀수/짝수 시간에 다음 정점에 도착하고 수빈이 홀수/짝수 시간에 다음 정점을 방문했다면
                        return speed + 1;

                    if(!visit[next][nEven]) {
                        time[next][nEven] = time[cur][even] + 1;
                        visit[next][nEven] = true;
                        Q.add(new Point(next, nTarget, speed + 1, nEven));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        System.out.println(k >= 0 && k <= 500000? BFS() : -1);
    }
}