package org.gorany.programmers.문자열내맘대로정리하기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    static class Word implements Comparable<Word>{

        String word;
        char c;

        public Word(String w, char c){
            word=w;
            this.c = c;
        }

        @Override
        public int compareTo(Word o) {

            return c - o.c;
        }
    }

    public static String[] solution(String[] strings, int n) {

        Arrays.sort(strings);

        for(int i=0; i<strings.length; i++)
            for(int j=1; j<strings.length; j++) {

                String front = strings[j];
                String before = strings[j - 1];
                String tmp = "";

                if (front.charAt(n) < before.charAt(n)) {
                    tmp = strings[j];
                    strings[j] = strings[j - 1];
                    strings[j - 1] = tmp;
                }
            }

        String[] answer = strings;
        return answer;
    }
/*    public static String[] solution(String[] strings, int n) {

        Arrays.sort(strings);

        List<Word> list = new ArrayList<>();

        for(int i=0; i<strings.length; i++) {
            String word = strings[i];
            char character = strings[i].charAt(n);

            list.add(new Word(word, character));
        }

        Collections.sort(list);
        String[] answer = new String[list.size()];

        int idx = 0;
        for(Word w : list)
            answer[idx++] = w.word;

        return answer;
    }*/

    public static void main(String[] args) {

        String[] str = {"abce", "abcd", "cdx"};
        int n = 2;

        System.out.println(Arrays.toString(solution(str, n)));
    }
}