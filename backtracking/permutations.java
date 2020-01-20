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
    /*
    I think the time complexity is O(n x n!) instead of O(n!), since you will have n! permutation. And, for each permutation, you run exact n recursive call to reach it
    */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList();
        if(nums == null || nums.length == 0)
            return ret;
        
        //backtrackPermute(new ArrayList(), nums, ret);
        backtrackPermuteSwap(0,nums,ret);
        
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
    
    //to avoid looking for elem in soFar list which is O(n) call, use swap method.
    //basically, for each call, what we want to do is, find all permutations beginnign with that elem at front. So just bring that elem to front by swapping, then put it back by swapping again
    
    private void backtrackPermuteSwap(int beginIndex, int[] nums, List<List<Integer>> ret){
        if(beginIndex == nums.length){
            List<Integer> ret1 = new ArrayList<>();
            for(int i: nums)
                ret1.add(i);
            ret.add(ret1);
            return;
        }
               
        //every time we check all 3 nums as possible chioice in the list.
        for(int swapIndex=beginIndex; swapIndex<nums.length; swapIndex++){
            
            swap(beginIndex,swapIndex, nums);
            
            //solve this further-fill rest of digits
            backtrackPermuteSwap(beginIndex+1, nums, ret);
            
            //backtrack - to compute all other possible answers
            swap(swapIndex,beginIndex, nums);
        }
    }
    
    private void swap(int i, int j, int[] A) {
        int temp =  A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
