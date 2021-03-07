package org.gorany.backjoon.퍼즐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Item{
        String str;
        int move, y, x;

        public Item(String s, int m, int yy, int xx){
            str=s;
            move=m;
            y=yy;
            x=xx;
        }
    }

    static int[][] strToArr(String str){
        int[][] arr = new int[3][3];

        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                arr[i][j] = str.charAt(j + (i * 3)) - '0';

        return arr;
    }
    static int[][] copyArr(int[][] src){
        int[][] result = new int[3][3];
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                result[i][j] = src[i][j];

        return result;
    }
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static int result = -1;

    static void BFS(String s, int yy, int xx){

        Set<String> set = new HashSet<>();
        Queue<Item> Q = new LinkedList<>();

        set.add(s);
        Q.add(new Item(s, 0, yy, xx));

        while(!Q.isEmpty()){
            Item item = Q.poll();

            String current = item.str;

            //String -> Array
            int[][] curArr = strToArr(current);

            int y = item.y;
            int x = item.x;
            int move = item.move;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                if(ny < 0 || nx < 0 || ny > 2 || nx > 2) continue;

                //copy Array
                int[][] arr = copyArr(curArr);

                //swap
                int tmp = arr[ny][nx];
                arr[ny][nx] = arr[y][x];
                arr[y][x] = tmp;

                //Array -> String
                StringBuilder next = new StringBuilder();

                for(int i=0; i<3; i++)
                    for(int j=0; j<3; j++)
                        next.append(arr[i][j]);

                if(set.add(next.toString())) {
                    if(set.contains("123456780")){
                        result = move + 1;
                        return;
                    }
                    Q.add(new Item(next.toString(), move + 1, ny, nx));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "";
        int y=0, x=0;

        for(int i=0; i<3; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                String s = st.nextToken();
                str += s;

                if(s.equals("0")){
                    y=i; x=j;
                }
            }
        }
        if(str.equals("123456780"))
            System.out.println(0);
        else{
            BFS(str, y, x);
            System.out.println(result);
        }
    }
}