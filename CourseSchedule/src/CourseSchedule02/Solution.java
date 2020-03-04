package CourseSchedule02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
* 210. Course Schedule II
* */

public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList[] list = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        int[] order = new int[numCourses];
        int index = 0;

        for(int i = 0; i < numCourses; i++){
            list[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < prerequisites.length; i++){
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            list[b].add(a);
            indegree[a] += 1;
        }

        Queue q = new LinkedList<Integer>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                q.add(i);
                order[index++] = i;
            }
        }

        //BFS
        while(!q.isEmpty()){
            int c = (int) q.poll();
            for(int i = 0; i < list[c].size(); i++){
                int j = (int) list[c].get(i);
                indegree[j] -= 1;
                if(indegree[j] == 0){
                    order[index++] = j;
                    q.add(j);
                }
            }
        }

        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] != 0) return new int[0];
        }

        return order;
    }
}
