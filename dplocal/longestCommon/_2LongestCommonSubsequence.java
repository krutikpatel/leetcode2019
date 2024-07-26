package dplocal.longestCommon;

public class _2LongestCommonSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
/*
 * Brute force
 * 
 * A basic brute-force solution could be to try all subsequences of �s1� and �s2� to find the longest one. 
 * We can match both the strings one character at a time. So for every index �i� in �s1� and �j� in �s2� we must choose between:

1. If the character s1[i] matches s2[j], we can recursively match for the remaining lengths.
2. If the character s1[i] does not match s2[j], we will start two new recursive calls by skipping one character separately 
	from each string.

we do not maintain currCount - because we need to take each call till end
 */
	public int findLCSLength(String s1, String s2) {
	      return findLCSLengthRecursive(s1, s2, 0, 0);
	}

	private int findLCSLengthRecursive(String s1, String s2, int i1, int i2) {
		//no more common characters possible because string ended
	    if(i1 == s1.length() || i2 == s2.length())
	      return 0;
	
	    //case 1
	    if(s1.charAt(i1) == s2.charAt(i2))
	      return 1 + findLCSLengthRecursive(s1, s2, i1+1, i2+1);
	
	    //case 2 - proceed 1 at a time from both
	    int c1 = findLCSLengthRecursive(s1, s2, i1, i2+1);
	    int c2 = findLCSLengthRecursive(s1, s2, i1+1, i2);
	
	    return Math.max(c1, c2);
	}
	
	/*
	 * top -down recursive with memo
	 */
	private int findLCSLengthRecursive2(String s1, String s2, int i1, int i2, Integer[][] dp) {
	    //no more common because string ended
		if(i1 == s1.length() || i2 == s2.length())
	      return 0;
	
		if(dp[i1][i2] == null) {
		    //case 1
		    if(s1.charAt(i1) == s2.charAt(i2))
		    	dp[i1][i2] = 1 + findLCSLengthRecursive(s1, s2, i1+1, i2+1);
		    else {
			    //case 2 - proceed 1 at a time from both
			    int c1 = findLCSLengthRecursive(s1, s2, i1, i2+1);
			    int c2 = findLCSLengthRecursive(s1, s2, i1+1, i2);
			    
			    dp[i1][i2] = Math.max(c1, c2);
		    }
		}
		return dp[i1][i2];
	}
	
	/*
	 * bottom -up iterative
	 * 
	 *
	 *  base case value is 0
	 *  
  if s1[i] == s2[j] 
    dp[i][j] = 1 + dp[i-1][j-1]
  else 
    dp[i][j] = max(dp[i-1][j], dp[i][j-1])

	 */
	public int longestCommonSubsequence(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        int maxLength = 0;
        for(int i=1; i <= s1.length(); i++) {
        for(int j=1; j <= s2.length(); j++) {
            if(s1.charAt(i-1) == s2.charAt(j-1))
            dp[i][j] = 1 + dp[i-1][j-1];
            else
            dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            
            maxLength = Math.max(maxLength, dp[i][j]);
        }
        }
        return maxLength;
    }
	
}
