package temp;

/*

Given a list of [origin, destination] pairs (you could think of them as plane tickets), find the longest continuous route. 

[
    ["SFO", "EWR"],
    ["SJC", "LAX"],
    ["DFW", "SJC"],
    ["EWR", "OAK"],
    ["LAX", "SFO”]
]  
Desired output
["DFW", "SJC", "LAX", "SFO", "EWR", "OAK"]

{
    {"SFO", "EWR"},
    {"SJC", "LAX"},
    {"DFW", "SJC"},
    {"EWR", "OAK"},
    {"LAX", "SFO"}
}

*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
 * https://www.techiedelight.com/find-cost-longest-path-dag/
 * print longest path in graph
 * 
 * BFS may be ONLY good to find max/min length
 * DFS can be good to print each path
 * 
 * Corners:
 * multiple routes, starting from same station (eg SFO).
 * Cycle forming, SFO-EWR and EWR,SFO
 */
public class try2 {
    public static void main(String args[] ) throws Exception {
        System.out.println("Hello World");
        String[][] routes = {
            {"SFO", "EWR"},
            {"SFO", "JFK"},            
            {"SJC", "LAX"},
            {"DFW", "SJC"},
            {"EWR", "OAK"},
            {"LAX", "SFO"},
            {"SFO", "A"},            
            {"JFK","SFO"}
        };
    
        int res = getLongestContiguousRoute(routes);
        System.out.println(res);
    }    

    public static List<String> maxPath = new ArrayList<>();

    /////////////////////////////////////////////////////////////////////////
    public static int getLongestContiguousRoute(String[][] routes) {
        HashMap<String, List<String>> adj = new HashMap<>();
        
        //create adjacency list
        for(String[] route : routes) {
            
            adj.putIfAbsent(route[0], new ArrayList<String>());
            adj.putIfAbsent(route[1], new ArrayList<String>());
            
            String dest = route[1];
            adj.get(route[0]).add(dest);
        }      

        //for each src, dfs and find contiguous len
        for(String[] route : routes) {
            String src = route[0];
            Set<String> visited = new HashSet<>();

            List<String> path = new ArrayList<>();
            //path.add(src);
            List<String> localPath = dfs(src, adj, visited,path);
            
        }
/*      
        //
        Set<String> visited = new HashSet<>();
        len = 0;
        List<String> path = new ArrayList<>();
        String src = "SFO";
        path.add(src);
        List<String> localPath = dfs(src, adj, visited,path);
        //
*/        
        System.out.println(maxPath);
        
        return maxPath.size();
    }
    
    /*
    -src
        -go thru all neightbors
        -visited
        -incr len
    return if visited    
    
    NOTE: easier to track path in String compared to list.. because of references etc
    
    Problem:
	    beware, path is beign accumulated even after switching ton new neighbor. 
	    new neighbor shud just start from sfo again, rahter than carrying prev path
	    
	How would i track path as String instead of List ?    
    */
    public static List<String> dfs(String src, HashMap<String, List<String>> adj, Set<String> visited, List<String> path) {
        //Lesson : imp ret condition for DFS, if end of tree or visited
    	if(visited.contains(src) ||  adj.get(src).size()==0){
    		//System.out.println(path + "______");
    		return path;
        }
    	
        visited.add(src);
        System.out.println(visited + "___visited___");
        path.add(src);
        
        List<String> neighbors = adj.get(src);               
        for(String n: neighbors) {
        	//path.add(n); IF U ADD HERE, U WILL ADD VISITED NODE ONCE, BECAUSE ITS ONLY CHECKED LATER
            List<String> currPath = dfs(n,adj,visited, path);
            //System.out.println(src+"_"+n);
            
            System.out.println(currPath);
            System.out.println("---");
            
            if(currPath.size() >= maxPath.size()) {
            	//maxPath = currPath;//Lesson : MAKE COPY, DONT JUST ASSIGN NEW REFERENCE, THIS KEEPS CHANGING, so final result is messed up !
            	maxPath = new ArrayList<>(currPath);
            }
            path.remove(n);//Lesson : remove this neighbor and try next neighbor
        }
        
        return maxPath;
    }
    
    /*
     * BFS ? -start with src in q. go level by level. until q is empty ?
     */
    public static void bfs(String src, HashMap<String, List<String>> adj) {
    	//next level, cant everything to same list.
    }
}
