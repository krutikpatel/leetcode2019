/* Medium
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
        
    -mark num/bucket -ve so that next time if we see -ve, we know its been touched before and thus appeared twice   
    */
    public List<Integer> findDuplicates(int[] nums) {
        /*
        -use val as index, and turn that index into -ve
        */
        List<Integer> ret = new ArrayList<>();
        for(int i=0;i<nums.length;i++) {
            int index = Math.abs(Math.abs(nums[i]) -1);// -1 because num 8 in array size 8 cant go in index8, but at index 7
            
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
/*
Related problem:
https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        
        //use bucket val as arr index
        for(int i=0;i<nums.length;i++) {
            int index = Math.abs(nums[i]) - 1;
            
            //do it only if its not done before by aany duplicate elem
            if(nums[index] > 0)
                nums[index] = -nums[index];
        }
        
        for(int i=0;i<nums.length;i++) {
            if(nums[i] > 0)
                ret.add(i+1);
        }
        
        return ret;
    }
*/
