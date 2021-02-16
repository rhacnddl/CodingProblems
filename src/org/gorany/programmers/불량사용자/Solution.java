package org.gorany.programmers.불량사용자;

import java.util.*;

public class Solution {

    static boolean[] u_visit, b_visit;
    static int answer = 0;


    static boolean allTrue(boolean[] visit){
        boolean res = true;

        for(int i=0; i< visit.length; i++)
            if(!visit[i]) res = false;

        return res;
    }
    /*
    static void DFS(String[] users, String[] banned, int current, int current_){
        if(allTrue(b_visit)){
            answer++;
            return;
        }

        for(int i=0; i<u_visit.length; i++){
            if(!u_visit[i]){
                u_visit[i] = true;
                String userID = users[i];

                for(int j=current_; j<b_visit.length; j++){
                    if(!b_visit[j]){
                        String banID = banned[j];

                        //글자 수가 다르면 skip
                        if(banID.length() != userID.length()) continue;

                        boolean flag = true;
                        char[] user = userID.toCharArray();
                        char[] ban = banID.toCharArray();

                        //userID와 banID 비교
                        for(int k=0; k<ban.length; k++){
                            if(ban[k] == '*') continue;

                            if(user[k] != ban[k]) flag = false;
                        }

                        //비교 결과 유사하다면
                        if(flag){
                            b_visit[j] = true;

                            *//*System.out.println("# "+userID +" 와 "+banID +"는 유사함");
                            for(int a=0; a< b_visit.length; a++)System.out.print(b_visit[a] + " ");
                            System.out.println("\n---------------------------------------------");*//*

                            DFS(users, banned, i, j);

                            b_visit[j] = false;
                        }
                    }

                }
                u_visit[i] = false;
            }
        }
    }*/
    static void DFS(String[] users, String[] bans, int current) {

        if(allTrue(b_visit)){
            answer++;
            return;
        }

        String userID = users[current];
        for(int i=0; i<bans.length; i++){
            if(!b_visit[i]){

                String banID = bans[i];

                //글자 수가 다르면 skip
                if(banID.length() != userID.length()) continue;

                boolean flag = true;
                char[] user = userID.toCharArray();
                char[] ban = banID.toCharArray();

                //userID와 banID 비교
                for(int k=0; k<ban.length; k++){
                    if(ban[k] == '*') continue;

                    if(user[k] != ban[k]) flag = false;
                }

                //비교 결과 유사하다면
                if(flag){
                    b_visit[i] = true;
                    u_visit[current] = true;

                    System.out.println("# "+userID +" 와 "+banID +"는 유사함");
                    for(int a=0; a< b_visit.length; a++)System.out.print(b_visit[a] + " ");
                    System.out.println("\n---------------------------------------------");

                    int a = current;

                    for(int z=0; z<users.length; z++){
                        if(a == users.length - 1)
                            a = 0;

                        a += 1;
                        if(!u_visit[a])
                            DFS(users, bans, a);

                    }
                }

            }
        }
    }
    static int solution(String[] user_id, String[] banned_id) {

        /*for(int i=0; i< user_id.length; i++) {
            u_visit = new boolean[user_id.length];
            b_visit = new boolean[banned_id.length];
            DFS(user_id, banned_id, i);
        }*/
        u_visit = new boolean[user_id.length];
        b_visit = new boolean[banned_id.length];
        DFS(user_id, banned_id, 0);

        return answer;
    }

    public static void main(String[] args) {
        //String[] userid = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        //String[] banned = {"fr*d*", "*rodo", "******", "******"};
        String[] userid = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned = {"*rodo", "*rodo", "******"};

        System.out.println(solution(userid, banned));
    }
}
