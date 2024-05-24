/*
Given an array nums of unique integers, return all possible subsets of nums.

The solution set must not contain duplicate subsets. You may return the solution in any order.

Example 1:

Input: nums = [1,2,3]

Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:

Input: nums = [7]

Output: [[],[7]]

*/

class Solution {
    List<List<Integer>> ret;
    public List<List<Integer>> subsets(int[] nums) {
        ret = new ArrayList<>();
        helper(new ArrayList<>(), nums, 0);
        return ret;
    }

    private void helper(List<Integer> sofar, int[] nums, int begin) {
        ret.add(new ArrayList<>(sofar));

        for(int i=begin;i<nums.length;i++) {
            sofar.add(nums[i]);
            helper(sofar, nums, i+1);
            sofar.remove(sofar.size()-1);
        }
    }
}
