package TaskScheduler;
/*
* 621. Task Scheduler
* Input: tasks = ["A","A","A","B","B","B"], n = 2
  Output: 8
  Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
*/

import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        Arrays.sort(tasks);
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < tasks.length; i++){
            map.put(tasks[i], 0);
        }
        for(int i = 0; i < tasks.length; i++){
            map.put(tasks[i], map.get(tasks[i]) + 1);
        }

        int maxCount = map.get(tasks[0]);
        int numMostFrequent = 0;

        for(char key: map.keySet()){
            if(maxCount == map.get(key)){
                numMostFrequent++;
            }
            if(map.get(key) > maxCount){
                maxCount = map.get(key);
                numMostFrequent = 1;
            }
        }

        int partCount = maxCount - 1;
        int partLength = n - (numMostFrequent - 1);
        int slots = partCount * partLength;
        int availableTasks = tasks.length - maxCount * numMostFrequent;
        int idles = Math.max(0, slots - availableTasks);
        int total = tasks.length + idles;

        return total;
    }
}