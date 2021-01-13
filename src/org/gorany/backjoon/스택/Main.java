package org.gorany.backjoon.스택;

import java.util.Scanner;

class Stack{

    private int top;
    private int[] stack;

    public Stack(){
        top = -1;
        stack = new int[10001];
    }
    public void push(int item){ stack[++top] = item; }
    public int pop(){ return top != -1?stack[top--]:-1; }
    public int size(){ return top; }
    public int empty(){ return top == -1?1:0; }
    public int top(){ return top != -1?stack[top]:-1; }
}

public class Main {

    public static void main(String[] args) {

        Stack s = new Stack();
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        if(n < 1 && n > 10000) System.exit(1);

        for(int i=0; i<n; i++){
            String str = sc.nextLine();
            if(str.contains("push")){
                int item = Integer.parseInt(str.substring(str.lastIndexOf(" ") + 1));
                if(!(item < 1 && item > 100000))
                    s.push(item);
            }
            else if(str.equals("pop"))
                System.out.println(s.pop());
            else if(str.equals("top"))
                System.out.println(s.top());
            else if(str.equals("size"))
                System.out.println(s.size());
            else if(str.equals("empty"))
                System.out.println(s.empty());
        }

    }
}
