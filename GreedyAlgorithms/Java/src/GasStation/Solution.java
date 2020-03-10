/* 134. Gas Station
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to
 * its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction,
 * otherwise return -1.
 *
 * Input:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * Output: 3
 * */

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] gasArray = new int[gas.length * 2];
        int[] costArray = new int[cost.length * 2];

        for(int i = 0; i < gasArray.length; i++){
            if(i >= gas.length){
                gasArray[i] = gas[i - gas.length];
            }else {
                gasArray[i] = gas[i];
            }

        }

        for(int i = 0; i < costArray.length; i++){
            if(i >= cost.length){
                costArray[i] = cost[i - cost.length];
            }else {
                costArray[i] = cost[i];
            }
        }

        for(int i = 0; i < gasArray.length; i++){
            int currentGas;
            int prevGas = 0;
            if(i > gas.length) return - 1;
            for(int j = i; j < gas.length + i; j++){
                currentGas = gasArray[j];
                if(currentGas + prevGas >= costArray[j]){
                    prevGas = currentGas + prevGas - costArray[j];
                    if(j - i == gas.length - 1) return i;
                }else{
                    break;
                }
            }
        }
        return -1;
    }
}