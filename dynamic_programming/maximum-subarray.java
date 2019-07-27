/*
Maximum Subarray

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
*/
/*
    Greedy cum DP
    sumSoFar is kind of memoized for DP
    
    leetcode soln explains this as approach as well
    */
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int sumSoFar = nums[0];
        
        for(int i=1;i<nums.length;i++){
            sumSoFar = Math.max(nums[i], sumSoFar+nums[i]);//we pick local max at/upto index i
            maxSum = Math.max(maxSum, sumSoFar); // Even if we picked nums[i] in last line, our max is still whatver max is so far..
        }
        return maxSum;
    }
}
