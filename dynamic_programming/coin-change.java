/*
Coin Change

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1

Example 2:

Input: coins = [2], amount = 3
Output: -1
*/
/*
NOTE:
easily solvable with BFS too
BFS used to give optimal solution

*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0)
		return 0;
	    
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        
        //bottom -up. we will go 0 -> n and fill dp for each num till amount
        for(int i=1;i<=amount; i++){
            dp[i] = amount+1;   //Integer.MAX_VALUE; will create overflow when we do +1
            
            //try each coin, and find min for dp[i]
            //we dont care if ans was found, if val is MAX, we will know ans was not found
            for(int j=0;j<coins.length;j++){
                if(coins[j] <= i){
                    //we can use this coin
                    dp[i] = Math.min(dp[i], 1+dp[i - coins[j]]);    //min with dp[i] will catch dp made with smaller coins
                }
            }
        }
        
        return dp[amount] > amount ? -1:dp[amount];
    }
}
