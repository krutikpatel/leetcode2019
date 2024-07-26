/*
https://leetcode.com/problems/course-schedule/description/

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
*/
class Solution {
    /*
    Topological sort.
    -add all those nodes who does not have incoming degree/branch, to a queue. Those are like root nodes.
    
    -When we go thru queue, get adjacency list of removed node. 
        -go thru all those nodes, decrease their incoming degree by one.
        -if resuling incoming_degree == 0, add them to queue. (like one level completed)
    
    -As far as sorting goes, once we remoove node from queue, add it to sorted list. thats our topological_sorted_list.
    
    IMP-Topological sort is for Acyclic graphs
    */
    /*
    For this question:
    -each pair is node in graph.
    -pair[n][0] is course
    -pair[n][1] is incoming node. (adjacency), indegree.
    
    -We are not guaranteed that graph is acyclic.
    
    -First we need to calculate indegree for each node. This question input is like creating graph first.
    */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        
        //create/count indegree for each node
        for(int[] pair:prerequisites) {
            indegree[pair[0]]++;    //pair[0] is some course which has some prerequisits
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        //add indegree = 0, nodes to queue
        for(int i=0;i<indegree.length;i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }
        
        while(!q.isEmpty()) {
            int currCourse = q.remove();
            numCourses--; //one course completed (no prereq or done)
            
            //go over all nodes and update indegrees for courses dependent on this course.
            //Note: to make this faster, dont go over all pairs, create graph/map first
            for(int[] pair:prerequisites){
                if(pair[1]==currCourse){
                    indegree[pair[0]]--;
                    if(indegree[pair[0]]==0){
                        q.add(pair[0]);
                    }
                }
            }
        }
        
        //for cycles, it is positive. because we wont have indegree = 0 for some of them

        return numCourses == 0;
    }
}
