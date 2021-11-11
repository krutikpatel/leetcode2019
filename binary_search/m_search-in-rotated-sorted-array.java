/* medium
Search in Rotated Sorted Array

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
*/
/*
Trick: find out which side is sorted, and narrow down that side and do normal binary search there
*/
class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        
        while(l<=r){
            int mid = l + (r-l)/2;
            if(nums[mid] == target)
                return mid;
            
            //check which side is sorted, and if its in that side, do normal binary search there
            
            if(nums[l] <= nums[mid]){
                //left side sorted
                
                //if target in this range - narrow to left side else go on right side
                if(target >= nums[l] && target <= nums[mid]){
                    r = mid-1;
                }else{
                    l = mid+1;
                }
            } else{
                //right side sorted
                
                //if target in this range - narrow to righ side else go on right side
                if(target >= nums[mid] && target <= nums[r]){
                    l = mid+1;                    
                }else{
                    r = mid-1;
                }
            }
        }
        
        return -1;
    }
}
