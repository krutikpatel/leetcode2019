/* medium
Find Minimum in Rotated Sorted Array

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1

Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0
*/
class Solution {
    /*
    Trick:
    look for unsorted side -if r is smaller than mid
        -min has to be on that side
        -and we narrow down towards smaller elem too, so end up with right answer
    
    proof/verification:
        -in case of sorted array, we will go towards left so will chase min anyways
        -verify with 1,2,3 elems - rotated
    */
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length-1;
        
        while(l<r){
            int mid = l+(r-l)/2;
            
            //look for unsorted side -if r is smaller
            //min has to be on that side
            if(nums[r] <= nums[mid]){
                //smaller on right
                l = mid+1;
            }else{
                r = mid;
            }                
        }
        
        return nums[l];
    }
}
