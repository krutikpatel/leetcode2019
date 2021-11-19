/* medium
934. Shortest Bridge
DescriptionHintsSubmissionsDiscussSolution

In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)

Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.

Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)

 

Example 1:

Input: [[0,1],[1,0]]
Output: 1

Example 2:

Input: [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
*/

class Solution {
    /*
    There are 2 islands of 1's AND we need to connect them
    because its BFS to optimize soln, start from target (1 nodes). so make queue of 1's
    
    DFS one "1" island.
    Then BFS to expand it.
    if we reach some another 1, we are done.
    
    
    Use DFS + BFS to solve this WONDERFUL problem! 
     * Step 1: use DFS to mark the first island to another number (also, add these 1's to Q, for BFS in 2nd step)
     * Step 2: start from the first island, doing BFS level order traversal to find number of bridges (levels)
     * until reach the second island
    
    */
    public int shortestBridge(int[][] A) {
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[A.length][A[0].length];
        
        boolean found = false;
        //build Q, for just one "1" island
        for(int i=0;i<A.length;i++) {
            if(found)
                break;
            for(int j = 0;j<A[0].length;j++) {
                if(A[i][j] == 1) {
                    dfs(A,i,j,visited, q);
                    found = true;
                    break;
                }
                
            }
        }        
        
        int levels = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        
        while(!q.isEmpty()) {                    
            
            int len = q.size();
            //key check all nodes in Q at once, to get correct level
            for(int i=0;i<len;i++) {
                int[] curr = q.poll();
                
                //check each side of curr and expand
                for(int[] dir : dirs) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];

                    if(x<0 || x>= A.length || y<0 || y>= A[0].length || visited[x][y] == true)
                        continue;

                    if(A[x][y] == 1)
                        return levels;
                    else
                        q.offer(new int[]{x,y});
                    
                    //don not re-use cell
                    visited[x][y] = true;
                }    
            }
            levels++;
        }
        
        return -1;
    }
    
    private void dfs(int[][] A, int i, int j, boolean[][] visited, Queue<int[]> q) {
        if(i<0 || i>= A.length || j<0 || j>= A[0].length || A[i][j] == 0 || visited[i][j] == true)
            return;
        q.add(new int[]{i,j});
        visited[i][j] = true;
        dfs(A,i+1,j,visited,q);
        dfs(A,i-1,j,visited,q);
        dfs(A,i,j+1,visited,q);
        dfs(A,i,j-1,visited,q);
    }
}
