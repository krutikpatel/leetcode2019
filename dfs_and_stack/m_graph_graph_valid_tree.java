/*
You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.

ie, you can reach all nodes from any node
*/
class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) //this condition checks if graph has cycles or not. Valid tree will have n-1 edges
            return false;//imp 

        Map<Integer, List<Integer>> adj = new HashMap<>();
        for(int i=0;i<edges.length;i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            List<Integer> adjA = adj.getOrDefault(a, new ArrayList<>());
            List<Integer> adjB = adj.getOrDefault(b, new ArrayList<>());
            adjA.add(b);
            adj.put(a,adjA);

            adjB.add(a);
            adj.put(b,adjB);
        }

        //dfs from 0
        boolean[] visited = new boolean[n];//since nodes are 0 to n-1, we can also use Set<Integer>
        dfs(0, adj, visited);
        for(boolean b: visited){
            if(b==false)
                return false;
        }
        return true;
    }

    private void dfs(int i, Map<Integer, List<Integer>> adj, boolean[] visited) {
        if(visited[i])
            return;
        visited[i] = true;
        for(int nbr : adj.getOrDefault(i, new ArrayList<>())){
            dfs(nbr, adj, visited);
        }
    }
}
