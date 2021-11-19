/* medium
695. Max Area of Island
DescriptionHintsSubmissionsDiscussSolution

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]

Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.

Example 2:

[[0,0,0,0,0,0,0,0]]

Given the above grid, return 0
*/
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        
        int maxArea = 0;
        if(grid == null || grid.length == 0)
            return 0;
        
        //for each point try bfs, will keep marking visited cells
        //mark visited with -1
        //keep track of max area
        //call bfs if cell == 1 
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    maxArea = Math.max(maxArea, dfs(grid,i,j));
                }
            }
        }
        
        return maxArea;
    }
    
    public int dfs(int[][] grid, int i, int j){
        //all direction checks at once in eginning
        if( i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1){
            grid[i][j] = 0;//marking visited
            return 1 + dfs(grid, i+1, j) + dfs(grid, i-1, j) + dfs(grid, i, j-1) + dfs(grid, i, j+1);
        }
        return 0;
    }
    
    private int bfs(int[][] m, int i, int j){
        int area = 1;
        
        Queue<int[]> q = new LinkedList();
        //begin from i,j
        //if neighbor == 1 add to Q otherwise skip
        //mark visited with -1
        q.add(new int[]{i,j});
        while(!q.isEmpty()){
            int[] curr = q.remove();
            //area++;
            //System.out.println(area);
            int r = curr[0];
            int c = curr[1];
            m[r][c] = -1;
            
            //up
            if(r-1 >=0 && m[r-1][c] == 1){
                q.add(new int[]{r-1,c});
                m[r-1][c] = -1;
                area+=1;
            }
            //down
            if(r+1 < m.length && m[r+1][c] == 1){
                q.add(new int[]{r+1,c});
                m[r+1][c] = -1;
                area+=1;
            }
            //left
            if(c-1 >=0 && m[r][c-1] == 1){
                q.add(new int[]{r,c-1});
                m[r][c-1] = -1;
                area+=1;
            }
            //right
            if(c+1 < m[0].length && m[r][c+1] == 1){
                q.add(new int[]{r,c+1});
                m[r][c+1] = -1;
                area+=1;
            }            
            //System.out.println("==itr end ==");
        }
        
        return area;
    }
}
