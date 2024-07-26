package dplocal.fibonaciinums;

/*
 * Given a staircase with ‘n’ steps and an array of ‘n’ numbers representing the fee that you have to pay if you take the step. 
 * Implement a method to calculate the minimum fee required to reach the top of the staircase (beyond the top-most step). 
 * At every step, you have an option to take either 1 step, 2 steps, or 3 steps. 
 * You should assume that you are standing at the first step.
 * 
 * Example:
 * Number of stairs (n) : 6
	Fee: {1,2,5,2,1,2}
	Output: 3
	Explanation: Starting from index '0', we can reach the top through: 0->3->top
	The total fee we have to pay will be (1+2).

 */
public class _5_MinJumpsWithFee {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
//brute force - recursive
	public int findMinFee(int[] fee) {
	    return findMinFeeRecursive(fee, 0);
	}
		  
	private int findMinFeeRecursive(int[] fee, int currentIndex) {
	    if( currentIndex > fee.length - 1)
	    	return 0;//reached so no jump no fee = 0
	
	    int currFee = fee[currentIndex];
	    
	    // if we take 1 step, we are left with 'n-1' steps;
		int take1Step = findMinFeeRecursive(fee, currentIndex + 1);
		
		// similarly, if we took 2 steps, we are left with 'n-2' steps;
		int take2Step = findMinFeeRecursive(fee, currentIndex + 2);
		
		// if we took 3 steps, we are left with 'n-3' steps;
	    int take3Step = findMinFeeRecursive(fee, currentIndex + 3);
	    
	     
	    //recursive min of all 3 options
	    int min = Math.min(Math.min(take1Step, take2Step), take3Step);
	
	    return min + currFee;
	}

//top-down memoization
	private int findMinFeeDpRec(int[] fee, int currentIndex, int[] dp) {
	    if( currentIndex > fee.length - 1)
	      return 0; 
	
	    if(dp[currentIndex] == 0) {
		    // if we take 1 step, we are left with 'n-1' steps;
			int take1Step = findMinFeeDpRec(fee, currentIndex + 1, dp);
			
			// similarly, if we took 2 steps, we are left with 'n-2' steps;
			int take2Step = findMinFeeDpRec(fee, currentIndex + 2, dp);
			
			// if we took 3 steps, we are left with 'n-3' steps;
		    int take3Step = findMinFeeDpRec(fee, currentIndex + 3, dp);
		    
		    //recursive min of all 3 options
		    int min = Math.min(Math.min(take1Step, take2Step), take3Step);
		    dp[currentIndex] = min + fee[currentIndex];
	    }
	    
	    return dp[currentIndex];
	}
	
//bottom-up dp
	//some logic needed to populate dp[0], dp[1] and dp[2]
}
