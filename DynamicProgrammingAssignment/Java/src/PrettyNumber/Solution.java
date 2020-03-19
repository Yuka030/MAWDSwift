package PrettyNumber;

import java.util.Scanner;

public class Solution {
    static int prettyNumber(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] ways = new int[n + 1][10];

        for(int i = 1; i <= 9; i++){
            ways[1][i] = 1;
        }

        for(int i = 2; i <= n; i++){
            for(int j = 0; j <= 9; j++){
                if((j - 1) >= 0){
                    ways[i][j] += ways[i - 1][j - 1];
                }
                if((j + 1) <= 9){
                    ways[i][j] += ways[i - 1][j + 1];
                }
            }
        }

        int answer = 0;
        for(int i = 0; i <= 9; i++){
            answer += ways[n][i];
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(prettyNumber());
    }
}
