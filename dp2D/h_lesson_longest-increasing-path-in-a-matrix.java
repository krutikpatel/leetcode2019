/*
Longest Increasing Path in a Matrix
DescriptionHintsSubmissionsDiscussSolution
Given an integer matrix, find the length of the longest increasing path.
From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
Example 1:
Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].
*/
class Solution {
    private int m, n;

    //int count = 0;
    /*
    DFS + memoization
    time complexity : mxn. each cell calculated only once. later used DP. proved using count in dfs method
    
    No need to track visited, because we are looking for optimum solution, so rather we store soln at each node (memoization), and re-use it next time.
    */    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        m = matrix.length; n = matrix[0].length;
        
        //store max len for each node
        int[][] dp = new int[m][n];
        int ans = 0;
        
        //DFS from each cell, BUT with memoization
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans = Math.max(ans, dfs(matrix, i, j, dp));
        //System.out.println(count); time complexity
        return ans;
    }
    
    //return max path for cell (i,j)
    private int dfs(int[][] matrix, int i, int j, int[][] dp) {
        if(dp[i][j] != 0) {
            return dp[i][j];
        }
        
        //if(i >= m-1 || i < 1 || j >= n || j <0 || matrix[i][j] )
        
        int len = 0;//curr cell        
        
        /*
        First, DFS in all 4 directions, if valid. dp[i][j] is max of all 4 dirs +1
        Then at last count curr node
        */
        if(i+1 <m && matrix[i+1][j] > matrix[i][j])
            len = Math.max(len, dfs(matrix, i+1, j, dp));
        
        if(i-1 >=0 && matrix[i-1][j] > matrix[i][j])
            len = Math.max(len,dfs(matrix, i-1, j, dp));
        
        if(j+1 < n && matrix[i][j+1] > matrix[i][j])
            len = Math.max(len, dfs(matrix, i, j+1, dp));
        
        if(j-1 >= 0 && matrix[i][j-1] > matrix[i][j])
            len = Math.max(len, dfs(matrix, i, j-1, dp));
        
        len+=1; //count curr node
        dp[i][j] = len; //dp[i][j] is max of all 4 possible directions
        
        return len;
    }
}
