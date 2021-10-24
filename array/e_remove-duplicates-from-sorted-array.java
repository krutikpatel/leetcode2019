/* Easy
Remove Duplicates from Sorted Array

Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.
*/

class Solution {
  /*
  Two pointers : slow and fast
  */
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        
        int good = 0;
        
        //sincce we are looking for duplicates, we start j from 1
        for(int j=1;j<nums.length;j++){
            //once we find uniue/non-duplicate elem, we add it to array (same arr here lol)
            if(nums[j] != nums[good]){  //since arr is sorted, we can also say if nums[j] > nums[good]
                good++; //we donw want to "replace" good num with new num                
                nums[good] = nums[j];
            }
        }
        
        //good is index, but we need to return length
        return good+1;
    }
}
