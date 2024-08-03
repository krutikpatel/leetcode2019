/*
https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/description/

You have n dice, and each dice has k faces numbered from 1 to k.

Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice, so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.

 

Example 1:

Input: n = 1, k = 6, target = 3
Output: 1
Explanation: You throw one die with 6 faces.
There is only one way to get a sum of 3.
Example 2:

Input: n = 2, k = 6, target = 7
Output: 6
Explanation: You throw two dice, each with 6 faces.
There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.

*/
class Solution {
    /*
    -n dice
        -k faces (1 to k)
        
    -target sum
    -each die must be used
    
    ref: https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/discuss/770166/Evolve-from-brute-force-to-dp
    */
    private static int MOD = 1000000007;
    
    public int numRollsToTarget(int n, int k, int target) {
        Integer[][] dp = new Integer[n+1][target+1];//since 2nd dimension max is target: instead of sumsofar, we need to go from target to 0
        int count  = countHelper(n,k,target,n,target,dp);
        //System.out.println(count);
        //return (int)(count%MOD);    
        return count;
    }

        //dp state vars: diceIndex, sumSoFar NOT k, k is CONSTANT ! see which args are changing, those are states
    private int countHelper(int n, int k, int target, int diceIndex, int raminingTarget, Integer[][]dp){
        if(diceIndex==0||raminingTarget<0) {
            return raminingTarget==0?1:0;
        }
        
        if(dp[diceIndex][raminingTarget] != null){
            return dp[diceIndex][raminingTarget];
        }
        
        int ret = 0;
        //try k faces of curr die
        for(int i=1;i<=k;i++){
            //int x = countHelper(n,k,target,diceIndex-1,raminingTarget-i,dp)%MOD;//have to do it everytime, can just do it at end
            //ret+= x;
            
            //imp inline calculation, above method did not work!
            ret=(ret+countHelper(n,k,target,diceIndex-1,raminingTarget-i,dp))%MOD;//have to do it everytime, can just do it at end 
        }
        dp[diceIndex][raminingTarget] = ret;
        return dp[diceIndex][raminingTarget];
    }
/*    
    //variables: k,index
    private long countHelper(int n, int k, int target, int diceIndex, int sumSoFar, Long[][]dp){
        if(diceIndex > n){
            if(sumSoFar == target){
                //count++;
                return 1;
            }
            return 0;
        }
        //
        //if(sumSoFar>target){
        //    return 0;
        //}
        //
        if(dp[diceIndex][k] != null){
            return dp[diceIndex][k];
        }
        
        long ret = 0;
        //try k faces of curr die
        for(int i=1;i<=k;i++){
            ret+=countHelper(n,k,target,diceIndex+1,sumSoFar+i,dp);
        }
        dp[diceIndex][k] = ret;
        return dp[diceIndex][k];
    }
*/
    
    /* brute force
    private void countHelper(int n, int k, int target, int diceIndex, int sumSoFar){
        if(diceIndex > n){
            if(sumSoFar == target){
                count++;
            }
            return;
        }
        if(sumSoFar>target){
            return;
        }
        
        //try k faces of curr die
        for(int i=1;i<=k;i++){
            countHelper(n,k,target,diceIndex+1,sumSoFar+i);
        }
    }
    */
}
