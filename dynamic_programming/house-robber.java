/*
House Robber

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.

Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
*/
class Solution {
    public int rob(int[] nums) {
        if(nums.length==0)
            return 0;
        if(nums.length == 1)
            return nums[0];
             
        int[] memo = new int[nums.length+1];
/*        
        //base case that we know solution to is nums[0] of f(0) ONLY.
        //because f(n) needs n-1 and n-2 we add them to memo
        memo[0] = 0;//this is like -1
        memo[1] = nums[0];//this is like 0
        
        /*
            -bottom-up 0->n. Since we know f(0), we want to begin with 1
            -Also, we try to fill up memo (arr of answers). So we keep to maintain our correlation function for memo
            memo : 0, nums[0], ?
        */
        
/*        for(int i=1;i<nums.length;i++){
            //we have two choices, wither we take index 0 and 2 or 1. this choices valid at any place.
            memo[i+1] = Math.max(nums[i] + memo[i-1], memo[i]);//memo[i] = max(nums[i] + nums[i-2], nums[i-1])
            //System.out.println(memo[i+1]);
        }        
*/

        //no need to begin with 0-1 elem (elem before 0th)
        memo[0] = nums[0];
        memo[1] = Math.max(nums[0],nums[1]);
        for(int i=2;i<nums.length;i++) {
            memo[i] = Math.max(nums[i] + memo[i-2], memo[i-1]);
        }
        
        return memo[nums.length-1];
        //return memo[nums.length];//last elem of memo
        
        /*
        Note: in reality we only need last 2 max sums.
        Ans is :  max(nums[i] + f(i-2), f(i-1) )
        
        so solution can be:
        
        if (nums.length == 0) return 0;
        int prev1 = 0;
        int prev2 = 0;
        for (int num : nums) {
            int tmp = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = tmp;
        }
        return prev1;
        */
    }
}
