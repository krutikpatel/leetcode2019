/* Medium
3Sum Closest

Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length < 3)
            return 0;
        
        //Imp operation for int array
        Arrays.sort(nums);
        
        int closest = nums[0] + nums[1] + nums[2]; //numsInteger.MAX_VALUE; wont work   //to track min/closest
        
        /*
        take each elem : x, and find sum2 for other two elem = target-x
        */
        for(int i = 0;i<nums.length-2;i++){                        

            //find two sum = target -x
            //standard two sum one pass sweep
            int j = i+1;
            int k = nums.length-1;
            int local_target = target - nums[i];
            
            while(j<k){
                int sum = nums[j] + nums[k] + nums[i];
                
                //track min difference from target
                if(Math.abs(target - sum) < Math.abs(target - closest))
                    closest = sum;
                   
                if(nums[j] + nums[k] < local_target){  // in simple words:  if(nums[i] + nums[j] + nums[k] < target)                 
                    j++;
                } else if(nums[j] + nums[k] > local_target){ // in simple words: if(nums[i] + nums[j] + nums[k] > target)
                    k--;
                }else                         
                    return target;                    
            }
        }
        
        return closest;
    }
}
