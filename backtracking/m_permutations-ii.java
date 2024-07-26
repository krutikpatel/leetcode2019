/*
Permutations II
DescriptionHintsSubmissionsDiscussSolution

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
*/
class Solution {
  //no sorting needed here
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums==null || nums.length==0) { return ans; }
        permute(ans, nums, 0);
        return ans;
    }
    
    private void permute(List<List<Integer>> ans, int[] nums, int index) {
        if (index == nums.length) { 
            List<Integer> temp = new ArrayList<>();
            for (int num: nums) { temp.add(num); }
            ans.add(temp);
            return;
        }
      
        //use set for each iteration to manage visited nodes
        Set<Integer> appeared = new HashSet<>();
        for (int i=index; i<nums.length; ++i) {
            //if not used
            if (appeared.add(nums[i])) {
                swap(nums, index, i);
                permute(ans, nums, index+1);
                swap(nums, index, i);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int save = nums[i];
        nums[i] = nums[j];
        nums[j] = save;
    }
  
    /*
    Ref:
    https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
    */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList();
        if(nums == null || nums.length == 0)
            return ret;
        
        Arrays.sort(nums); //imp for duplicates
        
        backtrackPermute(new ArrayList(), nums, new boolean[nums.length], ret);
        
        return ret;
    }
    
    private void backtrackPermute(List<Integer> soFar, int[] nums, boolean[] usedIndices, List<List<Integer>> ret){
        if(soFar.size() == nums.length){
            ret.add(new ArrayList(soFar));
            return;
        }
        
        //every time we check all 3 nums as possible chioice in the list.
        for(int i=0; i<nums.length; i++){
            
            /*
            //check if valid. Cant just check presense of num in list, because duplicates are allowed
            if(soFar.contains(nums[i]))
                continue;            
            */
            
            /*
            check whether to use this num or not. since duplicates allowed, comes down to whether index used or not
            Need to skip ith num
            a. if index i is used OR
            b.
                ith and i-1th nums same. AND i-1th index is already used
            */
            if(usedIndices[i] == true || (i>0 && nums[i] == nums[i-1] && usedIndices[i-1] == true)) //we need last condn, because otherwise we will end up avoiding ALL duplicate i and i-1 !
                
                continue;
            
            //mark used
            usedIndices[i] = true;
            
            //take one step, add next num
            soFar.add(nums[i]);
            
            //solve this further-fill rest of digits
            backtrackPermute(soFar, nums, usedIndices, ret);
            
            //mark un-used
            usedIndices[i] = false;
                        
            //backtrack - to compute all other possible answers
            soFar.remove(soFar.size()-1);
        }
    }
}
