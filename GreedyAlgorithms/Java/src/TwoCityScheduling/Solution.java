package TwoCityScheduling;
/*1029. Two City Scheduling
There are 2N people a company is planning to interview.
The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].
Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
 */

import java.util.*;

public class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int cost = 0;

        Arrays.sort(costs, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[1] - o1[0]) - (o2[1] - o2[0]);
            }
        });

        for(int i = 0; i < costs.length / 2; i++){
            cost += costs[i][1] + costs[costs.length - 1 -i][0];
        }

        return  cost;
    }
}