package PalindromeLength;

public class Solution {

    public static int findLP(String str){
        return calc(str, 0, str.length() - 1);
    }

    public static int calc(String str, int start, int end){
        if(start > end) return 0;
        if(start == end) return 1;

        if(str.charAt(start) == str.charAt(end)){
            return 2 + calc(str, start + 1, end - 1);
        }

        int c1 = calc(str, start, end - 1);
        int c2 = calc(str, start + 1, end);

        return Math.max(c1, c2);
    }

    public static void main(String[] args) {
        System.out.println(findLP("banana"));
    }
}
