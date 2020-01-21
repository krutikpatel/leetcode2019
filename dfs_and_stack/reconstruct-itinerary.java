/*
Reconstruct Itinerary
DescriptionHintsSubmissionsDiscussSolution

Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

    If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
    All airports are represented by three capital letters (IATA code).
    You may assume all tickets form at least one valid itinerary.

Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.

*/
class Solution {
    /*
    -Map<String,List<String>>
    -Put everything in map - creating adj list    
        Sorting list : 
    
    -DFS from jfk - recursion
    -is backtracking involved?
    
    -adding to result in correct order, since we are doing DFS recursion
    */  
    
    Map<String, PriorityQueue<String>> flights;//if there are 2 dest from same src, arrange them alphabetically
    LinkedList<String> path;

    public List<String> findItinerary(List<List<String>> tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();
        
        for (List<String> ticket : tickets) {
            flights.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            flights.get(ticket.get(0)).add(ticket.get(1));//put in PQ
        }
        
        dfs("JFK");//just one DFS from src = JFK
        return path;
    }

    public void dfs(String departure) {
        PriorityQueue<String> dests = flights.get(departure);
        while (dests != null && !dests.isEmpty()) {
            dfs(dests.poll());
        }            
        path.addFirst(departure);//imp - because its DFS, first one will get added last(recursive calls)
    }
}
