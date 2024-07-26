package dplocal.fibonaciinums;

/*
 * Given a stair with ‘n’ steps, implement a method to count how many possible ways are there to reach the top of the staircase, 
 * given that, at every step you can either take 1 step, 2 steps, or 3 steps.
 */
public class _2_Staircase {

	private static _2_Staircase instance = new _2_Staircase();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Use the base case info we have 
	 * n=0 -> 1
	 * n=1 -> 1
	 * n=2 -> 2 (either use 1s or 2)
	 */
	private int CountWays(int n) {
		if( n == 0)
	      return 1; // base case, we don't need to take any step, so there is only one way
	      
	    if(n == 1)
	      return 1; // we can take one step to reach the end, and that is the only way

	    if(n == 2)
	      return 2; // we can take one step twice or jump two steps to reach at the top

	    // if we take 1 step, we are left with 'n-1' steps;
	    int take1Step = CountWays(n-1); 
	    // similarly, if we took 2 steps, we are left with 'n-2' steps;
	    int take2Step = CountWays(n-2); 
	    // if we took 3 steps, we are left with 'n-3' steps;
	    int take3Step = CountWays(n-3); 

	    //sum all 3 ways
	    return take1Step + take2Step + take3Step;
	}
	
	//top-down recursive
	private int CountWays2(int n, int[] dp) {
		if( n == 0)
	      return 1; // base case, we don't need to take any step, so there is only one way
	      
	    if(n == 1)
	      return 1; // we can take one step to reach the end, and that is the only way

	    if(n == 2)
	      return 2; // we can take one step twice or jump two steps to reach at the top
	    
	    if(dp[n] != 0)
	    	return dp[n];
	    
	    dp[n] = CountWays2(n-1,dp) + CountWays2(n-2,dp) + CountWays2(n-3,dp);
	    return dp[n];
	}
	
	//bottom-up iterative
	private int CountWays3(int n) {
		int[] dp = new int[n+1];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for(int i=3;i<=n;i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		return dp[n];
	}
}
