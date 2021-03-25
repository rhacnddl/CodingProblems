package org.gorany.backjoon.문자열폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String origin = br.readLine();
        char[] chars = br.readLine().toCharArray();

        Stack<Character> stack = new Stack<>();

        while(true){
            boolean flag = true;
            StringBuilder sb = new StringBuilder();
            char[] arr = origin.toCharArray();

            for(int i=0; i<arr.length; i++){
                stack.push(arr[i]);

                if(stack.peek() == chars[ chars.length-1 ]){
                    boolean delete = true;
                    for(int j=i, idx=chars.length-1; j>i-chars.length; j--, idx--)
                        if(stack.elementAt(j) != chars[idx])
                            delete = false;

                    if(delete)
                        for(int j=0; j<chars.length; j++)
                            stack.pop();
                }
            }

            while(!stack.isEmpty()) {
                sb.append(stack.pop());
            }

            origin = sb.reverse().toString();
            if(flag) break;
        }
        System.out.println(origin.equals("")? "FRULA":origin);
    }
}