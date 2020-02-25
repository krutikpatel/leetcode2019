/*
Bus Routes
DescriptionHintsSubmissionsDiscussSolution

We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.

Example:
Input: 
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation: 
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.

*/
class Solution {
    /*
    Note: each route is one bus
    
    BFS Queue of bus stops
    Record visited as USED BUS/ROUTE
    */
    public int numBusesToDestination(int[][] routes, int S, int T) {
        
        if (S==T) return 0; 
        
        //create adj list. <node,<busindexthat_can_go_there>> => all stops in same route are neighbors by 1 hop
        //so we just need to track diff routes. Each route is one bus, so we can say we map buses
        //station to busIndex/route map
        HashMap<Integer, List<Integer>> adjList = new HashMap<>();
        
        for(int i = 0; i < routes.length; i++){
            //each route[] is one bus, ith bus
            for(int j = 0; j < routes[i].length; j++){
                int currStation = routes[i][j];
                List<Integer> buses = adjList.getOrDefault(currStation, new ArrayList<>());
                
                //all buses on this route goes/represents i route
                buses.add(i);
                adjList.put(currStation, buses);                
            }       
        }
                
        //BFS - start from S. Queue of bus-stops
        Queue<Integer> q = new LinkedList<>();        
        q.offer(S);
        
        //track visited/used BUS to avoid loop (NOT bus stops)
        HashSet<Integer> visited = new HashSet<>();
        
        int ret = 0;
        while(!q.isEmpty()) {
            //go thru all nodes in q, make one hop
            int len = q.size();
                        
            for(int i=0;i<len;i++) {                
                int curr = q.poll();
                
                //check if dest
                if(T==curr)
                    return ret;                                                
                
                //add all neighbors of curr to q
                for(int bus: adjList.get(curr)) {
                    //if visited BUS, skip, we have already added all stops reachable by this bus to Q
                    if(visited.contains(bus))
                        continue;
                    
                    visited.add(bus);
                    
                    //add all stops that can be reached with this bus
                    for(int bus_stop : routes[bus]){
                        q.offer(bus_stop);
                    }
                }
                
            }
            ret++;//one hop done - DO NOT DO AT BEGINNING, THE POSITION OF RETURN DEFINES WHEN WE INCREMENT THIS
        }
        
        return -1;
    }

}
