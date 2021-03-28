package org.gorany.programmers.자물쇠와열쇠;

import java.io.IOException;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        Solution sol = new Solution();
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        System.out.println(sol.solution(key, lock));
    }
}
