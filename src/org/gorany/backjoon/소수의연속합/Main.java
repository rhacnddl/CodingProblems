package org.gorany.backjoon.소수의연속합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static boolean isPrime(int x){
        if(x == 1) return false;

        for(int i=2; i<=Math.sqrt(x); i++)
            if(x % i == 0)
                return false;

        return true;
    }

    static int[] prime = new int[3000000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), idx = 0, cnt = 0;

        for(int i=1; i<=n; i++)
            if(isPrime(i))
                prime[idx++] = i;

        for(int i=0; i<idx; i++){
            int sum = 0;

            for(int j=i; j<idx; j++){
                sum += prime[j];

                if(sum >= n){
                    if(sum == n) cnt++;
                    break;
                }
            }
        }
        System.out.println(cnt);
    }
}