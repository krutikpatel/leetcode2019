/* Easy
268. Missing Number
DescriptionHintsSubmissionsDiscussSolution

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2

Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8

*/
class Solution {
    /*
    public int missingNumber(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) numSet.add(num);

        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; number++) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }
    */
    /*
    3 ways to do it:
    https://leetcode.com/problems/missing-number/discuss/69786/3-different-ideas:-XOR-SUM-Binary-Search.-Java-code
    
    Trick:
    use array itself to store info. we know possible nums are 0-n.
    
    Corners:
    -missing n
    -0 on non zero place.
    */
    public int missingNumber(int[] nums) { //sum
        int len = nums.length;
        int sum = (0+len)*(len+1)/2;
        for(int i=0; i<len; i++)
            sum-=nums[i];
        return sum;
    }
    
    /*
    public int missingNumber(int[] nums) { //xor
        int res = nums.length;
        for(int i=0; i<nums.length; i++){
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }

    public int missingNumber(int[] nums) { //binary search
        Arrays.sort(nums);
        int left = 0, right = nums.length, mid= (left + right)/2;
        while(left<right){
            mid = (left + right)/2;
            if(nums[mid]>mid) right = mid;
            else left = mid+1;
        }
        return left;
    }
    */
    
    /* 2 O(n) - did not work yet
    public int missingNumber(int[] nums) {
        //mark index-th num as -ve
        //remainig slot is our num
        for(int i=0;i<nums.length;i++) {
            int index = Math.abs(nums[i]);
            if(index >= nums.length)
                continue;
            nums[index] = -nums[index];            
        }
        
        for(int i=0;i<nums.length;i++) {
            
            if(nums[i] >0)
                return i;
            if(nums[i] ==0  && i!=0)
                return i;
                        
        }
        return nums.length;
    }
    */
}
