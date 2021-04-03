package org.gorany.programmers.DevMatching2021.second;
/*출처: 프로그래머스 코딩 테스트 연습, https://programmers.co.kr/learn/challenges*/
import java.util.Arrays;

class Solution {

    static int startY, startX, endY, endX;
    static int[][] origin = new int[101][101], map = new int[101][101];
    static boolean[][] visit;

    static void DFS(int y, int x, int before){

        visit[y][x] = true;

        int tmp = map[y][x];
        map[y][x] = before;

        int ny = 0, nx = 0;

        //To Right
        if(y == startY && x != endX){
            ny = y;
            nx = x + 1;
        }
        //To Down
        else if(y != endY && x == endX){
            ny = y + 1;
            nx = x;
        }
        //To Left
        else if(y == endY && x != startX){
            ny = y;
            nx = x - 1;
        }
        //To Up
        else{
            ny = y - 1;
            nx = x;
        }

        if(visit[ny][nx] || ny == 0 || nx == 0) return;

        DFS(ny, nx, tmp);
    }

    public int[] solution(int rows, int columns, int[][] queries) {

        int m = queries.length;
        int index = 0;
        int[] answer = new int[m];

        /* init */
        for(int i=1; i<=rows; i++)
            for(int j=1; j<=columns; j++){
                origin[i][j] = (i-1) * columns + j;
                map[i][j] = (i-1) * columns + j;
            }

        /* rotation loop */
        for(int i=0; i<m; i++){
            startY = queries[i][0];
            startX = queries[i][1];
            endY = queries[i][2];
            endX = queries[i][3];

            visit = new boolean[101][101];
            int tmp = map[startY + 1][startX];

            /* rotation */
            DFS(startY, startX, tmp);

            int idx = 0;
            int[] result = new int[10001];

            /* compare between origin and map */
            for(int a=1; a<=rows; a++)
                for(int b=1; b<=columns; b++)
                    if(origin[a][b] != map[a][b])
                        result[idx++] = origin[a][b];

            /* find Minimum & save to answer[] */
            int min = 1000000000;
            for(int a=0; a<idx; a++)
                min = Math.min(min, result[a]);

            answer[index++] = min;

            /* update origin */
            for(int a=1; a<=rows; a++)
                for(int b=1; b<=columns; b++)
                    origin[a][b] = map[a][b];
        }
        return answer;
    }
}

public class Main {

    public static void main(String[] args) {
        int rows = 6, cols = 6;
        int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};

        //int rows = 3, cols = 3;
        //int[][] queries = {{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};

        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(rows, cols, queries)));
    }
}