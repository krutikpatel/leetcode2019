/*
Number of Islands

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3
*/
/*
    Time complexity is O(m x n) because each cell is visited only once
*/
class Solution {
    int m,n;
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        
        m = grid.length;
        n = grid[0].length;
        
        int count = 0;
        
        for(int i = 0; i <m; i++ ) {
            for(int j = 0; j< n; j++){
                //sweet the area if 1
                if(grid[i][j] == '1'){
                    //DFS sweep
                    dfsSweepArea(grid, i, j);
                    count++;            
                }
            }
        }
        
        return count;
    }
    
    private void dfsSweepArea(char[][] grid, int i, int j){
        if(i<0 || j < 0 || i>=m || j >=n || grid[i][j] == '0')
            return;
        
        //mark as visited
        grid[i][j] = '0';
        
        //DFS recursion on all directions one by one
        dfsSweepArea(grid, i-1, j);
        dfsSweepArea(grid, i+1, j);
        dfsSweepArea(grid, i, j-1);
        dfsSweepArea(grid, i, j+1);
        
    }
}
