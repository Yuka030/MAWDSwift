package TwoByNTiles02;

public class Solution {
    static int twoByNTiles02(int x){
        if(x == 1) return 1;
        if(x == 2) return 3;

        return twoByNTiles02(x - 1) + 2 * (twoByNTiles02( x - 2));
    }

    static int twoByTiles02DP(int x){
        int[] ways = new int[x + 1];
        ways[1] = 1;
        ways[2] = 3;

        for(int i = 3; i < x + 1; i++){
            ways[i] = ways[i - 1] + 2 * (ways[i - 2]);
        }

        return ways[x];
    }

    public static void main(String[] args) {
        System.out.println(twoByNTiles02(8));
        System.out.println(twoByTiles02DP(8));
        System.out.println(twoByNTiles02(12));
        System.out.println(twoByTiles02DP(12));
    }
}
