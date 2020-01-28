/*
Lesson problem

797. All Paths From Source to Target
DescriptionHintsSubmissionsDiscussSolution

Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

*/
/*
Recognize that graph[i] is i's neighbors
*/
class Solution {
    /*
    create adjacency list for each node
    DFS from 0
    backtrack
    */
    HashMap<Integer,List<Integer>> adjList;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        adjList = new HashMap<>();
        for(int i=0;i<graph.length;i++) {
            List<Integer> adj = adjList.getOrDefault(i,new ArrayList<>());
            for(int j=0;j<graph[i].length;j++) {                
                adj.add(graph[i][j]);                
            }
            adjList.put(i,adj);
            //System.out.println("adj"+i +" = " + adj);
        }
        
        List<List<Integer>> ret = new ArrayList<>();
        
        List<Integer> sofar = new ArrayList<>();
        sofar.add(0);
        dfs(0,graph.length-1,sofar, ret);
        return ret;
    }
    
    private void dfs(int from, int to, List<Integer> soFar, List<List<Integer>> ret) {
        if(from == to) {
            ret.add(new ArrayList<>(soFar));
            //System.out.println("added"+soFar.size());
            return;
        }
        
        List<Integer> adj = adjList.get(from);
        for(int i:adj) {
            soFar.add(i);
            dfs(i,to,soFar,ret);
            soFar.remove(soFar.size()-1);
        }
    }
}
