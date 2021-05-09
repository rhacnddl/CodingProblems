package org.gorany.backjoon.아기돼지와늑대;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point{
        int y, x, dir;
        public Point(int yy, int xx, int d){
            y=yy;
            x=xx;
            dir=d;
        }
    }

    static int n, m;
    static int[] Y = {-1, 1, 0, 0}, X = {0, 0, -1, 1};
    static char[][] map = new char[101][101];
    static boolean[][][] visit = new boolean[101][101][4];

    /* dir: 미끄러지는 방향(상,하,좌,우) */
    static Point sliding(int y, int x, int dir){

        while(true){
            y += Y[dir];
            x += X[dir];

            visit[y][x][dir] = true;
            /* 산을 마주하면 한칸 뒤를 반환 */
            if(map[y][x] == '#')
                return new Point(y - Y[dir], x - X[dir], dir);

            /* 초원을 마주하면 그대로 반환 */
            if(map[y][x] == '.')
                return new Point(y, x, dir);
        }
    }
    static boolean isVisit(int y, int x){
        for(int i=0; i<4; i++)
            if(visit[y][x][i])
                return false;

        return true;
    }

    static void BFS(List<Point> list){
        Queue<Point> Q = new LinkedList<>();

        list.stream().forEach(p -> {
            visit[p.y][p.x][0] = true;
            Q.add(new Point(p.y, p.x, 0));
        });

        while(!Q.isEmpty()){
            Point p = Q.poll();
            int y = p.y;
            int x = p.x;

            for(int a=0; a<4; a++){
                int ny = y+Y[a];
                int nx = x+X[a];

                if(ny < 1 || nx < 1 || ny > n || nx > m || visit[ny][nx][a] || map[ny][nx] == '#') continue;

                visit[ny][nx][a] = true;
                
                /* 다음 정점이 빙판('+') */
                if(map[ny][nx] == '+'){
                    /* 빙판을 미끄러져 도착하는 곳의 좌표 반환 */
                    Point nextP = sliding(ny, nx, a);
                    Q.add(new Point(nextP.y, nextP.x, a));
                }
                /* 다음 정점이 초원('.') */
                else
                    Q.add(new Point(ny, nx, a));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        List<Point> list = new ArrayList<>();

        for(int i=1; i<=n; i++){
            char[] str = br.readLine().toCharArray();
            for(int j=1; j<=m; j++){

                map[i][j] = str[j-1];

                if(map[i][j] == 'W')
                    list.add(new Point(i, j, 0));
            }
        }

        BFS(list);

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if(map[i][j] == '.'){
                    /* 안전한 곳(늑대가 방문하지 않은 곳) */
                    if(isVisit(i, j))
                        sb.append('P');
                    /* 늑대가 방문한 곳 */
                    else
                        sb.append('.');
                }
                /* 나머지 */
                else
                    sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}