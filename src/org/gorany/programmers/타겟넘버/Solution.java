package org.gorany.programmers.타겟넘버;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int cnt = 0;
    static void DFS(int[] numbers, int num, int idx, int sum){
        System.out.println("idx : " + idx + " sum : " + sum + " num : " + num);
        if(idx == numbers.length){
            if(sum == num) cnt++;
            return;
        }
        DFS(numbers, num, idx + 1, sum + numbers[idx]);
        DFS(numbers, num, idx + 1, sum - numbers[idx]);
    }

    static public int solution(int[] numbers, int target){

        DFS(numbers, target, 0, 0);

        return cnt;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int a=0;
        int numbers[] = new int[st.countTokens()];

        while(st.hasMoreTokens())
            numbers[a++] = Integer.parseInt(st.nextToken());

        int target = Integer.parseInt(br.readLine());

        System.out.println(solution(numbers, target));
    }
}