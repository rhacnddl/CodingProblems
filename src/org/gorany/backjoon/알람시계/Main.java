package org.gorany.backjoon.알람시계;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int resHour = 0, resMin = 0;
        int hour = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());

        resMin = min - 45;
        resHour = hour;

        if(resMin < 0) {
            //resMin = (resMin == -45)? 25 : 60 - (45 - min) % 45;
            resMin += 60;
            resHour -= 1;

            if(resHour < 0)
                resHour = 23;
        }

        System.out.println(resHour + " " + resMin);
    }
}