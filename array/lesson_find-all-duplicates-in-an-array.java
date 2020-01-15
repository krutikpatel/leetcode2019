/*
442. Find All Duplicates in an Array
DescriptionHintsSubmissionsDiscussSolution

Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]

*/
class Solution {
    /*
    Key to optimal soultion is:
    -using elem value as index in array ie, use bucket val as arr index
    -And that only works if range of nums in array is "1 to n" (length of array). And that is given here.
        -that itself gives hint to the solution :)
    */
    public List<Integer> findDuplicates(int[] nums) {
        /*
        -use val as index, and turn that index into -ve
        */
        List<Integer> ret = new ArrayList<>();
        for(int i=0;i<nums.length;i++) {
            int index = Math.abs(Math.abs(nums[i]) -1);
            
            if(nums[index] < 0) {
                //we have already touched this num, so it is duplicate
                ret.add(Math.abs(nums[i]));
            } else {
                nums[index] = -nums[index];
            }
        }
        
        return ret;
    }
}
