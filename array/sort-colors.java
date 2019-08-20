/*
Sort Colors
DescriptionHintsSubmissionsDiscussSolution

Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Follow up:
IMP
    A rather straight forward solution is a two-pass algorithm using counting sort.
    Pass 1: First, iterate the array counting number of 0's, 1's, and 2's, 
    Pass 2: then overwrite array with total number of 0's, then 1's and followed by 2's.
    
    Could you come up with a one-pass algorithm using only constant space?

*/
/*
1-pass is essentially the 3-way quick partition method in quicksort. check this gret video demo from Professor Robert Sedgewick

// 2 pointers
    //gather all 0's to left. Blind swap. move iter because it was 0
    //gather all 2's to right. Blind swap. dont move iter because it was blind swap. it will be taken care of in next iteration.
        
*/
class Solution {
    public void sortColors(int[] nums) {
        if(nums == null || nums.length <= 1)
            return;
                
        int ind1 = 0;
        int ind2 = nums.length-1;
        int i=0;
        while(i<=ind2) { //v imp. makes sense to stop when reach ind2. if you keep going, it will messup results
            if(nums[i] == 0) {
                //swap with start.
                swap(i,ind1,nums);
                ind1++;
                i++;
            } else if(nums[i] == 2) {
                //swap with end
                swap(i,ind2,nums);
                ind2--;
            } else {
                //its 1
                i++;
            }
        }
    }
    
    private void swap(int a, int b, int[] nums) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
