/* medium
Search in a Sorted Array of Unknown Size

Given an integer array sorted in ascending order, write a function to search target in nums.  If target exists, then return its index, otherwise return -1. However, the array size is unknown to you. You may only access the array using an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k (0-indexed).

You may assume all integers in the array are less than 10000, and if you access the array out of bounds, ArrayReader.get will return 2147483647.

 

Example 1:

Input: array = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
*/
class Solution {
    /*
    Try to cross limit.
    -extend r by doubling it every time.
    -once crossed, l becomes last val of r.
    that l,r is your range for normal binary search :)
    */
    public int search(ArrayReader reader, int target) {
        int l = 1;
        int r = 1;
        while(reader.get(r) < target){
            r *= 2;
        }
        
        l = r/2;
        
        while(l<=r){
            int mid = l+ (r-l)/2;
            if(reader.get(mid) == target){
                return mid;
            } else if(reader.get(mid) < target){
                l = mid+1;
            } else
                r = mid -1;
        }
        
        return -1;
    }
}
