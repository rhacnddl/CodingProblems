package org.gorany.backjoon.별찍기5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), mid = n - 1;

        for(int i=0; i<n; i++){
            for(int j=0; j<n + i; j++)
                System.out.print( (j >= mid - i)? "*" : " ");
            System.out.println();
        }
    }
}
