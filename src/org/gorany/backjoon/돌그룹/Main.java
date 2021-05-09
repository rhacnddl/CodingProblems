package org.gorany.backjoon.돌그룹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[][][] visit = new boolean[501][1001][1501];

    static int BFS(int[] start){
        Queue<int[]> Q = new LinkedList<>();

        visit[start[0]][start[1]][start[2]] = true;
        Q.add(start);

        while(!Q.isEmpty()){
            int[] val = Q.poll();

            if(val[0] == val[2]) return 1;

            for(int a=0; a<3; a++){
                int[] nVal = {val[0], val[1], val[2]};
                int cur = a;
                int next = (cur+1) % 3;

                if(nVal[cur] == nVal[next]) continue;
                if(next == 0){
                    cur = 0;
                    next = 2;
                }

                nVal[next] -= nVal[cur];
                nVal[cur] *= 2;

                Arrays.sort(nVal);
                if(!visit[nVal[0]][nVal[1]][nVal[2]]){
                    visit[nVal[0]][nVal[1]][nVal[2]] = true;
                    Q.add(nVal);
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] val = new int[3];
        for(int i=0; i<3; i++)
            val[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(val);
        System.out.println(BFS(val));
    }
}