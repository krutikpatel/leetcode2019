/*
Longest Palindromic Substring
DescriptionHintsSubmissionsDiscussSolution

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:

Input: "cbbd"
Output: "bb"
*/
class Solution {
    /*
    make a boolean dp[][]
    -and track max's begin and end, since we need to return substr, not length
    
    i-beginIndex, j-endIndex
        Start i from n
        iterate j from i till n 
    */
    public String longestPalindrome(String s) {
        if(s==null || s.length()==0)
            return s;
        
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        
        int len = 0;
        int begin=-1;
        int end = -1;
        /*
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
        for(int i = n-1;i>=0;i--){	//end
            for(int j=i;j<n;j++){	//begin
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j-i<3 || dp[i+1][j-1]);
                if(dp[i][j]){
                    //System.out.println("i = "+i + " and j = "+j);
                    
                    //check len, and update indices
                    if((j-i+1) >=len){
                        len = j-i+1;
                        begin = i;
                        end = j;                                           
                    }                        
                }                    
            }
        }
        
        return s.substring(begin,end+1);//because end is not-inclsive in java substring
    }
}

/*
non DP more space efficient solution

public class Solution {

private int lo, maxLen;

public String longestPalindrome(String s) {
	int len = s.length();
	if (len < 2)
		return s;
	
    for (int i = 0; i < len-1; i++) {
     	extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
     	extendPalindrome(s, i, i+1); //assume even length.
    }
    return s.substring(lo, lo + maxLen);
}

private void extendPalindrome(String s, int j, int k) {
	while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
		j--;
		k++;
	}
    
    //every time we see palindrome, record start position and length of palindrome, record maxLen so far
	if (maxLen < k - j - 1) {
		lo = j + 1;
		maxLen = k - j - 1;
	}
}}

*/
