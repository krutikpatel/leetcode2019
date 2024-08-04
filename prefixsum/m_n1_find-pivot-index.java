/*
https://leetcode.com/problems/find-pivot-index/?envType=study-plan-v2&envId=leetcode-75

Given an array of integers nums, calculate the pivot index of this array.

The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.

If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.

Return the leftmost pivot index. If no such index exists, return -1.

 

Example 1:

Input: nums = [1,7,3,6,5,6]
Output: 3
Explanation:
The pivot index is 3.
Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
Right sum = nums[4] + nums[5] = 5 + 6 = 11
Example 2:

Input: nums = [1,2,3]
Output: -1
Explanation:
There is no index that satisfies the conditions in the problem statement.
*/
class Solution {
/*
    public int pivotIndex(int[] nums) {
        int[] sumFromLeft = new int[nums.length];
        int[] sumFromRight = new int[nums.length];
        
        int sum=0;
        for(int i=0;i<nums.length;i++) {
            sumFromLeft[i] = sum;
            sum+=nums[i];
        }

        sum=0;
        for(int i=nums.length-1;i>=0;i--) {
            sumFromRight[i] = sum;
            sum+=nums[i];
        }

        //find pivot
        for(int i=0;i<nums.length;i++) {
            if(sumFromLeft[i] == sumFromRight[i]) {
                return i;
            }
        }
        return -1;
    }
*/
    
        //prefix sum without creating prefixsum array
        public int pivotIndex(int[] nums) {
            int sum = 0, leftsum = 0;
            for (int x: nums) sum += x;
            for (int i = 0; i < nums.length; ++i) {
                //at index i, we compare sum on left and sum on right
                int sumRight = sum - leftsum - nums[i];
                if (leftsum == sumRight) {
                    return i;
                }

                leftsum += nums[i];
            }
            return -1;
        }
    
    
}
