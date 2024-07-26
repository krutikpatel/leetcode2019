package dplocal.longestCommon;

public class _1LongestCommonSubstring {

	/*
	 * Bruteforce
	 * A basic brute-force solution could be to try all substrings of ‘s1’ and ‘s2’ to find the longest common one. 
	 * We can start matching both the strings one character at a time, so we have two options at any step:

		1. If the strings have a matching character, we can recursively match for the remaining lengths and 
			keep a track of the current matching length.
		2. If the strings don’t match, 
			we start two new recursive calls by skipping one character separately from each string and reset the matching length.
		
		The length of the Longest Common Substring (LCS) will be the maximum number returned by the three recurse calls 
		in the above two options.

	 */
	public int findLCSLength(String s1, String s2) {
	      return findLCSLengthRecursive(s1, s2, 0, 0, 0);
	}
	
	private int findLCSLengthRecursive(String s1, String s2, int i1, int i2, int count) {
		if(i1 == s1.length() || i2 == s2.length())
		      return count;
		
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		//case 1
		if(s1.charAt(i1) == s2.charAt(i2)) {
			//recursive
			count1 = findLCSLengthRecursive(s1,s2, i1+1, i2+1, count+1);
		} else {
			//case2,3
			count2 = findLCSLengthRecursive(s1,s2, i1+1, i2, 0);
			count3 = findLCSLengthRecursive(s1,s2, i1, i2+1, 0);
		}
		
		return Math.max(count1,  Math.max(count2, count3));
	}
	
	///
	/*
	 * top -down recursive with memo
	 */
	private int findLCSLengthRecursive2(String s1, String s2, int i1, int i2, int count, Integer[][] dp) {
		if(i1 == s1.length() || i2 == s2.length())
		      return count;
		
		if(dp[i1][i2] != null)
			return dp[i1][i2];
		
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		//case 1
		if(s1.charAt(i1) == s2.charAt(i2)) {
			//recursive
			count1 = findLCSLengthRecursive(s1,s2, i1+1, i2+1, count+1);
		} else {
			//case2,3
			count2 = findLCSLengthRecursive(s1,s2, i1+1, i2, 0);
			count3 = findLCSLengthRecursive(s1,s2, i1, i2+1, 0);
		}
		
		dp[i1][i2] = Math.max(count1,  Math.max(count2, count3));
		return dp[i1][i2]; 
	}
	
	/*
	 * Bottom -up interative
	 * 
	 * here we start from index0 unlike palindrome problems
	 * 
	 * basecase: empty string, or either is empty string. dp[0][0] = 0
	 */
	public int findLCSLength3(String s1, String s2) {
	    int[][] dp = new int[s1.length()+1][s2.length()+1];
	    int maxLength = 0;
	    for(int i=1; i <= s1.length(); i++) {
	      for(int j=1; j <= s2.length(); j++) {
	        if(s1.charAt(i-1) == s2.charAt(j-1)) {
	          dp[i][j] = 1 + dp[i-1][j-1];
	          maxLength = Math.max(maxLength, dp[i][j]);
	        }
	      }
	    }
	    return maxLength;
	}
}
