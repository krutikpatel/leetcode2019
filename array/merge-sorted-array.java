/*
Merge Sorted Array

Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

    The number of elements initialized in nums1 and nums2 are m and n respectively.
    You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.

Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
*/
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        /*
        Two pointers - begin both from end
        
        If you begin from 0, you will need new array to store the merge. ok for merge-sort
        */
        int i=m-1,j=n-1;
        int ptr = nums1.length-1;
        while(i >= 0 && j>=0){
            if(nums1[i] >= nums2[j]){
                nums1[ptr] = nums1[i];
                i--;
            }else{
                nums1[ptr] = nums2[j];
                j--;
            }
            ptr--;
        }
        
        //one of i or j will break above loop earlier so we need to go thru them
        while(i>=0){
            nums1[ptr] = nums1[i];
            i--;
            ptr--;
        }
        while(j>=0){
            nums1[ptr] = nums2[j];
            j--;
            ptr--;
        }
    }
}
