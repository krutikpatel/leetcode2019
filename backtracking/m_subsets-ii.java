/*
Subsets II
DescriptionHintsSubmissionsDiscussSolution

Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ret = new ArrayList();
        if(nums == null || nums.length == 0)
            return ret;
        
        Arrays.sort(nums);
        ret.add(new ArrayList()); //empty list is also valid subset
        helper(0, new ArrayList(), nums, ret);
        
        return ret;
    }
    
    private void helper(int pickPos, List<Integer> soFar, int[] nums, List<List<Integer>> ret){
        if(soFar.size() > 0){
            ret.add(new ArrayList(soFar));            
        } // we dont return because we want to build up as well
        
        // using pickPos to mark available choices of nums
        // Take curr num and exhaust it. so we dont have to worry about order. after 2,3 we wud never have 3, 2
        for(int i=pickPos; i<nums.length; i++){            
            
            //skipused indices - easy to visualize at first digit selection
            if(i>pickPos && nums[i] == nums[i-1])
                continue;
            
            //take one step, add next num
            soFar.add(nums[i]);
            
            //solve this further-fill rest of digits
            helper(i+1, soFar, nums, ret);
            
            //backtrack - to compute all other possible answers
            soFar.remove(soFar.size()-1);
        }
    }
}
