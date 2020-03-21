package Epidemiology;

public class Solution {
    static int countDays(int p, int n, int r){
        int canInfectOthers = n;
        int todayInfected;
        int totalInfected = n;
        int days = 0;

        while(totalInfected <= p){
            todayInfected = canInfectOthers * r;
            totalInfected += todayInfected;
            canInfectOthers = todayInfected;
            days++;
        }
        return days;
    }

    public static void main(String[] args) {
        System.out.println(countDays(750, 1, 5));
        System.out.println(countDays(10, 2, 1));
    }
}
