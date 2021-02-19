package org.gorany.backjoon.수찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, find;
    static boolean flag;
    static void BS(int front, int back, int data){

        int mid = (front + back) / 2;

        int val = arr[mid];

        if(val == data){
            flag = true;
            return;
        }
        if(mid == front || mid == back) return;

        if(val < data)
            BS(mid, back, data);
        else
            BS(front, mid, data);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; st.hasMoreTokens(); i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        find = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i=0; st.hasMoreTokens(); i++)
            find[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        for(int i=0; i<find.length; i++){
            flag = false;
            BS(0, arr.length, find[i]);
            System.out.println(flag?1:0);
        }
    }
}
