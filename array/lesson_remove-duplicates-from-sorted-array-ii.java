/*
80. Remove Duplicates from Sorted Array II
DescriptionHintsSubmissionsDiscussSolution

Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

It doesn't matter what you leave beyond the returned length.

*/
/*
This kind of soulution can be generalized to "k" duplicate elems too

two pointers. 
goodPos
runnerPos,

keep count of elem, and reset when diff
*/
class Solution {
    public int removeDuplicates(int[] nums) {
        int goodIndexToOverwrite = 1;//this would be index of additional array, if used to put unique elems only
        int count = 1;
        for(int j=1;j<nums.length;j++) {
            if(nums[j] == nums[j-1]) {
                count++;
            } else {
                count = 1;
            }            
                        
            if(count<=2) {                
                //slide, this overwirtes 3rd duplicate too                
                nums[goodIndexToOverwrite] = nums[j];                
                goodIndexToOverwrite++;
            }
        }
        return goodIndexToOverwrite;
    }
}
