/*
https://leetcode.com/problems/course-schedule-ii/description/

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]
*/
class Solution {
    /*
    <>Imp detail to remember:
      <>the "adj list is created for source courses". because given a course, we want its dependents so that we can reduce their indegree
      <>another good option might be using set instead of list, if we go other way. adj list for dep/dest courses

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
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        boolean isPossible = true;
        
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();//<>the "adj list is created for source courses". because given a course, we want its dependents so that we can reduce their indegree
        int[] indegree = new int[numCourses];// easier to use this to lookp up 0 indegree nodes, and track indegrees rather than thru adjList
        int[] topologicalOrder = new int[numCourses];

        // Create the adjacency list representation of the graph. list for nodes with outgoing edges
        for (int i = 0; i < prerequisites.length; i++) {
          int dest = prerequisites[i][0];
          int src = prerequisites[i][1];
          List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
          lst.add(dest);
          adjList.put(src, lst);

          // Record in-degree of each vertex
          indegree[dest] += 1;
        }

        // Add all vertices with 0 in-degree to the queue
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
          if (indegree[i] == 0) {
            q.add(i);
          }
        }

        int i = 0;
        // Process until the Q becomes empty
        while (!q.isEmpty()) {
          int node = q.remove();
          topologicalOrder[i++] = node;

          // Reduce the in-degree of each neighbor by 1
          if (adjList.containsKey(node)) {
            for (Integer neighbor : adjList.get(node)) {
              indegree[neighbor]--;

              // If in-degree of a neighbor becomes 0, add it to the Q
              if (indegree[neighbor] == 0) {
                q.add(neighbor);
              }
            }
          }
        }

        // Check to see if topological sort is possible or not.
        if (i == numCourses) {
          return topologicalOrder;
        }

        return new int[0];
    }
}
