package org.gorany.backjoon.예산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long arr[] = new long[n], sum = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int j=0; j<n; j++) {
            arr[j] = Integer.parseInt(st.nextToken());
            sum += arr[j];
        }

        Arrays.sort(arr);

        long limit = Integer.parseInt(br.readLine());

        if(sum <= limit){
            System.out.println(arr[n-1]);
            System.exit(0);
        }

        long left = 1, right = limit, max = 0;
        while(left <= right){

            long mid = (left + right) / n;
            sum = 0L;

            for(int i=0; i<n; i++)
                sum += Math.min(arr[i], mid);

            System.out.println("mid : " + mid + "  sum : " + sum);

            if(sum <= limit){

                max = Math.max(max, mid);

                right = (mid + 1) * n;
            }
            else
                left = (mid - 1) * n;
        }
        System.out.println(max);
    }
}