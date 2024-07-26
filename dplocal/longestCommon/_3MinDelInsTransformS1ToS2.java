package dplocal.longestCommon;
/*
 * Problem Statement

Given strings s1 and s2, we need to transform s1 into s2 by deleting and inserting characters. 
Write a function to calculate the count of the minimum number of deletion and insertion operations.
 */
public class _3MinDelInsTransformS1ToS2 {
/*
 * Bottom-up
 * 
 * Logic:
 * This problem can easily be converted to the Longest Common Subsequence (LCS). 
 * If we can find the LCS of the two input strings, we can easily find how many characters we need to insert and delete from s1. 
 * Here is how we can do this:
 * 
 * 
1. Let’s assume len1 is the length of s1 and len2 is the length of s2.
2. Now let’s assume cc is the length of LCS of the two strings s1 and s2.
3. To transform s1 into s2, we need to delete everything from s1 which is not part of LCS, 
	so minimum deletions we need to perform from s1 => len1 - cc (remove common part)
4. Similarly, we need to insert everything in s1 which is present in s2 but not part of LCS, 
	so minimum insertions we need to perform in s1 => len2 - cc (inser un-common part)

 */
	public void findMDI(String s1, String s2) {
	    int c1 = findLCSLength(s1, s2);
	    
	    //solution:
	    System.out.println("Minimum deletions needed: " + (s1.length() - c1));
	    System.out.println("Minimum insertions needed: " + (s2.length() - c1));
	}
	
	//same LCS solution bottom-up as LCS problem
	private int findLCSLength(String s1, String s2) {
	    int[][] dp = new int[s1.length()+1][s2.length()+1];
	    int maxLength = 0;
	    
	    for(int i=1; i <= s1.length(); i++) {
		    for(int j=1; j <= s2.length(); j++) {
		    	
		    	//is chars same
		        if(s1.charAt(i-1) == s2.charAt(j-1)) {//note we r accessing 0th elem. (although we start from i,j=1)
		          dp[i][j] = 1 + dp[i-1][j-1];
		        }
		        else {
		        	//max of skipping char from each
		          dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
		        }
		        
		        maxLength = Math.max(maxLength, dp[i][j]);
		    }
	    }
	    return maxLength;
	}
	  
}
