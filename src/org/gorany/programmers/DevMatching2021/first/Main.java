package org.gorany.programmers.DevMatching2021.first;
/*출처: 프로그래머스 코딩 테스트 연습, https://programmers.co.kr/learn/challenges*/
import java.util.HashSet;
import java.util.Set;

class Solution {

    static Set<Integer> set = new HashSet<>();
    static int[] rank = {0, 6, 5, 4, 3, 2};

    public int[] solution(int[] lottos, int[] win_nums) {

        for(int n:win_nums)
            set.add(n);

        int zero = 0;
        int correct = 0;
        for(int n:lottos) {
            if (n == 0)
                zero++;
            if(set.contains(n))
                correct++;
        }

        int min = correct;
        int max = correct + zero;
        int[] answer = new int[2];

        if(min < 2)
            answer[1] = 6;
        else
            for(int i=1; i<6; i++)
                if(min == rank[i])
                    answer[1] = i;

        if(max < 2)
            answer[0] = 6;
        else
            for(int i=1; i<6; i++)
                if(max == rank[i])
                    answer[0] = i;

        return answer;
    }
}
/*
* 1등 : 6개 모두
* 2등 : 5개
* 3등 : 4개
* 4등 : 3개
* 5둥 : 2개
* 6등(낙첨) : else
* */
public class Main {

    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        //int[] lottos = {0,0,0,0,0,0};
        //int[] lottos = {45, 4, 35, 20, 3, 9};


    }
}
