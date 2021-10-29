/* Medium
560. Subarray Sum Equals K
DescriptionHintsSubmissionsDiscussSolution

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:

Input:nums = [1,1,1], k = 2
Output: 2

Note:

    The length of the array is in range [1, 20,000].
    The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

*/
class Solution {
    /*
Solution 1. Brute force. We just need two loops (i, j) and test if SUM[i, j] = k. Time complexity O(n^2), Space complexity O(1). 
    I bet this solution will TLE.

Solution 2. From solution 1, we know the key to solve this problem is SUM[i, j]. 
    So if we know SUM[0, i - 1] and SUM[0, j], then we can easily get SUM[i, j]. 
    To achieve this, we just need to go through the array, calculate the current sum and save number of all seen PreSum to a HashMap. 
    Time complexity O(n), Space complexity O(n).

Explanation: 
Sliding window cannot be used, because array contains negative number
-Its prefix sum problem.
-HashMap can store sum till index i
-But we take that concept one level up, since we are asked to count HOW many times we see the sum, and store the <sumseen_sofar, count>

    */
    public int subarraySum(int[] nums, int k) {
        
        int ret = 0;
        //<sum till i , how many times sum_i occurred> --> this will be used to know how many times we can sum "k"
        HashMap<Integer,Integer> sumCountmap = new HashMap<>();
        
        //imp to count sum including before 0th elem
        sumCountmap.put(0,1);
        
        int sum = 0;
        for(int i=0;i<nums.length;i++) {
            sum += nums[i];
            
            //check if sum - k exists in map
            if(sumCountmap.containsKey(sum-k)) {    //this means, some [j,i] window has sum = k. i is current index
                ret += sumCountmap.get(sum-k);                
            }
            sumCountmap.put(sum, sumCountmap.getOrDefault(sum,0)+1);
        }
        
        return ret;
    }
}
