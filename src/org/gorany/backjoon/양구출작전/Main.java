package org.gorany.backjoon.양구출작전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static long[] sheeps = new long[123457];
    static List<Integer>[] list = new List[123457];

    static long DFS(int x, long sheep){

        boolean isLeaf = true;

        long result = sheep;

        for(int next:list[x]){
            isLeaf = false;
            sheep += DFS(next, result);
        }

        if(isLeaf && sheeps[x] < 0)    return sheep;
        else if(sheep + sheeps[x] < 0) return 0;
        else                           return sheep + sheeps[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++)
            list[i] = new ArrayList<>();

        for(int i=2; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            char c = st.nextToken().charAt(0);
            int quantity = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            sheeps[i] = c == 'S'? quantity : -quantity;
            list[target].add(i);
        }
        System.out.println(DFS(1, 0));
    }
}
/*
* 7
S 300 1
W 500 2
S 600 3
S 300 3
S 700 1
W 1000 6
*
* 9
S 100 1
W 500 2
S 500 2
S 300 3
S 300 3
S 700 1
W 1000 7
S 300 8*/