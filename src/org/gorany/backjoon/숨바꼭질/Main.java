package org.gorany.backjoon.숨바꼭질;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 1000001;
    static int[] arr = new int[MAX];

    static Queue<Integer> Q = new LinkedList<>();
    static void BFS(int n, int k){

        arr[n] = 1;
        Q.add(n);

        while(!Q.isEmpty() && arr[k] == 0) {
            int present = Q.poll();
            //System.out.println("현재 : " + present + ", arr["+present+"] = " + arr[present]);

            int front = present + 1;
            int back = present - 1;
            int warp = present * 2;

            if(front >= 0 && front < MAX)
                if(arr[front] == 0){
                    arr[front] = arr[present] + 1;
                    Q.add(front);
                }
            if(back >= 0 && back < MAX)
                if(arr[back] == 0){
                    arr[back] = arr[present] + 1;
                    Q.add(back);
                }
            if(warp >= 0 && warp < MAX)
                if(arr[warp] == 0) {
                    arr[warp] = arr[present] + 1;
                    Q.add(warp);
                }
        }
        System.out.println(arr[k]-1);
    }
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();
        StringTokenizer stk = new StringTokenizer(str);

        int n = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        BFS(n, k);
    }
}
