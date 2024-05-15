/* medium
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

/*
    Notice that, dont think that we can use -ve sign Only once, (what is hown in above example is only sum=3 ans, but there are other cases where multiple -ve sign is used)

    Time complexity: O(2^n) . Size of recursion tree will be 2^n. n refers to the size of numsnums array.
    
    ==> optimized solutions are possible with DP. Below is only brute force soln

    Think + and - signs as two branches of binary treee, just two options to go form current node
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
