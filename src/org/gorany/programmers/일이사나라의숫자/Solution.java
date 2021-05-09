package org.gorany.programmers.일이사나라의숫자;

class Solution {
    public String solution(int n) {

        StringBuilder sb = new StringBuilder();
        int div = 1;

        while(true){
            int mok = n / div;

            if(mok == 0) break;

            int remain = mok % 3;

            if(remain == 0) remain = 3;

            n -= (remain * div);
            div *= 3;

            sb.insert(0, remain == 3? 4 : remain);
        }

        return sb.toString();
    }
}

class Main{
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solution(10));
    }
}