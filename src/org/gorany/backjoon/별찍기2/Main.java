package org.gorany.backjoon.별찍기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

/*    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), i, j, k;

        for(i=0; i<n; i++){ //n회 반복
            int mid = (n - 1) - i; //공백을 찍을 개수

            for(j=0; j<mid; j++) //mid회 반복
                System.out.print("*");

            for(k=mid; k<n; k++) //n - mid회 반복
                System.out.print(" ");

            System.out.println();
        }
    }*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), i, k;

        for(i=0; i<n; i++){
            int mid = (n - 1) - i; //별을 찍을 개수

            for(k=0; k<n; k++)
                System.out.print(k < mid? " " : "*");

            System.out.println();
        }
    }
}