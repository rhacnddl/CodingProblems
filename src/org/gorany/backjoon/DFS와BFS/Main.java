package org.gorany.backjoon.DFS와BFS;

import java.util.*;
import java.util.stream.IntStream;

/*
4 5 1
1 2
1 3
1 4
2 4
3 4
DFS : 1 2 4 3
BFS : 1 2 3 4

6 6 1
1 2
1 3
2 3
4 6
4 5
6 5
DFS : 1 2 3 4 5 6
BFS : 1 2 3 4 5 6

5 5 3
5 4
5 2
1 2
3 4
3 1
DFS : 3 1 2 5 4
BFS : 3 1 4 2 5
 */
public class Main {

    static int[][] graph;
    static boolean[] visit;
    static Queue<Integer> Q = new LinkedList<>();

    static void BFS(int start){

        visit[start] = true;
        System.out.printf("%d ", start);
        Q.add(start);

        while(!Q.isEmpty()){
            int next = Q.poll();

            for(int a : graph[next])
                if (a != 0 && !visit[a]) {
                    visit[a] = true;
                    System.out.printf("%d ", a);
                    Q.add(a);
                }

        }
    }
    static void DFS(int start){

        visit[start] = true; //방문한 정점
        System.out.printf("%d ", start);

        for(int a : graph[start])
            if(a != 0 && !visit[a])
                DFS(a);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int target, dest; //target & desc : 서로 이어진 정점
        int n = sc.nextInt(); //정점의 개수
        int m = sc.nextInt(); //간선의 개수
        int start = sc.nextInt(); //탐색을 시작할 정점

        visit = new boolean[n+1];
        graph = new int[n+1][m+1];

        for(int i=1; i<=m; i++){
            target = sc.nextInt();
            dest = sc.nextInt();
            for(int j=1; j<=m; j++)
                if(graph[target][j] == 0){
                    graph[target][j] = dest;
                    break;
                }
            for(int j=1; j<=m; j++)
                if(graph[dest][j] == 0){
                    graph[dest][j] = target;
                    break;
                }
        }
        for(int i=1; i<=n ;i++)
            Arrays.sort(graph[i]);
/*
        //debug code
        for(int i=1; i<=n; i++){
            System.out.printf("%d : ", i);
            for(int j=1; j<=m; j++)
                System.out.printf("%d ", graph[i][j]);
            System.out.println();
        }
*/
        //DFS
        DFS(start);
        IntStream.rangeClosed(1, n).forEach(i->visit[i] = false);
        System.out.println();
        //BFS
        BFS(start);

    }
}
