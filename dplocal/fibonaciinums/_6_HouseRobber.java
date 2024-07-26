package dplocal.fibonaciinums;

/*
 * There are n houses built in a line. A thief wants to steal the maximum possible money from these houses. 
 * The only restriction the thief has is that he can’t steal from two consecutive houses, 
 * as that would alert the security system. How should the thief maximize his stealing?

Problem Statement

	Given a number array representing the wealth of n houses, determine the maximum amount of money 
	the thief can steal without alerting the security system.
	
Example 1:
	
	Input: {2, 5, 1, 3, 6, 2, 4}
	Output: 15
	Explanation: The thief should steal from houses 5 + 6 + 4
*/
/*
 * Algo:
 * For every house i, we have two options:

	1. Steal from the current house (i), skip one and steal from (i+2).
	2. Skip the current house (i), and steal from the adjacent house (i+1).

	The thief should choose the one with the maximum amount from the above two options.
 */
public class _6_HouseRobber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

//brute force	- 0 to n
	public int findMaxSteal(int[] wealth) {
		    return findMaxStealRecursive(wealth, 0);
	}

	private int findMaxStealRecursive(int[] wealth, int currentIndex) {
	    if( currentIndex >= wealth.length)
	      return 0;

	    // option1. steal from current house and skip one to steal from the next house
	    int stealCurrent = wealth[currentIndex] + 
	                           findMaxStealRecursive(wealth, currentIndex + 2);
	    // skip current house to steel from the adjacent house
	    int skipCurrent = findMaxStealRecursive(wealth, currentIndex + 1);//============> imp to note that this result is just pure recursive call. we dont add anythign here like did above

	    return Math.max(stealCurrent, skipCurrent);
	}
	
// 0 to n recursive dp
	private int findMaxStealRecursiveDp(int[] wealth, int[] dp, int currentIndex) {
		if(currentIndex >= wealth.length)
			return 0;
		
		if(dp[currentIndex] == 0) {
			int useCurr = wealth[currentIndex] + findMaxStealRecursiveDp(wealth, dp, currentIndex+2);
			int useNext =  findMaxStealRecursiveDp(wealth, dp, currentIndex+1);
			
			dp[currentIndex] = Math.max(useCurr, useNext);
		}
		
		return dp[currentIndex];
	}
	
//bottom-up iterative
	private int findMaxStealIterativeDp(int[] wealth, int currentIndex) {
		if(currentIndex >= wealth.length)
			return 0;
		
		int[] dp = new int[wealth.length+1];
		dp[0] = wealth[0];
		dp[1] = Math.max(wealth[0], wealth[1]);//imp: tink as if only 2 houses, arr size 2
		
		for(int i=2;i<wealth.length;i++) {
			dp[i] = Math.max(dp[i-1], dp[i-2]+wealth[i]);
		}
		return dp[wealth.length-1];
		/*
		dp[0] = 0;//no houses
		dp[1] = wealth[0];
		
		//dp[1] = Math.max(wealth[0], wealth[1]);
		
		for(int i=1;i<wealth.length;i++) {
			dp[i+1] = Math.max(dp[i], dp[i-1]+wealth[i]);
		}
		
		return dp[wealth.length];
		*/
	}
}
