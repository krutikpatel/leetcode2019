/*
Product of Array Except Self
DescriptionHintsSubmissionsDiscussSolution

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
*/
class Solution {
    /*
    Idea: res[i] = product on left * product on right
    
    for that, we need :
    two iteration
    -one from left, skipping curr elem
    -one from right, skipping curr elem. 
    */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        
        int soFarFromLeft = 1;
        for(int i=0;i<n;i++) {
            //first store res
            res[i] = soFarFromLeft;
            
            //update product for next iteration
            soFarFromLeft = soFarFromLeft*nums[i];
        }
        
        int soFarFromRight = 1;
        for(int i=n-1;i>=0;i--) {
            //first store res
            res[i] = res[i] * soFarFromRight;
            
            //update product for next iteration
            soFarFromRight = soFarFromRight*nums[i];
        }
        
        return res;
    }
}
