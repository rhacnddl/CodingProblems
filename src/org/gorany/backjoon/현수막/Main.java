package org.gorany.backjoon.현수막;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map = new int[251][251];
    static int[] Y = {-1,1,0,0}, X={0,0,-1,1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        for(int i=1; i<=r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=c; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
