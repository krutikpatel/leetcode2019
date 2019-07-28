/*
Perfect Squares

Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.

Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
*/
class Solution {
    public int numSquares(int n) {
        if(n==0 || n==1)
            return n;
        
        int[] memo = new int[n+1];

        /*
        memo[n] = min(1 + memo[n-eachSmallerPerfectSquares])
        Try to fill whole memo array
        */
        for(int i=1;i<=n;i++){
            memo[i] = Integer.MAX_VALUE;    //because it was unitialzed arr, and we want min
            /*
            -figure out memo[i]
            for that we need to go thru all perfect squares - smaller than i
            and get min
            */            
            for(int j =1;j*j<=i;j++){
                int diff = i - (j*j);   // this is like n-1,n-4,n-9 ..
                memo[i] = Math.min(memo[i], 1+ memo[diff]); //that 1 is for j*j which helped reach i
            }
        }
        
        return memo[n];
    }
}
