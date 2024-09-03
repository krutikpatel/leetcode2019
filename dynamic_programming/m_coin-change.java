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
public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); //Integer.MAX_VALUE; will create overflow when we do +1
        dp[0] = 0;

	//we fill each dp[x] to reach last one
	//start filling DP for each amount vals from 1 to n
        for (int i = 1; i <= amount; i++) {// figure out dp
	    //and at each dp try to use each coins once, since we are building bottom-up
            for (int c : coins) {//coins
                if (c <= i) {
                    dp[i] = Math.min(dp[i], 1+ dp[i - c]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0)
		return 0;
	    
        //we try to fill ans till amount
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        
        //bottom -up. we will go 0 -> n and fill dp for each num till amount
        //since we start from 0, we wont have to worry about re-use of same coin (in same iteration)
        for(int i=1;i<=amount; i++){
            dp[i] = amount+1;   //Integer.MAX_VALUE; will create overflow when we do +1
            
            //fin dp[i]
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
