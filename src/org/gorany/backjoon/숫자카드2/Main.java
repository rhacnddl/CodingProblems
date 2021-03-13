package org.gorany.backjoon.숫자카드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int hashing(int x){
        if(x < 0) return x * (-1) + 10000000;
        return x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> list = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n], result = new int[20000001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());

            result[hashing(num[i])]++;
        }

        Arrays.sort(num);
        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {

            int target = Integer.parseInt(st.nextToken());
            int left = 0, right = n-1;
            boolean flag = false;

            while (left <= right) {

                int mid = (left + right) / 2;

                if (num[mid] == target) {
                    flag = true;
                    break;
                }

                if (num[mid] > target)
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            sb.append(flag? result[hashing(target)] + " " : "0 ");
        }
        System.out.println(sb.toString());
    }
}