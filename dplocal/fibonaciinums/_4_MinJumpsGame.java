package dplocal.fibonaciinums;

/*
 * Given an array of positive numbers, where each element represents the max number of jumps that can be made forward from that element, 
 * write a program to find the minimum number of jumps needed to reach the end of the array (starting from the first element). 
 * If an element is 0, then we cannot move through that element.

	Example 1:
	
	Input = {2,1,1,1,4}
	Output = 3
	Explanation: Starting from index '0', we can reach the last index through: 0->2->3->4

 */
public class _4_MinJumpsGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_4_MinJumpsGame aj = new _4_MinJumpsGame();
	    int[] jumps = {2, 1, 1, 1, 4};
	    System.out.println(aj.countMinJumps(jumps));
	    jumps = new int[]{1, 1, 3, 6, 9, 3, 0, 1, 3};
	    System.out.println(aj.countMinJumps(jumps));
	    
	}
	public int countMinJumps(int[] jumps) {
	    return this.countMinJumpsRecursive(jumps, 0);
	}
	///////////
	/*
	 * brute force
	 * 
	 * Ret val: 
	 * 		0 :  means can reach
	 * 		MAX: cannot
	 */
	 private int countMinJumpsRecursive(int[] jumps, int currentIndex) {
	    // if we have reached the last index, we don't need any more jumps
	    if( currentIndex >= jumps.length - 1)
	      return 0;

	    //imp
	    if (jumps[currentIndex] == 0)
	      //return Integer.MAX_VALUE;//cannot proceed. i think we can use -1 as well
	    	return -1;

	    int minJumps = Integer.MAX_VALUE;
	    
	    //try possible elems reachable from currentIndex
/*	    
	    int start = currentIndex + 1;
	    int end = currentIndex + jumps[currentIndex];
	    while(start < jumps.length && start <= end) {
	      // jump one step and recurse for the remaining array
	      int minJumps = countMinJumpsRecursive(jumps, start);
	      
	      if(minJumps != Integer.MAX_VALUE)
	        totalJumps = Math.min(totalJumps, minJumps + 1);
	      
	      start++;
	    }
*/
	    int curr = jumps[currentIndex];
		for(int i=1;i<=curr;i++){
			// jump one step and recurse for the remaining array
		    int currJumps = countMinJumpsRecursive(jumps,currentIndex+i);
		    
		    //if(currJumps != Integer.MAX_VALUE)//cond needed
		    if(currJumps != -1)//cond needed
		    	minJumps = Math.min(minJumps, currJumps + 1);//counting curr jump here
		      
		}
	    return minJumps;
	  }
	 
	 /*
	  * top-down recursive
	  * -simply add memoised dp array in above solution
	  */
	 private int countMinJumpsRecursive(int[] jumps, int currentIndex, int[] dp) {
	    // if we have reached the last index, we don't need any more jumps
	    if( currentIndex == jumps.length - 1)
	      return 0;

	    if (jumps[currentIndex] == 0)
	      return Integer.MAX_VALUE;//cannot proceed

	    if(dp[currentIndex] != 0) {
	    	return dp[currentIndex];
	    }
	    
	    int totalJumps = Integer.MAX_VALUE;
	    
	    //try possible elems reachable from currentIndex
	    int start = currentIndex + 1;
	    int end = currentIndex + jumps[currentIndex];
	    while(start < jumps.length && start <= end) {
	      // jump one step and recurse for the remaining array
	      int minJumps = countMinJumpsRecursive(jumps, start);
	      
	      if(minJumps != Integer.MAX_VALUE) {
	        totalJumps = Math.min(totalJumps, minJumps + 1);
	        dp[currentIndex] = totalJumps;
	        		
	      }
	      start++;
	    }
	    return dp[currentIndex];
	 }
}
