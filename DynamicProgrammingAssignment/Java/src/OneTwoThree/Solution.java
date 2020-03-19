package OneTwoThree;

public class Solution {
    static int oneTwoThree(int x){
        int[] ways = new int[x + 1];

        ways[0] = 1;
        ways[1] = 1;
        ways[2] = 2;

        for(int i = 3; i < x + 1; i++){
            ways[i] += ways[i - 1] + ways[i - 2] + ways[i - 3];
        }

        for(int n: ways){
            System.out.print(n + " ");
        }
        System.out.println();

        return ways[x];
    }

    public static void main(String[] args) {
        System.out.println(oneTwoThree(4));
        System.out.println(oneTwoThree(7));
        System.out.println(oneTwoThree(10));
    }
}
