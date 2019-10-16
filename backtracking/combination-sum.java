/*
Combination Sum

Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
*/
class Solution {

    /*
    IMP - for this problem : number can be re-used.
    That is why we did not increase "i" in our recursive call
    */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList();
        if(candidates == null || candidates.length == 0 || target == 0)
            return ret;
        
        helper(0, new ArrayList(), candidates, target, ret, 0);
        return ret;
    }
    
    /*
    Our choice: pickPos or index for number
    Constraint: can be done before adding or afrer adding :
        whether sum > target or not
    */
    private void helper(int pickPos, List<Integer> soFar, int[] nums, int target, List<List<Integer>> ret, int sumSoFar){

        if(sumSoFar == target){
            ret.add(new ArrayList(soFar));
            return;
        } else if(sumSoFar > target){
            return; //so that next statement can backtrack from here
        }
        
        // using pickPos to mark available choices of nums
        // Take curr num and exhaust it. so we dont have to worry about order. after 2,2,3 we wud never have 3, 2, 2
        for(int i=pickPos; i<nums.length; i++){            
            //take one step, add next num
            soFar.add(nums[i]);
            
            //solve this further-fill rest of digits
            helper(i, soFar, nums, target, ret, sumSoFar+nums[i]); //reuse i, bevause nums can be reused. 
            
            //backtrack - to compute all other possible answers
            soFar.remove(soFar.size()-1);
        }
    }
}
