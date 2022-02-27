/* Medium
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
            //skipping nums[i] in the product
            res[i] = soFarFromLeft;
            
            //update product for next iteration
            soFarFromLeft = soFarFromLeft*nums[i];
        }
        
        int soFarFromRight = 1;
        //at this point res[i] is product of left side. res[i] = product of i-1 elems
        for(int i=n-1;i>=0;i--) {
            //first store res
            //skipping nums[i] in the product
            res[i] = res[i] * soFarFromRight;
            
            //update product for next iteration
            soFarFromRight = soFarFromRight*nums[i];
        }
        
        return res;
    }
}
/*
My attempt:
class Solution {
    ////
    prod(x) = prod(0tox-1) * prod(x+1,len)
    0tox-1 -> go left to right
    x+1 to len -> got right to left
    skip x for both
    
    [1,2,3,4]
    p [1,1,2,6]
    q [24,12,4,1]
    
    ret [24,12,8,6]
    ////
    public int[] productExceptSelf(int[] nums) {
        
        int[] p = new int[nums.length];
        Arrays.fill(p,1);
        
        int prod = 1;//nums[0];
        //left to right
        for(int i=0;i<nums.length;i++){
            p[i] = prod;//by doing this we r essentially skipping ith elem in prod
            prod = prod*nums[i];//for next time
        }
        
        //System.out.println(p);
        //right to left
        int[] q = new int[nums.length];
        prod = 1;//nums[nums.length-1];
        for(int i = nums.length-1;i>=0;i--){
            q[i] = prod;
            prod = prod*nums[i];
        }
        //System.out.println(q);
        for(int i=0;i<nums.length;i++){
            nums[i] = p[i] * q[i];
        }
        return nums;
    }
}
*/
    
