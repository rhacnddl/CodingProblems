package org.gorany.backjoon.다리만들기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int og, tg, w;
        public Edge(int o, int t, int wt){
            og = o;
            tg = t;
            w = wt;
        }
        @Override
        public int compareTo(Edge o) {
            return w - o.w;
        }
    }
    static class Point{
        int y, x;
        public Point(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
    static int m, n;
    static int map[][];
    static int[] parent;
    static Queue<Point> Q = new LinkedList<>();
    static List<Edge> list = new ArrayList<>();
    static int[] Y = {-1, 1, 0, 0};
    static int[] X = {0, 0, -1, 1};
    static int cnt = 1;

    static void BFS_Labeling(){

        for(int i=1; i<=n; i++)
            for(int j=1; j<=m; j++){

                if(map[i][j] == 1){
                    cnt++;
                    map[i][j] = cnt;
                    Q.add(new Point(i, j));
                }

                while(!Q.isEmpty()){
                    Point p = Q.poll();
                    int y = p.y;
                    int x = p.x;

                    for(int a=0; a<4; a++){
                        int ny = y+Y[a];
                        int nx = x+X[a];

                        if((ny > 0 && ny <= n) && (nx > 0 && nx <= m) && map[ny][nx] == 1){
                            map[ny][nx] = cnt;
                            Q.add(new Point(ny, nx));
                        }
                    }
                }
            }
        Q.clear();
    }

    //섬에서 다른 섬으로 다리를 놓을 수 있는 경우 list에 추가
    static void BFS(){
        for(int i=1; i<=n; i++) {
            for (int j = 1; j <= m; j++) {

                if(map[i][j] != 0)
                    Q.add(new Point(i, j));

                while(!Q.isEmpty()){
                    Point p = Q.poll();
                    int y = p.y;
                    int x = p.x;

                    for(int a=0; a<4; a++){
                        int ny = y+Y[a];
                        int nx = x+X[a];

                        if((ny > 0 && ny <= n) && (nx > 0 && nx <= m) && map[ny][nx] == 0){
                            int weight = 1;

                            while((ny+Y[a] > 0 && ny+Y[a] <= n) && (nx+X[a] > 0 && nx+X[a] <= m)){

                                ny += Y[a];
                                nx += X[a];
                                if(map[ny][nx] != 0) {
                                    if(weight > 1) {
                                        //System.out.println((map[y][x]) + "에서 " + (map[ny][nx]) + "로 다리 길이는 " + weight + " // y:" + y + " x:" + x + " ny:" + ny + " nx:" + nx);
                                        list.add(new Edge(map[y][x] - 1, map[ny][nx] - 1, weight));
                                    }
                                    break;
                                }
                                weight++;
                            }
                        }
                    }
                }
            }
        }
    }
    /* Union-Find */
    static int getParent(int x){
        if(parent[x] == x) return x;
        return getParent(parent[x]);
    }
    static void unionParent(int a, int b){
        a = getParent(a);
        b = getParent(b);

        if(a > b) parent[a] = b;
        else parent[b] = a;
    }
    static boolean findParent(int a, int b){
        a = getParent(a);
        b = getParent(b);
        return a == b?true:false;
    }
    /* Union-Find */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        BFS_Labeling();

        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }

        BFS();

        parent = new int[cnt];
        for(int i=1; i<cnt; i++)
            parent[i] = i;

        Collections.sort(list);

        int sum = 0;
        for(int i=0; i<list.size(); i++){
            Edge edge = list.get(i);

            if(!findParent(edge.og, edge.tg)){
                sum += edge.w;
                unionParent(edge.og, edge.tg);
            }
        }
        boolean flag = true;
        for(int i=1; i<parent.length-1; i++)
            if(!findParent(parent[i], parent[i+1]))
                flag = false;

        /*Arrays.stream(parent).skip(1).forEach(i->System.out.print(i + " "));
        System.out.println();*/

        if(flag)
            System.out.println(sum);
        else
            System.out.println(-1);

    }
}
