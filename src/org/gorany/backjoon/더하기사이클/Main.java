package org.gorany.backjoon.더하기사이클;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int copy = n, cnt = 0;

        do{
            cnt++;
            StringBuilder sb = new StringBuilder();

            if(copy < 10)
                sb.append(0);

            sb.append(copy);

            int plus = (copy / 10) + (copy % 10);

            sb = sb.delete(0, 1).append(plus % 10);
            copy = Integer.parseInt(sb.toString());
        }while(copy != n);

        System.out.println(cnt);
    }
}
