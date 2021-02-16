package org.gorany.backjoon.주유쇼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Element implements Comparable<Element>{
        int tg, oilCost, sum;
        public Element(int target, int oilCost, int sum){
            tg=target;
            this.oilCost=oilCost;
            this.sum=sum;
        }
        @Override
        public int compareTo(Element o) {
            return sum - o.sum;
        }
    }
    static class Item implements Comparable<Item>{
        int idx, oilCost;
        public Item(int idx, int oilCost){
            this.idx = idx;
            this.oilCost = oilCost;
        }

        @Override
        public int compareTo(Item o) {
            return oilCost - o.oilCost;
        }
    }
    static class Edge{
        int tg, dist;
        public Edge(int tg, int dist){
            this.tg=tg;
            this.dist=dist;
        }
    }
    static final int N = 2501, INF = Integer.MAX_VALUE;
    static int[] oil = new int[N], cost[] = new int[N][N], dist[] = new int[N][N];
    static List<Edge>[] list = new ArrayList[N];

    static void Dijkstra(int start, int n){

        Queue<Element> Q = new PriorityQueue<>();

        if(start == 1)
            cost[start][start] = 0;
        else
            cost[start][start] = cost[1][start];

        Q.add(new Element(start, 100000000, cost[start][start]));

        while(!Q.isEmpty()){
            Element element = Q.poll();

            int current = element.tg;
            int prevOilCost = element.oilCost;
            int sum = element.sum;

            if(cost[start][current] < sum) continue;

            for(Edge e : list[current]){
                int next = e.tg;
                int nextDist = e.dist;
                int nextSum = sum;
                int nextOilCost;

                if(prevOilCost * nextDist < oil[current] * nextDist) { //이전 도시의 주유소
                    nextSum += prevOilCost * nextDist;
                    nextOilCost = prevOilCost;
                }
                else { //현재 도시의 주유소
                    nextSum += oil[current] * nextDist;
                    nextOilCost = oil[current];
                }

                if(cost[start][next] > nextSum) {
                    cost[start][next] = nextSum;

                    Q.add(new Element(next, nextOilCost, nextSum));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Queue<Item> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            oil[i] = Integer.parseInt(st.nextToken());
            if(i != n)
                pq.add(new Item(i, oil[i]));
            list[i] = new ArrayList<>();
            Arrays.fill(cost[i], INF);
        }

        for(int i=1; i<=m ;i++){
            st = new StringTokenizer(br.readLine());

            int home = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list[home].add(new Edge(target, dist));
            list[target].add(new Edge(home, dist));
        }

        Dijkstra(1, n);

        int min = cost[1][n];
        while(!pq.isEmpty()){
            Item item = pq.poll();
            Dijkstra(item.idx, n);

            if(cost[item.idx][n] < min){
                min = cost[item.idx][n];
                break;
            }
        }



        for(int j=1; j<=n; j++){
            for(int i=1 ; i<=n ;i++)
                System.out.print(cost[j][i] + " ");
            System.out.println();
        }


        /*for(int j=2; j<n; j++)
            min = Math.min(min, cost[j][n]);*/
        System.out.println(min);
    }
}