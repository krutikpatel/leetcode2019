/*
4Sum

Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/
class Solution {    
    /*
    Idea: we know 2sum solution using 2 pointers (left and right)
    So,
    
    1. 2sum Problem
    2. Reduce K sum problem to K – 1 sum Problem

    For each elem [i], solve k-1 problem recursively, at k=2, solve 2sum problem
    
    Time complexity is O(N^(K-1)).
    */
    public List<List<Integer>> fourSum(int[] nums, int target) {
              
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);        
    }
       
    /*
    Generic method
    */  
    private List<List<Integer>> kSum(int[] nums, int target, int k, int left) {
        int len = nums.length;
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        
        if(left >= len) {
            return ret;
        }
        
        // well know two pointer technique for 2sum
        if(k == 2) {
            int i = left, j = len - 1;
            while(i < j) {
                //find a pair
                if(target - nums[i] == nums[j]) {
                    List<Integer> temp = new ArrayList<>();
                    
                    //add prev 2 elems to make 4 pair
                    temp.add(nums[i]);
                    temp.add(target-nums[i]);
                    
                    ret.add(temp);
                    //skip duplication
                    while(i<j && nums[i]==nums[i+1]) i++;
                    while(i<j && nums[j-1]==nums[j]) j--;
                    
                    i++;
                    j--;
                //move left bound
                } else if (target - nums[i] > nums[j]) {
                    i++;
                //move right bound
                } else {
                    j--;
                }
            }
        }
        //else : just reduce k-sum problem to k-1 sum till it becomes 2sum problem //
        else{
            for (int i = left; i < len - (k - 1); i++) {
                //use current number to reduce ksum into k-1sum
                List<List<Integer>> curr = kSum(nums, target - nums[i], k-1, i+1);
                
                //add ith elem to k-1 solution
                for (List<Integer> t : curr) {
                    t.add(0, nums[i]);
                }
                //add found lists to result
                ret.addAll(curr);
                
                //skip duplicated numbers
                while (i < len-1 && nums[i] == nums[i+1]) {                    
                    i++;
                }
            }
        }
        
        return ret;
    }
}
