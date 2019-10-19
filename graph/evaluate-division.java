/*
Evaluate Division
DescriptionHintsSubmissionsDiscussSolution

Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
*/
class Solution {
    /*
    Image a/b = k as a link between node a and b, the weight from a to b is k, the reverse link is 1/k. Query is to find a path between two nodes.
    
    -for each node, we need neighbors and weight of that edge. Best way to do that is Map<neightboer,weigth> for each node
    */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();//char as node, and value: map of edge wigthts, from that char
        
        //create graph and weigths for eadges
        for(int i=0; i<equations.size();i++) {
            List<String> pair = equations.get(i);
            
            //create nodes for both strings/chars in pair
            map.putIfAbsent(pair.get(0), new HashMap<String, Double>());
            map.putIfAbsent(pair.get(1), new HashMap<String, Double>());
            
            //set weights for both directions of this edge
            map.get(pair.get(0)).put(pair.get(1), values[i]);
            map.get(pair.get(1)).put(pair.get(0), 1/values[i]);
        }
        
        double[] ret = new double[queries.size()];
        for(int i=0; i<queries.size();i++) {
            List<String> query = queries.get(i);
            
            Set<String> visited = new HashSet<>();
            //DFS
            ret[i] = dfs(query.get(0), query.get(1), map,visited, 1);
        }
        
        return ret;
    }
    
    /*
    Think a->a
    nonexistingnode to -> existing
    existing node to  -> nonexistingnode
    */    
    private double dfs(String from, String to, Map<String, Map<String, Double>> map, Set<String> visited, double sofar) {
        //if map does not have either from or to, there cannot be route. Also check for loop
        if(!map.containsKey(from) || !map.containsKey(to) || visited.contains(from) /*loop*/)
            return -1;
        
        if (from.equals(to)) 
            return sofar;
        
        visited.add(from);
                
        //for adj of from, dfs
        Map<String, Double> neighbors = map.get(from);
        for(String neighbor : neighbors.keySet()) {
            double res = dfs(neighbor, to, map, visited, sofar*neighbors.get(neighbor));
            if(res != -1)
                return res;//we found one
            //else keep looking
        }
        
        //did not find anything
        return -1;
    }
}
