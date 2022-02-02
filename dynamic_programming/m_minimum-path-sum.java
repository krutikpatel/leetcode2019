/*
Minimum Path Sum

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        //try to fill each position with optimal option, when we reach end, we will reach with optimal path !
        int[][] dp = new int[grid.length][grid[0].length];

        /*
        -try to fill each position with optimal option, when we reach end, we will reach with optimal path !
        -try to work out one sample, fill DP from top-left to bottom-right,
        and u will see pattern !
        */
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 && j == 0) dp[i][j] = grid[i][j];
                else if (i == 0) //we move right only
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                else if (j == 0) //we move down only
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                else 
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);//min of left and top
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];

    }
}
