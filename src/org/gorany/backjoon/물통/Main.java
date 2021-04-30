package org.gorany.backjoon.물통;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Water{
        int x, y, move;
        public Water(int yy, int xx, int m){
            y=yy; x=xx; move=m;
        }
    }
    static int a, b, finA, finB;
    static boolean[][] visit = new boolean[100001][2];



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        finA = Integer.parseInt(st.nextToken());
        finB = Integer.parseInt(st.nextToken());


    }
}
