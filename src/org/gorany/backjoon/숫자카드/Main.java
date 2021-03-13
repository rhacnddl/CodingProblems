package org.gorany.backjoon.숫자카드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] num = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
            num[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(num);

        int m = Integer.parseInt(br.readLine());
        boolean[] result = new boolean[m];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            int target = Integer.parseInt(st.nextToken());

            int left = 0, right = n-1;
            while(left <= right){

                int mid = (left + right) / 2;

                if(num[mid] >= target) {
                    if (num[mid] == target)
                        result[i] = true;

                    right = mid - 1;
                }
                else
                    left = mid + 1;
            }
            System.out.print(result[i]?"1 " : "0 ");
        }
    }
}
