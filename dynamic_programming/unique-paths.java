/*
Unique Paths

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Above is a 7 x 3 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
*/


    /*
    Similar questions:
91. Decode Ways
70. Climbing Stairs
509. Fibonacci Number

Practice them in a row for better understanding
    */

/*
Classic example of solving dp problem with matrix memoization
*/
class Solution {
    /*
    use dp[][] matrix.
    dp[i][j] = ways to reach dp[i][j] point
        -you can reach it from 
        dp[i-1][j] (upper cell) and
        dp[i][j-1] (left cell)
        
    Thus,
    dp[i][j] = dp[i-1][j] + dp[i][j-1]
    
    start filling from dp[0][0]. 
    */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //initialize smallest problem know solutions. 
        //only one way to reach all points in single row/column from [0][0]
        
        //if only one row existed
        for(int i = 0;i<m;i++)
            dp[i][0] = 1;
        //if only one col existed
        for(int j = 0;j<n;j++)
            dp[0][j] = 1;
        
        for(int i = 1;i<m;i++){
            for(int j = 1;j<n;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        return dp[m-1][n-1];
    }
}
