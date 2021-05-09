package org.gorany.programmers.순위검색;

import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {

    static class Info{
        char lang;
        boolean back;
        boolean junior;
        boolean chick;
        int score;

        public Info(char lang, boolean back, boolean junior, boolean chick, int score) {
            this.lang = lang;
            this.back = back;
            this.junior = junior;
            this.chick = chick;
            this.score = score;
        }
    }

    public int[] solution(String[] info, String[] query) {

        Info[] infos = new Info[info.length];

        int idx = 0;
        for(String str : info){
            StringTokenizer st = new StringTokenizer(str, " ");

            char lang = st.nextToken().charAt(0);
            boolean back = st.nextToken().charAt(0) == 'b';
            boolean junior = st.nextToken().charAt(0) == 'j';
            boolean chick = st.nextToken().charAt(0) == 'c';
            int score = Integer.parseInt(st.nextToken());

            infos[idx++] = new Info(lang, back, junior, chick, score);
        }

        int[] answer = new int[query.length];

        for(int i=0; i< query.length; i++) {
            boolean[] person = new boolean[idx];

            String[] st = query[i].split(" and ");

            String lang = st[0];
            String field = st[1];
            String career = st[2];
            String[] strings = st[3].split(" ");
            String food = strings[0];
            int score = Integer.parseInt(strings[1]);

            if(lang.charAt(0) != '-') {
                char lang_ = lang.charAt(0);

                for (int j = 0; j < idx; j++) {
                    if (person[j]) continue;

                    if (lang_ != infos[j].lang)
                        person[j] = true;
                }
            }

            if(field.charAt(0) != '-'){
                boolean field_ = field.charAt(0) == 'b';

                for(int j=0; j<idx; j++){
                    if(person[j]) continue;

                    if(field_ != infos[j].back)
                        person[j] = true;
                }
            }

            if(career.charAt(0) != '-'){
                boolean career_ = career.charAt(0) == 'j';

                for(int j=0; j<idx; j++){
                    if(person[j]) continue;

                    if(career_ != infos[j].junior)
                        person[j] = true;
                }
            }

            if(food.charAt(0) != '-'){
                boolean food_ = food.charAt(0) == 'c';

                for(int j=0; j<idx; j++){
                    if(person[j]) continue;

                    if(food_ != infos[j].chick)
                        person[j] = true;
                }
            }

            for(int j=0; j<idx; j++){
                if(person[j]) continue;

                if(score > infos[j].score)
                    person[j] = true;
            }

            int cnt = 0;
            for(int j=0; j<idx; j++)
                if(!person[j])
                    cnt++;

            answer[i] = cnt;
        }

        return answer;
    }
}

class Main{
    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        System.out.println(Arrays.toString(sol.solution(info, query)));
    }
}