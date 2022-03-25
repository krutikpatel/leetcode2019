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
        
        int closest = nums[0] + nums[1] + nums[2]; //numsInteger.MAX_VALUE; wont work
        
        /*
        take each elem : x, and find two-sum for other two elem = target-x
        */
        for(int i = 0;i<nums.length-2;i++){                        

            //find two sum = target -x
            //standard two sum one pass sweep
            int l = i+1;
            int r = nums.length-1;
            int local_target = target - nums[i];
            
            while(l<r){
                int currSum = nums[l] + nums[r] + nums[i];//easier to call them a,b,c for simplicity
                
                //track min difference from target
                if(Math.abs(target - currSum) < Math.abs(target - closest))
                    closest = currSum;
                   
                if(nums[l] + nums[r] < local_target){                    
                    l++;
                } else if(nums[l] + nums[r] > local_target){
                    r--;
                }else                         
                    return target;                    
            }
        }
        
        return closest;
    }
    ////
    public int threesumclosest2(int[] nums, int t) {
        //int ret = Integer.MAX_VALUE;
        if(nums == null || nums.length < 3)
            return 0;

        int ret = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++) {
            int j = i+1;
            int k = nums.length-1;
            int a = nums[i];
            while(j<k) {
                int b = nums[j];
                int c = nums[k];
                int currSum = a+b+c;
                ret = Math.min(Math.abs(ret-t), Math.abs(ret-currSum));

                if(currSum > t) {
                    j--;
                } else if(currSum < t) {
                    i++;
                } else {
                    return currSum;
                }
            }
        }

        return ret;
    }
    //////////////
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
