/* easy
Intersection of Two Arrays

Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]

Note:

    Each element in the result must be unique.
    The result can be in any order.
*/
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0)
            return new int[0];
        
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> ret = new HashSet<>();
        
        for(int i : nums1){
            set.add(i);
        }
        
        for(int i : nums2){
            if(set.contains(i))
                ret.add(i);
        }
        
        int[] retarr = new int[ret.size()];
        int idx = 0;
        for(Integer i : ret){
            retarr[idx] = i;
            idx++;
        }
            
        return retarr;
    }
}
