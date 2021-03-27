package org.gorany.backjoon.A와B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static String s, t;

    static boolean BFS(){

        int length = s.length();
        boolean possible = false;
        Queue<String> Q = new LinkedList<>();

        Q.add(t);

        while(!Q.isEmpty()){
            String cur = Q.poll();
            int curLen = cur.length();

            if(cur.length() < length) break;
            if(cur.length() == length && cur.equals(s)) return true;

            StringBuilder sb = new StringBuilder(cur);

            if(cur.charAt(curLen - 1) == 'A')
                sb.deleteCharAt(curLen-1);

            else if(cur.charAt(curLen - 1) == 'B'){
                sb.deleteCharAt(curLen-1);
                sb.reverse();
            }

            Q.add(sb.toString());
        }
        return possible;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        t = br.readLine();

        System.out.println(BFS()? 1:0);
    }
}