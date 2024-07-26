/*
You are given a network of n directed nodes, labeled from 1 to n. You are also given times, a list of directed edges where times[i] = (ui, vi, ti).

ui is the source node (an integer from 1 to n)
vi is the target node (an integer from 1 to n)
ti is the time it takes for a signal to travel from the source to the target node (an integer greater than or equal to 0).
You are also given an integer k, representing the node that we will send a signal from.

Return the minimum time it takes for all of the n nodes to receive the signal. If it is impossible for all the nodes to receive the signal, return -1 instead
*/
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> edges = new HashMap<>();//edge = nbr with weight
        for(int[] time : times) {
            //time[1] = dest, time[2] = dist
            edges.computeIfAbsent(time[0], key -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);// dist,node
        pq.offer(new int[]{0,k});//0 time to reach itself

        Set<Integer> visited = new HashSet<>();
        int ret = 0;
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int currWt = curr[0];
            int currNode = curr[1];

            if(visited.contains(currNode)){
                continue;
            }
            visited.add(currNode);
            ret = currWt;//this is weight so far. and at end, this is our ans

            if(edges.containsKey(currNode)){
                for(int[] edge : edges.get(currNode)){
                    int nbr = edge[0];
                    int nbrWt = edge[1];

                    //if(!visited.contains(nbr)){
                        pq.offer(new int[]{nbrWt + currWt, nbr});
                    //}
                }
            }
        }

        return visited.size() == n ?ret:-1;
    }
}
