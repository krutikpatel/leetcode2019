/*
Palindromic Substrings
Given a string, your task is to count how many palindromic substrings in this string.
The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
*/
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        int count = 0;
        
        if(s==null || n==0){
            return count;
        }
        
        for(int i=0;i<n;i++) {
            count += checkPal(s, i,i); //form palindrom string from single center char (odd length string)
            count += checkPal(s, i,i+1);//form palindrom string from two central chars (even length string)
        }
        return count;
    }
    
    /*
    i goes left
    j goes right
    till char matches
    */
    private int checkPal(String s, int i, int j) {
        int count = 0;
        while(i>=0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            count++;
            i--;
            j++;
        }
        return count;
    }
    
    /*
    slower than above
    */
    public int countSubstringsDP(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        
        int count = 0;
        /*
        DP/memoize top-to-bottom (i = n to 0)
        Because of nature of problem - "substring" for palindrome
            if you want to know if substring s[0-5] (i.e. dp[0][5]) is palindrome, 
            you'd look up the dp table for dp[1][4], however, if "i" goes from left to right, 
            dp[1] has not been calculated yet because it'll dp[1] comes after dp[0]
            
        i-beginIndex, j-endIndex
        Start i from n
        iterate j from i till n
        
        -think/check substring i,j every-time
            str(j,i) = palndrome if
                -begin and end char same AND 
                    -length is <= 3 (makes sense visually)
                    OR - prev dp = true (because then we are adding same char on both sides of valid palindrome)
                        -repv dp is substring of (i,j) which is (i+1,j-1)
        */
        for(int i = n-1;i>=0;i--){
            for(int j=i;j<n;j++){
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j-i<3 || dp[i+1][j-1]);
                if(dp[i][j])
                    count++;
            }
        }
        
        return count;
    }
}
