/*
215. Kth Largest Element in an Array
DescriptionHintsSubmissionsDiscussSolution

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.

*/
/*
Quicksort partition. 
-one you partition based on pivot, if you have x-1 elems on your left, xth elem is xth smallest elem.
*/
class Solution {
    //Note: shuffling array before using in quick select, will improve speed over varying inputs/datasets
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return Integer.MAX_VALUE;
        
        return findN_KthSmallest(nums, 0, nums.length - 1, nums.length - k); // kth largest is n-k th smallest :)        
    }
    
    //code for find kth smallest elem using Quick Select
    private int findN_KthSmallest(int[] nums, int l, int r, int k){
        if (l > r) return Integer.MAX_VALUE;
        
        //do partition around some pivot. and end is result of pivot index.
        int pivot = nums[r];
        //two pointers for partition. j is runnign till end, and keeps track of .. . i keeps track of wrong/big elem on left.
        //so whenever we find small elem using j, we swap it with i.
        //Quicksort partition lesson : i is placement index, where you will place next valid elem
        //j is runner, which will go each elem from left to right
        
        int left_i = l;
        for(int j = l;j<r; j++){
            if (nums[j] <= pivot){ // Put numbers < pivot, to pivot's left			    
                swap(nums, left_i, j);
                left_i++;
            }
        }
        //last step in parition, one more swap for pivot
        swap(nums, left_i, r);// Finally, swap A[end] with A[left]
        
        //now binary decide. left_i is where pivot is.
        //now we have smaller elems on left side.
        if(left_i == k){
            //we are standing at ans, kth
            return nums[left_i];
        } else if(left_i > k){
            //too many elems on left, so go left
            return findN_KthSmallest(nums, l, left_i-1, k);
        } else {
            //go right
            return findN_KthSmallest(nums, left_i+1, r, k);
        }
    }
    
    void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;				
    }
}
