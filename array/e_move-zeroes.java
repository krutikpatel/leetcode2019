/* Easy
 Move Zeroes

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:

    You must do this in-place without making a copy of the array.
    Minimize the total number of operations.
*/

class Solution {
    /*
    Two pointers
    -slow and fast
    
    see how you would do it visually, we pick non zero elem, and start putting them from 0th index.
    */
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0)
            return;
        
        int good = 0;
        for(int j=0;j<nums.length;j++){
            if(nums[j] != 0){
                nums[good] = nums[j];
                good++;
            }                
        }
        while(good<nums.length){
            nums[good] = 0;
            good++;
        }
    }
}
