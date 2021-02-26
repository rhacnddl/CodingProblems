package org.gorany.backjoon;

public class FloydWarshall {

    static final int INF = 1000000000;
    static int n = 5;

    static int[][] distance = {
            {0, 3, 8, INF, -4},
            {INF, 0, INF, 1, 7},
            {INF, 4, 0, INF, INF},
            {2, INF, -5, 0, INF},
            {INF, INF, INF, 6, 0}
    };

    static void FloydWarshall(){

        int[][] d = distance;

        for(int k=0; k<n; k++) //k : 거쳐가는 정점
            for(int i=0; i<n; i++) //i : 출발 정점
                for(int j=0; j<n; j++) //j : 도착 정점
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++)
                System.out.printf("%2d ", d[i][j]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        FloydWarshall();
    }
}
