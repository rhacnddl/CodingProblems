package org.gorany.backjoon.막대기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());

        int[] arr = new int[1 << 4];
        arr[0] = 1 << 6;

        int idx = 1, result = -1;
        while(true){
            boolean flag = false;

            int sum = 0, cnt = 0;
            for(int i=0; i<idx; i++){
                sum += arr[i];
                cnt++;
                if(sum == x){
                    result = cnt;
                    flag = true;
                }
            }
            if(flag) break;

            idx++;

            if(sum > x)
                idx--;

            arr[idx] = (arr[idx-1] >> 1);
            arr[idx-1] = (arr[idx-1] >> 1);
        }
        System.out.println(result);
    }
}