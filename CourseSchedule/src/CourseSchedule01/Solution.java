package CourseSchedule01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 207. Course Schedule
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * */

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] list = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];

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
            }
        }

        //BFS
        while(!q.isEmpty()){
            int c = (int) q.poll();
            for(int i = 0; i < list[c].size(); i++){
                int j = (int) list[c].get(i);
                indegree[j] -= 1;
                if(indegree[j] == 0){
                    q.add(j);
                }
            }
        }

        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] != 0) return false;
        }

        return true;
    }
}