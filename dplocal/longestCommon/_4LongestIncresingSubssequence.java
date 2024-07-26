package dplocal.longestCommon;

import java.util.Arrays;

/*
 * Problem Statement

Given a number sequence, find the length of its Longest Increasing Subsequence (LIS). In an increasing subsequence, all the elements are in increasing order (from lowest to highest).

Example 1:

Input: {4,2,3,6,10,1,12}
Output: 5
Explanation: The LIS is {2,3,6,10,12}.

==> realize that although its subsequence, longest one can begin from any index and not necessarily from 1st elem
 */
//Leetcode
class Solution {
    /*
    This DP solution is followable: O(n^2)
    https://leetcode.com/problems/longest-increasing-subsequence/discuss/74836/My-easy-to-understand-O(n2)-solution-using-DP-with-video-explanation
    
    -start filling DP from left side
    -for i, every elem on left < that elem, increase dp[i]. this will work becaue we started from left most 2 elems, 0 and 1
    
    O(nlogn) is using binary search, and taking advantage of fact that, Arrays.binarySearch will return index of one less when elem not found. we can use that info to find increasing sequence
    */
    public int lengthOfLIS(int[] nums) {
        if(nums.length <=1){
            return nums.length;
        }
        
        int[] dp = new int[nums.length];
        int max = 1;
        //two ptrs of subsequence boundary i and j
        //j=start and i=end
        for(int i = 0;i<nums.length;i++){
            dp[i] = 1;//base case
            
            //check all vals from 0 to i -> we know soln to hese subproblems, because we are building up i from 0 to n
            for(int j = 0;j<i;j++){
                if(nums[j] < nums[i]){
                    //increasing
                    dp[i] = Math.max(dp[i], 1+dp[j]);
                    
                    //track global max
                    max = Math.max(max,dp[i]);
                }
            }
            
        }
        return max;
    }
}
/////////
public class _4LongestIncresingSubssequence {

/*
 * Brute force
 * 
 * A basic brute-force solution could be to try all the subsequences of the given number sequence. 
 * We can process one number at a time, so we have two options at any step:

1. If the current number is greater than the previous number that we included, 
	we can increment our count and make a recursive call for the remaining array.
2. We can skip the current number to make a recursive call for the remaining array.

The length of the longest increasing subsequence will be the maximum number returned by the two recurse calls from the above two options.

==> use 2 indices for comparision
	-because of the nature of problem. we need to access prev elem for comparision
	-1char string = 1
	
 */
	public int findLISLength(int[] nums) {
	      return findLISLengthRecursive(nums, 0, -1);
	}

	private int findLISLengthRecursive(int[] nums, int currentIndex, int previousIndex) {
	    //string ended or empty string basecase
		if(currentIndex == nums.length)
	      return 0;

	    // include nums[currentIndex] if it is larger than the last included number
	    int c1 = 0;
	    if(previousIndex == -1 || nums[currentIndex] > nums[previousIndex])	//if incresing order
	      c1 = 1 + findLISLengthRecursive(nums, currentIndex+1, currentIndex);

	    // excluding the number at currentIndex - KEEP PRECINDEX AS IS !
	    int c2 = findLISLengthRecursive(nums, currentIndex+1, previousIndex);

	    return Math.max(c1, c2);
	}
	
/*
 * top -down recusrive	
 */
	private int findLISLengthRecursive(int[] nums, int currentIndex, int previousIndex, Integer[][] dp) {
	    if(currentIndex == nums.length)
	      return 0;

	    //IMP previousIndex+1
	    if(dp[currentIndex][previousIndex+1] == null) {
		    // include nums[currentIndex] if it is larger than the last included number
		    int c1 = 0;
		    if(previousIndex == -1 || nums[currentIndex] > nums[previousIndex])	//if incresing order
		      c1 = 1 + findLISLengthRecursive(nums, currentIndex+1, currentIndex, dp);
	
		    // excluding the number at currentIndex - KEEP PREVINDEX AS IS !
		    int c2 = findLISLengthRecursive(nums, currentIndex+1, previousIndex, dp);
		    
		    dp[currentIndex][previousIndex+1] = Math.max(c1, c2);
	    }
	    
	    return dp[currentIndex][previousIndex+1];
	}
	
/*
 * bottom - up iterative
 * 
 * -> try to go with this: 1D dp	
 */
	public int lengthOfLIS(int[] nums) {
	        int[] dp = new int[nums.length];
	        
	        //base case ans is 1 for each index
	        Arrays.fill(dp, 1);
	        
	        //i = curr
	        //j = prev
	        //each time to fill dp[i], we go from j = 0 to i
	        for (int i = 1; i < nums.length; i++) {
	            for (int j = 0; j < i; j++) {
	                if (nums[i] > nums[j]) {
	                	//max of seq starting at i OR prev+1
	                    dp[i] = Math.max(dp[i], dp[j] + 1);
	                }
	            }
	        }
	        
	        //find max val in dp[] - could have been done above
	        int longest = 0;
	        for (int c: dp) {
	            longest = Math.max(longest, c);
	        }
	        
	        return longest;
	}
}
