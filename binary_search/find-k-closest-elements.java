/*
Find K Closest Elements

Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:

Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]

Example 2:

Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]

Note:
    The value k is positive and will always be smaller than the length of the sorted array.
    Length of the given array is positive and will not exceed 104
    Absolute value of elements in the array and x will not exceed 104
*/
class Solution {
    /*
    Modified Template III
    -here we access : mid+k
    maintain window of k elems
    -based on whether mid is closer to x or mid+k, move the mid (ie whole window)
    
    
    -uses modified version of template 3.
    -aim to find beginning of that k elems window
        -we either want elem from left side of x, or right side of x (whichever is closer)
    
    -now, check
    -if mid is closer to x or mid+k is closer ?
    -if mid+k close, we need to move right, remove mid
    -else, keep mid, go left

    At end, left val is beginning of k-elem window
    */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0;
        int r = arr.length - k;
        
        while(l<r){
            int mid = l+(r-l)/2;
            
            if(Math.abs(arr[mid]-x) > Math.abs(arr[mid+k]-x)){
                //mid+1 is closer so move right
                l = mid+1;
            } else {
                //left
                r = mid;
            }
        }
        
        System.out.println(l);
        //take k elems beginning from l
        List<Integer> res = new ArrayList(k);
        for (int i = l; i < l+k; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}
