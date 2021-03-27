package org.gorany.backjoon.균형잡힌세상;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[] bracket = {'[', '(', ')', ']'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            String origin = br.readLine();
            char[] arr = origin.toCharArray();

            if(arr[0] == '.') break;

            int top = -1;
            char[] stack = new char[100];

            for(char c:arr)
                for(int a=0; a<4; a++)
                    if(c == bracket[a]) {
                        if (top != -1 && (c - stack[top] == 2 || c - stack[top] == 1))
                            --top;
                        else
                            stack[++top] = c;
                    }

            sb.append(top == -1? "yes":"no").append('\n');
        }
        System.out.println(sb);
    }
}