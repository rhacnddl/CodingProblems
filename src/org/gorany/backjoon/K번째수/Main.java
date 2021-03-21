package org.gorany.backjoon.K번째수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long[] B = new long[n * n + 1];

        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++) {
                B[j + (i-1) * n] = (i * j);
            }
        System.out.println(Arrays.toString(B));
        Arrays.sort(B);
        System.out.println(Arrays.toString(B));

        int left = 0, right = k;

        while(left <= right){
            int mid = (left + right) / 2;

            if(mid == k){
                System.out.println(B[mid]);
                break;
            }
            else if(mid < k)
                left = mid + 1;
            else
                right = mid - 1;
        }
    }
}