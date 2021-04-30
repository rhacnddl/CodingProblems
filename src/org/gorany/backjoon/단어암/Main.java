package org.gorany.backjoon.단어암;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr = new int[10001];

    static int know = (1<<26) - 1;
    static boolean isKnow(int idx){

        return (arr[idx] & know) == arr[idx];
    }
    static void disassemble(String word, int idx){

        for(char c:word.toCharArray())
            arr[idx] |= (1 << c-'a');
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) {
            String word = br.readLine();
            disassemble(word, i);
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int v = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            know = (v==1)? know & ~(1 << c-'a'): know | (1 << c-'a');

            int cnt = 0;
            for(int j=0; j<n; j++)
                if(isKnow(j))
                    cnt++;

            sb.append(cnt).append('\n');
        }
        System.out.print(sb);
    }
}