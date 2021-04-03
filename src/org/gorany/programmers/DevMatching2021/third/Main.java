package org.gorany.programmers.DevMatching2021.third;
/*출처: 프로그래머스 코딩 테스트 연습, https://programmers.co.kr/learn/challenges*/
import java.util.*;

class Solution {

    static int[] list = new int[10001], dp = new int[10001];
    static Map<String, Integer> map = new HashMap<>();

    static void DFS(int x, int cost){

        if(x == 0) return;

        int tmp = (cost / 10);

        if(tmp < 1)
            dp[x] += cost;
        else{
            dp[x] += cost - tmp;
            DFS(list[x], tmp);
        }
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        int n = enroll.length;
        int m = seller.length;

        for(int i=1; i<=n; i++)
            map.put(enroll[i-1], i);

        for(int i=1; i<=n; i++){
            String refer = referral[i-1];

            list[i] = refer.equals("-")? 0 :map.get(refer);
        }

        for(int i=0; i<m; i++){
            int idx = map.get(seller[i]);
            int cost = amount[i] * 100;

            DFS(idx, cost);
        }

        int[] answer = new int[n];
        for(int i=1; i<=n; i++)
            answer[i-1] = dp[i];

        return answer;
    }
}

public class Main {

    public static void main(String[] args) {

        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(enroll, referral, seller, amount)));
    }
}
/*
* SELECT * FROM PLACES WHERE HOST_ID IN
(SELECT P.HOST_ID FROM PLACES P GROUP BY HOST_ID HAVING COUNT(P.HOST_ID) > 1)
ORDER BY ID*/