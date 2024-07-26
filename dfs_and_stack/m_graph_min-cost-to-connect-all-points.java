/*
https://leetcode.com/problems/min-cost-to-connect-all-points/editorial/

You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

*/

class Solution {
    /**
    Prim's algo

    Think of it as modified BFS
        in BFS, instead of normal Q, use PQ so that we use optimized distance
     */
    public int minCostConnectPoints(int[][] points) {
        int N = points.length;
        Map<Integer, List<int[]>> adj = new HashMap<>();//adj with weighted edges : nbrDist, nbr
        for (int i = 0; i < N; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];

            //for each node, go over remaining nodes. because we do bidirectional initialzation, till "i" is already covered
            for (int j = i + 1; j < N; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2);

                //adj for both nodes involved
                adj.computeIfAbsent(i, k -> new ArrayList<>()).add(new int[]{dist, j});// lambda function is nicer way to do this in just 1 line
                adj.computeIfAbsent(j, k -> new ArrayList<>()).add(new int[]{dist, i});
            }
        }

        int res = 0;
        Set<Integer> visit = new HashSet<>();//keep track of visited nodes to avoid cycles

        //min heap of weighted edges, and just insert everything at each node, and poll which is min !
        PriorityQueue<int[]> minH = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        //start with 0th node itself
        minH.offer(new int[]{0, 0});

        //till all nodes visited
        //this heap will experience n^2 nodes, because we add n for n - avoid cycles though
        while (visit.size() < N) {
            int[] curr = minH.poll();//poll top, which will be min
            int cost = curr[0];
            int currNode = curr[1];

            //imp to avoid cycles here too
            if (visit.contains(currNode)) {
                continue;
            }
            
            res += cost;//build up min result
            visit.add(currNode);

            //insert currNode's adj
            for (int[] nei : adj.getOrDefault(currNode, Collections.emptyList())) {
                int neiCost = nei[0];
                int neiIndex = nei[1];

                //avoid cycles
                if (!visit.contains(neiIndex)) {
                    minH.offer(new int[]{neiCost, neiIndex});
                }
            }
        }
        return res;
    }
}
