package org.gorany.backjoon.영어읽기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] arr = new int[10000][52];

    static int[] decomposition(String word) {
        int[] result = new int[52];

        for (int i = 1; i < word.length() - 1; i++) {
            int alphabet = word.charAt(i) - 'a';
            if (alphabet < 0)
                alphabet += 58;

            result[alphabet]++;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] dic = new String[n];
        for (int i = 0; i < n; i++) {
            dic[i] = br.readLine();
            arr[i] = decomposition(dic[i]);
        }

        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {

            String[] word = br.readLine().split(" ");

            int result = 1;
            for (int z = 0; z < word.length; z++) {
                int[] consist = decomposition(word[z]);
                int cnt = 0;
            /*for(int j=0; j<52; j++)
                System.out.print(consist[j] + " ");
            System.out.println();*/

                for (int j = 0; j < n; j++) {
                    boolean correct = true;

                    //두 단어 모두 길이가 2이상이고 첫 글자와 마지막 글자가 동일하지 않다면 skip
                    if (dic[j].length() > 1 && word[z].length() > 1) {
                        if (dic[j].charAt(0) != word[z].charAt(0) || dic[j].charAt(dic[j].length() - 1) != word[z].charAt(word[z].length() - 1))
                            continue;
                    }
                    //두 단어 모두 길이가 1이며 두 단어가 동일하지 않다면 skip
                    else if (dic[j].length() == 1 && word[z].length() == 1) {
                        if (!dic[j].equals(word[z])) continue;
                    }
                    //두 단어 중 하나만 길이가 1일 때는 skip
                    else if (dic[j].length() == 1 || word[z].length() == 1)
                        continue;

                    for (int k = 0; k < 52; k++)
                        if (consist[k] != arr[j][k]) {
                            correct = false;
                            break;
                        }

                    if (correct) cnt++;
                }
                result *= cnt;
            }
            sb.append(result).append('\n');
        }
        System.out.print(sb);

/*        for(int j=0; j<n; j++) {
            for (int i = 0; i < 52; i++)
                System.out.print(arr[j][i] + " ");
            System.out.println();
        }*/
    }
}