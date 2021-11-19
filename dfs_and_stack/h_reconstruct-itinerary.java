/* hard
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
    
    -Imp - we cant blindly proceed to next node without having actual route. it may happen in last method
    
    -----------------------------------
    Soultion is Hierholzer's Algorithm
    its not normal DFS
    -----------------------------------
    Algo notes:
    The point that we got stuck would be the last airport that we visit. And then we follow the visited vertex (airport) backwards, we would obtain the final itinerary.

=>Actually, we could consider the algorithm as the postorder DFS (Depth-First Search) in a directed graph, from a fixed starting point.

=>As we know that, each input is guaranteed to have a solution. Therefore, the task of the problem can be interpreted as that given a list of flights (i.e. edges in graph), we should find an order to use each flight once and only once.

=>In the resulted path, before we visit the last airport (denoted as V), we can say that we have already used all the rest flights, i.e. if there is any flight starting from V, then we must have already taken that before.

=>Or to put it another way, before adding the last airport (vertex) in the final path, we have visited all its outgoing vertex.
=>Before adding an airport into the final itinerary, we must first visit all its outgoing neighbor vertex.


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
        //algorithm gives path in reve
        path.addFirst(departure);//imp - because its DFS, first one will get added last(recursive calls)
    }
    
    public void dfs2(String departure, List<String> sofar) {
        PriorityQueue<String> dests = flights.get(departure);
        while (dests != null && !dests.isEmpty()) {
            dfs(dests.poll());
        }            
        path.addFirst(departure);//imp - because its DFS, first one will get added last(recursive calls)
    }

    /*
    normal DFS - leads to routes that do not exist, because in loop, we go to all neighbors regardless of path existence
    
    HashMap<String, PriorityQueue<String>> graph;
    public List<String> findItinerary(List<List<String>> tickets) {
        //create adj list 
        graph = new HashMap<>();
        for(List<String> route : tickets){
            PriorityQueue<String> curr = graph.getOrDefault(route.get(0),new PriorityQueue<>((a,b) -> a.compareTo(b)) );
            curr.offer(route.get(1));
            graph.put(route.get(0), curr);
        }

        List<String> ret = new ArrayList<>();
        ret.add("JFK");
        dfs("JFK",graph, ret);
        return ret;
    }

    private void dfs(String from, HashMap<String, PriorityQueue<String>> graph, List<String> sofar){
        String curr = from;//sofar.get(sofar.size()-1);
        PriorityQueue<String> pq = graph.get(curr);
        
        while(pq!= null && !pq.isEmpty()){
            String next = pq.poll();
            //if(graph.get(next) != null){
                sofar.add(next);
                dfs(next,graph, sofar);
            //}
        }
    }
*/
}
