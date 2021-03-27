package org.gorany.backjoon.듣보잡;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();


        for(int i=0; i<n; i++)
            set.add(br.readLine());

        int idx = 0;
        for(int i=0; i<m; i++){
            String name = br.readLine();

            if(set.contains(name))
                list.add(name);
        }
        Collections.sort(list);

        System.out.println(list.size());
        StringBuilder sb = new StringBuilder();
        for(String name : list)

            sb.append(name).append('\n');
        System.out.println(sb);
    }
}