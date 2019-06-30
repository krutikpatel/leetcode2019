/*
 3Sum

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if(nums == null || nums.length ==0)
            return ret;
        
        //Imp operation for int array
        Arrays.sort(nums);
        /*
        take each elem : x, and find sum-2 for other two elem = -x
        two pointer technique
        */
        for(int i = 0;i<nums.length-2;i++){            
            if(i > 0 && nums[i] == nums[i-1])
                continue;                   // skip same result. we dont want duplicate sum pair
            
            int x = nums[i];
                        
            //find two sum = -x. 
            //standard two sum one pass sweep
            int j = i+1;
            int k = nums.length-1;
            while(j<k){
                if(nums[j] + nums[k] < -x){
                    j++;
                } else if(nums[j] + nums[k] > -x){
                    k--;
                }else{                           
                    ret.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                    
                    //trick : if you skip same j and k's we will avoid duplicates
                    while (j < k && nums[j] == nums[j - 1]) j++;  // skip same result
                    while (j < k && nums[k] == nums[k + 1]) k--;  // skip same result
                }
            }
        }
        
        return ret;
    }
}
