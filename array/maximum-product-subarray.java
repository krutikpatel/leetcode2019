/*
Maximum Product Subarray

Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
*/
class Solution {
    
    //https://www.youtube.com/watch?v=vtJvbRlHqTA
    public int maxProduct(int[] nums) {
        if(nums.length == 0)
            return 0;
        
        //int max = Integer.MIN_VALUE;
        //track max,min,res till index i
        int max = nums[0];  //to keep track of contiguous max from 0 till i 
        int min = nums[0];  //to consider -ve num growing so far and in case we encounter one more -ve.
        int res = nums[0];  //separate than max because local max may be not global max
        
        for(int i=1;i<nums.length;i++){
            int temp = max;
            /* There are only 3 things possible
            1. nums[i] alone is greater till index i (rather than multiplying with prev product). (we ignore prev max,min)
            2. nums[i] * min_till_i gives greater result.
            3. nums[i] * max_till_i gives greater result.
            */
            max = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            min = Math.min(nums[i], Math.min(temp * nums[i], min * nums[i]));
            
            //trick local-max cannot be answer so we track res : [2,3,-2,4] , at -2, localMax = -2
            if(max > res){                
                res = max;
            }
                
        }
                
        return res;
    }
}
