package org.gorany.backjoon.랜선자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] lan = new long[n];

        for(int i=0; i<n; i++)
            lan[i] = Long.parseLong(br.readLine());

        Arrays.sort(lan);

        long left = 1L, right = lan[n-1];
        long cnt, max = 0L;

        while(left <= right){
            long mid = (left + right) / 2L;

            cnt = 0L;

            for(int i=0; i<n; i++)
                cnt += (lan[i] / mid);

            if(cnt >= k) {
                if(max < mid)
                    max = mid;

                left = mid + 1;
            }
            else
                right = mid - 1;
        }
        System.out.println(max);
    }
}