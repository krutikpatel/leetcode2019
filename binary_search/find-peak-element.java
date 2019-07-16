/*
Find Peak Element

A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.

Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
*/
/*
Even for template 2, where we need to access mid+1,
-if its array problem, our r = arr.length-1
  -to cover case of arraysize = 1
*/
class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length-1; //trick - for any "array" problem - to avoid index overflow: since we need to look at mid+1, how do we cover arraySize =1 ?
        
        while(l<r){
            int mid = l+(r-l)/2;
            if(nums[mid+1] > nums[mid]){
                //go right - chase bigger elem
                l = mid+1;
            }else{
                r=  mid;
            }
        }
        
        return l;
    }
}
