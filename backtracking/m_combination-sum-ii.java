/*
Combination Sum II
DescriptionHintsSubmissionsDiscussSolution

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

*/
class Solution {
    /*
    Array can have duplicates
    each index can not be used again
    */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList();
        if(candidates == null || candidates.length == 0 || target == 0)
            return ret;
        
        Arrays.sort(candidates);
        helper(0, new ArrayList(), candidates, target, ret, 0);
        return ret;
    }
        
    private void helper(int pickPos, List<Integer> soFar, int[] nums, int target, List<List<Integer>> ret, int sumSoFar){

        if(sumSoFar == target){
            ret.add(new ArrayList(soFar));//imp to add copy, because this list will be modified for next backtracking
            return;
        } else if(sumSoFar > target){
            return; //so that next statement can backtrack from here
        }
        
        // using pickPos to mark available choices of nums
        // Take curr num and exhaust it. so we dont have to worry about order. after 2,2,3 we wud never have 3, 2, 2
        for(int i=pickPos; i<nums.length; i++){            
            
            // skip duplicates
            if(i > pickPos && nums[i] == nums[i-1]) 
                continue; 
            
            //take one step, add next num
            soFar.add(nums[i]);
            
            //solve this further-fill rest of digits
            helper(i+1, soFar, nums, target, ret, sumSoFar+nums[i]); //i+1 becasue we cant reuse same index again
            
            //backtrack - to compute all other possible answers
            soFar.remove(soFar.size()-1);
        }
    }

}
