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
    /*
    Brute force - multple recusrsive calls:
    very nice explanatio at:
    https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
    
    ----------------------------------------
    
    => logic : If the two ends of a string are the same, then they must be included in the longest palindrome subsequence. 
    Otherwise, both ends cannot be included in the longest palindrome subsequence.
    
    int longestPalindromeSubseq(string s) {
        return longestPalindromeSubseq(0,s.size()-1,s); 
    }
    int longestPalindromeSubseq(int l, int r, string &s) {
        if(l==r) return 1;
        if(l>r) return 0;  //happens after "aa" 
        return s[l]==s[r] ? 2 + longestPalindromeSubseq(l+1,r-1, s) : 
            max(longestPalindromeSubseq(l+1,r, s),longestPalindromeSubseq(l,r-1, s)); 
    }
    
    --
    so
    window/string is: i,j
    if(c[i] == c[j]){
        dp[i][j] = 2 + dp[i+1][j-1]
    } else {
        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
    }
    */
    public int longestPalindromeSubseq(String s) {
        if(s==null || s.length()==0)
            return 0;
        
        int len = s.length();
        //every string is represented by window i,j, and we need to store result of that, thats why 2D array.
        int[][] dp = new int[len][len];
        
        /*
        for every iteration we check substring(i,j)
        
        At end we would have filled up diagonally upper half matrix
        our window is [i..j]
        */
        for(int i = 0;i<len;i++){
            dp[i][i]=1;//single char string
        }
        
        //we begin from end side. we begin with sub-str len = 2
        for(int i = len - 2;i >= 0;i--){
            for(int j = i + 1;j < len;j++){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    //max of one char less from left, OR one char remvoed from right
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }                    
            }
        }                    
        
        //top-right corner. last vals of i,j after loops finishes
        //final window is whole string, i=0,j=n-1
        return dp[0][len-1];
    }
    
    public int longestPalindromeSubseq2(String s) {
        if(s==null || s.length()==0)
            return 0;
        
        int n = s.length();
        //every string is represented by window i,j, and we need to store result of that, thats why 2D array.
        int[][] dp = new int[n][n];
        
        /*
        for every iteration we check substring(i,j)
        
        At end we would have filled up diagonally upper half matrix
        our window is [i..j]
        */
        for(int i = n-1;i>=0;i--){
            dp[i][i] = 1; //one char, same char
            
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
        //final window is whole string, i=0,j=n-1
        return dp[0][n-1];
    }
}
