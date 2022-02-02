/*
Climbing Stairs

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
*/
class Solution {
    public int climbStairs(int n) {
        
        if(n <= 2)
            return n;
        
        int[] memo = new int[n+1];    
        memo[1] = 1;
        memo[2] = 2;
        
        helperTopDown(n, memo);
        //helperBottomUp(0, n, memo);
        
        return memo[n];
    }
    
    private int helperTopDown(int n, int[] memo){
        //if(n <= 0) not reqd
        //    return 0;
        if(n == 1)
            return 1;
        
        if(memo[n] > 0)
            return memo[n];
        
        int ret = helperTopDown(n-1, memo) + helperTopDown(n-2, memo);
        memo[n] = ret;
        
        return ret;
    }
    
    //
    private int helperBottomUp(int n){
        int[] dp = new int[n + 1];
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
          dp[i] = dp[i-1] + dp[i - 2];
        }
        return dp[n];
    }
}
