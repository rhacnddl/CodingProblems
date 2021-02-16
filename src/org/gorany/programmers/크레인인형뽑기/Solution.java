package org.gorany.programmers.크레인인형뽑기;


import java.util.Stack;

public class Solution {

    static int solution(int[][] board, int moves[]){

        Stack<Integer> s = new Stack<>();
        int n = board.length;
        int answer = 0;

        /*for(int i=0; i<n; i++){
            for(int j=0; j<n; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }*/

        for(int i=0; i<moves.length; i++){

            int current = moves[i] - 1;

            for(int j=0; j<n; j++){
                if(board[j][current] != 0){

                    if(!s.isEmpty() && board[j][current] == s.peek()){
                        s.pop();
                        answer += 2;
                    }
                    else
                        s.push(board[j][current]);

                    board[j][current] = 0;
                    break;
                }
            }
        }

/*        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }*/

        return answer;
    }

    public static void main(String[] args) {

        int[][] board = {
                {0,0,0,0,0},
                {0,0,1,0,3},
                {0,2,5,0,1},
                {4,2,4,4,2},
                {3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};

        System.out.print(solution(board, moves));
    }
}
