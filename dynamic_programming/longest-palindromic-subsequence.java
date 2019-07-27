/*
Longest Palindromic Subsequence

Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"

Output:

4

One possible longest palindromic subsequence is "bbbb".

Example 2:
Input:

"cbbd"

Output:

2
*/
class Solution {
    public int longestPalindromeSubseq(String s) {
        if(s==null || s.length()==0)
            return 0;
        
        int n = s.length();
        int[][] dp = new int[n][n];
        
        /*
        for every iteration we check substring(i,j)
        
        At end we would have filled up diagonally upper half matrix
        
        We try to fill-up max-len in dp matrix 
        */
        for(int i = n-1;i>=0;i--){
            dp[i][i] = 1; //one char, same char , str-len = 1
            
            for(int j=i+1;j<n;j++){ //Remember: we begin j=i+1 and NOT i, because dp[i+1] will break otherwise
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2; //prev dp + 2 chars of curr
                } else{
                    //find smaller dp max
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);    //max of dp(removed-left-char) , dp(removed-right-char)
                } 
            }
        }
        
        //top-right corner. last vals of i,j after loops finishes
        return dp[0][n-1];
    }
}
