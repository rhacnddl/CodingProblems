package org.gorany.backjoon.문자열폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String origin = br.readLine();
        char[] bomb = br.readLine().toCharArray();
        int bombSize = bomb.length;

        char[] arr = origin.toCharArray();

        int top = -1;
        char[] stack = new char[1000001];
        for (char c : arr) {
            stack[++top] = c;

            //현재 문자가 폭발 문자열의 가장 마지막 꺼와 같다면 and stack의 크기가 폭발문자열 크기 이상이어야함
            if (stack[top] == bomb[bombSize - 1] && top >= bombSize - 1) {

                boolean flag = true;
                //한 문자씩 뒤로 가며 폭발 문자와 비교 (폭발 문자열과 다르면 flag = false)
                for (int j = bombSize - 1, z = 0; j >= 0; j--, z++)
                    if (stack[top - z] != bomb[j]) {
                        flag = false;
                        break;
                    }

                if (flag) //폭발 문자열이 존재하면 폭파 (top을 줄이면된다)
                    top -= bombSize;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<=top; i++)
            sb.append(stack[i]);

        origin = sb.toString();

        System.out.println(origin.equals("")? "FRULA":origin);
    }
}