package dplocal.fibonaciinums;
/*
 * You are given an integer array coins representing coins of different denominations and an integer amount 
 * representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by
 any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.
 

Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

 */
public class _7_CoinChange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
/*
 * Brute force
 * -utilize each coin as many times as possible
 */
	public int coinChange(int[] coins, int amount) {
	    return coinChange(0, coins, amount);
		//return coinChange2(coins, amount);
	}
	/* this does not work
	private int coinChange2(int[] coins, int rem) {
	    if (rem < 0) return -1;
	    if (rem == 0) return 0;
	    
	    int min = Integer.MAX_VALUE;
	    for (int coin : coins) {
	      int res = coinChange2(coins, rem - coin);
	      if (res >= 0 && res < min)
	        min = 1 + res;
	    }
	    
	    return (min == Integer.MAX_VALUE) ? -1 : min;
	}*/

	/*
	 * For each coin, try to use it 1 to as many times as possible. AND record the min of all solutions.
	 * -that min is we count for final solution
	 * 
	 * IMP - keep reducing the amount for recursive call, rather than going 0 to n
	 */
	private int coinChange(int idxCoin, int[] coins, int amount) {
	    if (amount == 0)
	    	return 0;
	    
	    //invalid cases
	    //amount is remaining but we ran out of coins!
	    if(idxCoin >= coins.length) {
	    	return -1;//why? why 0 wont work? // Input: coins = [2], amount = 3 => -1 indicates cant make sum
	    }
	    if(amount<0) {
	    	return -1;
	    }
	    
	    int currCoin = coins[idxCoin];
	    int maxCountCurrCoin = amount/currCoin;
	    int minCost = Integer.MAX_VALUE;//track min
	    
	    //try using this coin various times
	    //note: reverse loop works too
	    for(int c = 0;c<=maxCountCurrCoin;c++) {
	    	//if there is scope to add another coin
	    	int currAmount = c*currCoin; 
	    	//if(amount >= currAmount) { - it will be less
	    		int res = coinChange(idxCoin+1, coins, amount - currAmount);
	    		//if res is valid
	    		if(res != -1) {
	    			minCost = Math.min(minCost, res+c);//res+c is current solution
	    		}
	    	//}
	    }
	    
	    return minCost == Integer.MAX_VALUE ? -1 : minCost;//return -1 if no valid ans (coins are too large or cant sumup
	}
	
	/* follow this:
	 * top -down recursive
	 * 
	 *  memoise from brute force. we might calculate soln to rem amount again, so memo solution.
    	dp[amount+1] solutions
    	
    	-loop is diff than brute force
    	-here we loop coins 

dp[] stores solution to subproblems/remaining-amounts
imp -rec call is made on reduced amount
     
	 */
	 public int coinChange(int[] coins, int amount) {
	        if (amount < 1) return 0;
	        //return coinChange(0,coins, amount, new Integer[amount+1]);    
	        return helper(coins, amount, new Integer[amount+1]);    
	    }
	    
	    private int helper(int[] coins, int rem, Integer[] dp) { // rem: remaining coins after the last step; count[rem]: minimum number of coins to sum up to rem
	        if(rem<0) return -1; // not valid
	        if(rem==0) return 0; // completed
	        
	        if(dp[rem] != null) return dp[rem]; // already computed, so reuse
	        
	        int min = Integer.MAX_VALUE;
	        //try all coins, no need to maintain coinIndex
	        for(int coin : coins) {
	            int res = helper(coins, rem-coin, dp);
	            if(res>=0 && res < min)
	                min = 1+res;
	        }
	        dp[rem] = (min==Integer.MAX_VALUE) ? -1 : min;
	        return dp[rem];
	    }
	    
/*
 * bottom-up iterative	
 * 
 *     O(amount * no of coins)
 */
    public int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0)
		return 0;
	    
        //we try to fill ans till amount
        int[] dp = new int[amount + 1];
        //base case
        dp[0] = 0;
        
        //bottom -up. we will go 0 -> n and fill dp for each num till amount
        //since we start from 0, we wont have to worry about re-use of same coin (in same iteration)
        for(int i=1;i<=amount; i++){
            dp[i] = amount+1;   //Integer.MAX_VALUE; will create overflow when we do +1
            
            //fin dp[i]
            //try each coin, and find min for dp[i]
            //we dont care if ans was found, if val is MAX, we will know ans was not found

            for(int c: coins) {
            	//int currCoinVal = coins[j];
                if(c <= i){//less than amount
                    //we can use this coin
                    dp[i] = Math.min(dp[i], 1+dp[i - c]);    //min with dp[i] will catch dp made with smaller coins
                }
            }
        }
        
        //return dp[amount] > amount ? -1:dp[amount];
        return dp[amount] ==  amount+1  ? -1:dp[amount];
    }
    
}
