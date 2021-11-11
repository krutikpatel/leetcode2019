/* medium
34. Find First and Last Position of Element in Sorted Array
DescriptionHintsSubmissionsDiscussSolution

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

*/
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return new int[]{-1,-1};
        
        int a = findBegin(nums,target);
        int b = findEnd(nums,target);
        
        return new int[]{a,b};
    }
    
    /*
    dont rty to use Template II
    -use Template I
    */
    private int findBegin(int[] nums, int target){
        int l=0;
        int r = nums.length-1;
        
        int ret = -1;
        //narrow down towards leftmost target
        while(l<=r){
            int mid = l+(r-l)/2;
            
            if (nums[mid] >= target){
                //move left
                r = mid-1;
            } else {
                l = mid+1;
            }
            //track last valid target index
            if(nums[mid] == target)
                ret = mid;
        }
        
        return ret;
    }
    
    private int findEnd(int[] nums, int target){
        int l=0;
        int r = nums.length-1;
        
        int ret = -1;
        //narrow down towards rightmost target
        while(l<=r){
            int mid = l+(r-l)/2;
            
            if (nums[mid] <= target){
                //move right
                l = mid+1;                
            } else {
                r = mid-1;
            }
            //track last valid target index
            if(nums[mid] == target)
                ret = mid;
        }
        
        return ret;
    }
    
    /////// Template 2 - end not working ///////
    private int findBegin2(int[] nums, int target){
        int l=0;
        int r = nums.length-1;
        
        //narrow down towards leftmost target
        while(l<r){
            int mid = l+(r-l)/2;
            
            if (nums[mid] >= target){
                //move left
                r = mid;
            } else {
                l = mid+1;
            }
        }
        
        if(nums[l] == target)
            return l;
        else
            return -1;
    }
    
    private int findEnd2(int[] nums, int target){        
        int l = 0;
        int r = nums.length-1;
        //narrow down towards rightmost target
        /*
        mid is target - go right inclusive
        mid is less than target - go right
        mid is greater than target - go left - not inclusive
        */
        while(l<r){
            int mid = l+(r-l)/2;            
            
            if(nums[mid] == target && nums[mid+1] != target)
                return mid;
            else if (nums[mid] > target){
                //move left
                r = mid -1;
            } else {
                //nums[mid] <= target
                l = mid;
            }
           
        }
        
        System.out.println(l);
        if(nums[l] == target)
            return l;
        else
            return -1;
    }
}
