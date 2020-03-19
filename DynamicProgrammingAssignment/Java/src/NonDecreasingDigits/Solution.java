package NonDecreasingDigits;

import java.util.Scanner;

public class Solution {
    static int nonDecreasingDigits(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if(n == 1) return 10;
        int result = 0;
        for(int i = 0; i < 10; i++){
            result += (10 - i) * (Math.pow((i + 1), (n - 2)));
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(nonDecreasingDigits());
    }
}