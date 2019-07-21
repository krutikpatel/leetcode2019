/*
Median of Two Sorted Arrays

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0

Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
*/
class Solution {
    
    //solution followed: 
    //https://github.com/mission-peace/interview/blob/master/src/com/interview/binarysearch/MedianOfTwoSortedArrayOfDifferentLength.java
    
    /**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * Solution
 * Take minimum size of two array. Possible number of partitions are from 0 to m in m size array.
 * Try every cut in binary search way. When you cut first array at i then you cut second array at (m + n + 1)/2 - i
 * Now try to find the i where a[i-1] <= b[j] and b[j-1] <= a[i]. So this i is partition around which lies the median.
 *
 * Time complexity is O(log(min(x,y))
 * Space complexity is O(1)
 *
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * https://discuss.leetcode.com/topic/4996/share-my-o-log-min-m-n-solution-with-explanation/4
 
   Solution in my words:
   -------------------------
   -we ahve two sorted arrays of length x and y.
   -Brute force way to find median is, combine both arrays, sort them, and take middle/median.
   -To avoid that, lets look at imp prop of median:
        -its middle point of sorted array
        -length of elems is same on its both sides
        -all elems on left are smaller and all elems on right are bigger.
        
   -Now, our goal is to find such point wihtout combining these arrays and then sorting it.
   -To do that:
        -we need point on both arrays that gives same count of elems on left and right sides 
            -(combined of lefts of x and y and , combined of rights of x and y)
            -take one array and look for such suitable pointin other 
                -(min length would be better to look in , because we are aiming to find middle point of combination of two arrays, so rather look at smaller len array for this point)
        -Then, at such point see if it makes median, which is:
            -If combined left is smaller than combined right
                -last of left of x < first of right of y AND
                -last of left of y < first of right of x
            -That makes combined left < combined right. Thus right point as median
        
   
 */
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //we want nums1 as smaller array where we will do binary search
        if(nums1.length > nums2.length)
            return findMedianSortedArrays(nums2,nums1);
        
        int x = nums1.length;
        int y = nums2.length;
        
        int low = 0;
        int high = x;
        
        while(low<=high){
            //find partition points in bith
            
            //partition points/indices that makes count of combined left same as count of combined right
            // ix + iy = (x+y+1)/2
            int ix = (low+high)/2;
            //adjust point in y accordingly of ix
            int iy = (x+y+1)/2 - ix;    //REMEMBER the magic of adding 1, to make this formula work for both even and odd sum-lengths
                    
            /*
            now check if this is the correct point.
                -4 points of interest are:
                    ix,ix-1     REMEMBER -1 NOT +1
                    iy,iy-1
            */
            
            //get 4 elems around partition/cut
            //for min: check lower bound
            //for max: check upper bound
            int maxLeftX = (ix == 0) ? Integer.MIN_VALUE : nums1[ix-1]; 
            int minRightX = (ix == x) ? Integer.MAX_VALUE : nums1[ix];
            int maxLeftY = (iy == 0) ? Integer.MIN_VALUE : nums2[iy-1];
            int minRightY = (iy == y) ? Integer.MAX_VALUE : nums2[iy];
            
            if(maxLeftX <= minRightY && minRightX >=maxLeftY){
                //we found correct partition point
                if ((x + y) % 2 == 0) { //if even
                    return ((double)Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2;
                } else {
                    return (double)Math.max(maxLeftX, maxLeftY);
                }
            } 
            //REMEMBER : binary moves not inclusive of ix. - Template I of binary search
            else if(maxLeftX > minRightY){
                //too many elems in Y right, so move x left, which will move y right
                high = ix - 1;
                
            } else {
                //go right in x and left in y
                low = ix + 1;
            }
        }
        
        return -1;
    }
}
