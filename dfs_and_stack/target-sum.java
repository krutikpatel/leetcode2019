/*
Target Sum

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:

Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
*/
class Solution {
    int count = 0;
    
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0)
            return 0;
        
        helper(nums, S, 0, 0);
        return count;        
    }
    
    private void helper(int[] nums, int S, int currPos, int soFar){
        if(currPos == nums.length){
            if(soFar == S){
                count++;
                
            }
            return;
        }
                
        //DFS on nighbors one by one
        helper(nums, S, currPos+1, soFar + nums[currPos]);
        helper(nums, S, currPos+1, soFar - nums[currPos]);
    }
}
