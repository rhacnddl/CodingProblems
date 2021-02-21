package org.gorany.backjoon.숫자판점프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static Set<String> set = new HashSet<>();
    static String[][] map = new String[5][5];
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static void DFS(int y, int x, int depth, String str){

        if(depth == 6) {
            set.add(str);
            return;
        }

        str += map[y][x];

        for(int a=0; a<4; a++){
            int ny = y+Y[a];
            int nx = x+X[a];

            if(ny < 0 || nx < 0 || ny > 4 || nx > 4) continue;

            DFS(ny, nx, depth + 1, str);
        }
    }

    static void printBit(int x){

        for(int i=16; i>0; i--)
            System.out.print((x & (1 << i-1)) == (1 << i-1)? 1:0);

        System.out.println();
    }
    static int setAllZero(int x){
        return x = 0;
    }
    static int setAllOne(int x){
        return x = -1;
    }

    public static void main(String[] args) throws IOException {
        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++)
                map[i][j] = st.nextToken();
        }

        for(int i=0; i<5; i++)
            for(int j=0; j<5; j++)
                DFS(i, j, 0, "");

        System.out.println(set.size());*/

        int a = 13;

        System.out.println("13's Binary Digit");
        printBit(a);

        System.out.println("모든 비트를 0으로 set");
        printBit(setAllZero(a));

        System.out.println("모든 비트를 1로 set");
        printBit(setAllOne(a));

        a = 13;
        System.out.println("3번째 Bit는 무엇일까?");
        System.out.println((a & (1 << 3)) == (1 << 3)? 1:0);

        System.out.println("5번째 Bit를 1로 만들기");
        a = 13;
        a |= (1 << 5);
        printBit(a);

        System.out.println("5번째 Bit를 0으로 만들기");
        a &= ~(1 << 5);
        printBit(a);

        System.out.println("5번째 Bit를 Toggle 시키기 (0 -> 1, 1 -> 0)");
        a ^= (1 << 5);
        printBit(a);

        System.out.println("마지막 원소 구하기");
        System.out.println(a & -a);

        System.out.println("마지막 원소 삭제하기");
        a &= (a-1);
        printBit(a);
    }
}