package dplocal.fibonaciinums;

/*
 * Given a number ‘n’, implement a method to count how many possible ways there are to express ‘n’ as the sum of 1, 3, or 4.

	Example 1:
	
	n : 4
	Number of ways = 4
	Explanation: Following are the four ways we can express 'n' : {1,1,1,1}, {1,3}, {3,1}, {4} 

 */
public class _3_NumberFactors {

	private static _3_NumberFactors instance = new _3_NumberFactors();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = instance.CountWays(2);
	}
	
	/*
	 * Bruteforce
	 * Think basecase info available to us
	 * 
	 * -n will never turn negative because of n-4 or n-3, because those factors are preemptively covered in base cases.
	 * so base case will return val if n <3 or n<4
	 */
	public int CountWays(int n) {
	    if( n == 0)
	      return 1; // base case, we don't need to subtract anything, there is only one way

	    if(n == 1)
	      return 1; // we can subtract 1 to be left with zero, and that is the only way

	    if(n == 2)
	      return 1; // we can subtract 1 twice to get zero and that is the only way

	    if(n == 3)
	      return 2; // '3' can be expressed as {1,1,1}, {3}
	      
	    // if we subtract 1, we are left with 'n-1'
	    int subtract1 = CountWays(n-1);
	    // if we subtract 3, we are left with 'n-3'
	    int subtract3 = CountWays(n-3);
	    // if we subtract 4, we are left with 'n-4'
	    int subtract4 = CountWays(n-4);

	    return subtract1 + subtract3 + subtract4;
	  }

	/*
	 * top-down recursive
	 */
	public int CountWays2(int n, int[] dp) {
	    if( n == 0)
	      return 1; // base case, we don't need to subtract anything, there is only one way

	    if(n == 1)
	      return 1; // we can subtract 1 to be left with zero, and that is the only way

	    if(n == 2)
	      return 1; // we can subtract 1 twice to get zero and that is the only way

	    if(n == 3)
	      return 2; // '3' can be expressed as {1,1,1}, {3}

	    if(dp[n] == 0) {
	    	dp[n] = CountWays2(n-1,dp) + CountWays2(n-3,dp) + CountWays2(n-4,dp); 
	    }
	    return dp[n];
	}
	
	/*
	 * bottom-up iterative
	 */
	public int CountWays2(int n) {
		int[] dp = new int[n+1];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 2;
		
		for(int i=4;i<=n;i++) {
			dp[i] = dp[i-1] + dp[i-3] + dp[i-4];
		}
		return dp[n];
	}
}
