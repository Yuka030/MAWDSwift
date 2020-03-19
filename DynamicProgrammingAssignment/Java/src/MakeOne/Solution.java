package MakeOne;

public class Solution {
    static int makeOne(int n){
        if(n == 1) return 0;
        if(n % 3 == 0) return 1 + makeOne( n / 3);
        return Math.min(1 + makeOne(n / 2), 1 + makeOne( n / 2));
    }

    static int makeOneDP(int n){ //9
        int[] d = new int[n + 1]; //d[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

        for(int i = 2; i < n + 1; i++){
            d[i] = d[i - 1] + 1;
            if(i % 2 == 0 && d[i] > d[i / 2] + 1){
                System.out.println("dividable by 2 & i is " + i);
                d[i] = d[i / 2] + 1;
            }

            if(i % 3 == 0 && d[i] > d[i / 3] + 1){
                System.out.println("dividable by 3 & i is " + i);
                d[i] = d[i / 3] + 1;
            }
        }

        System.out.println(d[n]);
        for(int num: d){
            System.out.print(num + " ");
        }
        return d[n];
    }
    public static void main(String[] args) {
        makeOneDP(10);
    }
}
