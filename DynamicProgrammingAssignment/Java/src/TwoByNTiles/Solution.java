package TwoByNTiles;

public class Solution {
    static int twoByNtiles(int x){
        if(x == 1) return 1;
        if(x == 2) return 2;
        return twoByNtiles(x - 1) + twoByNtiles(x - 2);
    }

    static int twoByNTilesDP(int x){
        int[] ways = new int[x + 1];
        ways[1] = 1;
        ways[2] = 2;

        for(int i = 3; i < x + 1; i++){
            ways[i] = ways[i - 1] + ways[i - 2];
        }

        return ways[x];
    }

    public static void main(String[] args) {
        System.out.println(twoByNtiles(9));
        System.out.println(twoByNTilesDP(9));
    }
}
