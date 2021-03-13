package org.gorany.backjoon.나무자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        long[] tree = new long[(int)n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
            tree[i] = Long.parseLong(st.nextToken());

        Arrays.sort(tree);

        long start = 0, end = tree[(int)n-1], max = 0;

        while(start <= end){

            long mid = (start + end) / 2;
            long sum = 0L;

            for(int i=0; i<n; i++) {

                if (tree[i] <= mid) continue;

                sum += tree[i] - mid;
            }

            if(sum >= m){

                if(max < mid)
                    max = mid;

                start = mid + 1;
            }
            else
                end = mid - 1;
        }

        System.out.println(max);
    }
}