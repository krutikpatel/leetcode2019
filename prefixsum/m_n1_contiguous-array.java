/*
https://leetcode.com/problems/contiguous-array/description/
Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.

 

Example 1:

Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
Example 2:

Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
*/
class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();// map of count and index
        map.put(0, -1);//why -1 ??? -1 is prev index to 0
                        //if see 1 then count+ +1 
                        //if see 0 then count+ -1
                        //we maintain count this way because we want to know how many times same thing happened before - ie prefix knowledge
                        //instead of sumsofar, we do + and -
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }

    /*
    public int findMaxLength(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0;
        int ret = 0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            map.put(i,sum);

            //check prev subarr
            while(i>=1){
                //check map
                if(i%2 !=0){//even no of elems
                    if(map.get(i) * 2 == i+1){
                        ret++;
                    }
                }
                i--;
            }
        }
        return ret;
    }
    */
}
