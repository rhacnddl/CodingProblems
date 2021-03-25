package org.gorany.backjoon.ATM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] time = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
            time[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(time);

        int tmp = 0, sum = 0;
        for(int i=0; i<n; i++) {
            tmp += time[i];
            sum += tmp;
        }

        System.out.println(sum);
    }
}