package org.gorany.backjoon.텀프로젝트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int N = 100001;
    static Set<Integer> set;
    static Queue<Integer> q;
    static int[] list;
    static boolean[] solo, visit;
    static boolean flag;

    static void DFS(int next){

        visit[next] = true;
        q.add(next);
        set.add(next);

        int v = list[next];

        if(!set.add(v)) //값이 중복하게 있다면
            while(!q.isEmpty()) {
                if (q.peek() == v) { //그 값이 나올 때 까지 dequeue
                    flag = true;
                    break;
                }
                q.poll();
            }

        if(!visit[v])
            DFS(v);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){

            int n = Integer.parseInt(br.readLine());

            set = new HashSet<>();
            q = new LinkedList<>();
            solo = new boolean[N];
            visit = new boolean[N];
            list = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int j = 1;
            while(st.hasMoreTokens()){
                int num = Integer.parseInt(st.nextToken());

                if(num == j){
                    visit[j] = true;
                    solo[j] = true;
                }

                list[j++] = num;
            }

            for(int a=1; a<=n; a++)
                if(!visit[a]) {
                    flag = false;
                    DFS(a);
                    if(flag)
                        while(!q.isEmpty())
                            solo[q.poll()] = true;
                    q.clear();
                    set.clear();
                }

            int sum = 0;
            for(int a=1; a<=n; a++)
                if(!solo[a]) sum++;

            System.out.println(sum);
        }
    }
}