/*
Permutations

Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/
/*
constraint is num cannot be repeated. so check if soFar already has digit, use only if its not present sofar
For each index/slot, use all digits you can..

Time complexity:
Permute function will be called n! times. 
   -that function does O(n) work i think,
   so, total: O(n*n!)
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList();
        if(nums == null || nums.length == 0)
            return ret;
        
        backtrackPermute(new ArrayList(), nums, ret);
        
        return ret;
    }
    
    private void backtrackPermute(List<Integer> soFar, int[] nums, List<List<Integer>> ret){
        if(soFar.size() == nums.length){
            ret.add(new ArrayList(soFar));
            return;
        }
        
        //every time we check all 3 nums as possible chioice in the list.
        for(int i=0; i<nums.length; i++){
            //check if valid
            if(soFar.contains(nums[i]))
                continue;
            //take one step, add next num
            soFar.add(nums[i]);
            
            //solve this further-fill rest of digits
            backtrackPermute(soFar, nums, ret);
            
            //backtrack - to compute all other possible answers
            soFar.remove(soFar.size()-1);
        }
    }
}
