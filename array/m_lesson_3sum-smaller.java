/* Medium
259. 3Sum Smaller
DescriptionHintsSubmissionsDiscussSolution

Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:

Input: nums = [-2,0,1,3], and target = 2
Output: 2 
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]

*/
class Solution {
    /*
    lesson
    3sum like problem.
    for each A[i], we do 2 sum sweep. 
    in 2sum sweep, we count each combination < target
    
    
    Time complexity : O(n^2). The twoSumSmaller function takes O(n) time because both left and right traverse at most n steps. (NOT log(n) )
    Therefore, the overall time complexity is O(n^2).
    
    */
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private int twoSumSmaller(int[] nums, int startIndex, int target) {
        int pairCount = 0;
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                /* trick
                // IF THREESUM < TARGET, THEN BECAUSE THEE ARRAY IS SORTED
                // ALL NUMBERS IN BETWEEN WILL ALSO BE LESS OR EQUAL TO K
                // AND THEREFORE BE VALID ANSWERS
                0, 1, 2, 3, 4 target=6
                first iteration:
                i, low, x, y, high
                pairCount = high-low =3

                the combinations are
                i,low,high(obvious) =0,1,4
                i,low,x = 0,1,2
                i,low,y = 0,1,3

                */
                pairCount += right - left;
                left++;
            } else {
                right--;
            }
        }
        return pairCount;
    }
}
