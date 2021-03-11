package org.gorany.backjoon.전화번호목록;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){
            int n = Integer.parseInt(br.readLine());

            String[] strs = new String[n];
            for(int j=0; j<n; j++)
                strs[j] = br.readLine();

            boolean flag = true;

            first:
            for(int a=0; a<n; a++){
                String cur = strs[a];

                for(int b=a; b<n; b++){
                    String next = strs[b];
                    if(a == b || cur.length() == next.length()) continue;

                    if(cur.length() < next.length()) {
                        if (next.startsWith(cur))
                            flag = false;
                    }
                    else {
                        if(cur.startsWith(next))
                            flag = false;
                    }

                    if(!flag)
                        break first;
                }
            }
            System.out.println(flag? "YES":"NO");
        }
    }
}