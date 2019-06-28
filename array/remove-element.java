/*
Remove Element

Given an array nums and a value val, remove all instances of that value in-place and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example 1:

Given nums = [3,2,2,3], val = 3,

Your function should return length = 2, with the first two elements of nums being 2.

It doesn't matter what you leave beyond the returned length.

*/

class Solution {
    /*
    Two pointers. Both begin at 0
    
    Think : cherry picking and adding new new array, but instead we are just adding in same array
        -ptr1: runner : goes and checks each elem
        -ptr2: slow. we keep track of last good elem index to track where to add next elem
        
    */
    public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0)
            return 0;
        
        //slow ptr : we keep track of last good elem index to track where to add next elem
        int good = 0;
        
        //j ptr is runner/fast ptr. it goes and checks each elem
        for(int j=0;j<nums.length;j++){
            if(nums[j] != val){
                nums[good] = nums[j];
                good++;
            }                        
        }
        
        return good;
        
    }
}
