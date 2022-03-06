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
  -think u have empty extra array which u use to put unique nums. how would u use ptrs. u will use "write" ptr to indicate where unique elem goes
  */
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        
        int write = 0;//this is like index of new/extra space array 
        
        //sincce we are looking for duplicates, we start j from 1
        for(int j=1;j<nums.length;j++){
            //once we find uniue/non-duplicate elem, we add it to array (same arr here lol)
            if(nums[j] != nums[write]){
                write++; //we donw want to "replace" good num with new num                
                nums[write] = nums[j];
            }
        }
        
        //good is index, but we need to return length
        return write+1;
    }
}
