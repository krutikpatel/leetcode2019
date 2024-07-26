package dplocal.palindromic;
/*
 * Given a string, find the total number of palindromic substrings in it. 
 * Please note we need to find the total number of substrings and not subsequences.

Example 1:

Input: "abdbca"
Output: 7
Explanation: Here are the palindromic substrings, "a", "b", "d", "b", "c", "a", "bdb".
 */
public class _3CountofPalindromicSubstrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

/*
 * Note:
 * recursive DP method was helpful finding longest result, because we could add vals to return result.
 * 
 * -iterative DP uses bool dp[][], indicating if smller part if palindrome or not. 
 * so here that is more useful to count no of pal substrings
 * 
 *  
 *  
 *  The time and space complexity of the above algorithm is O(n^2), where ‘n’ is the length of the input string.	
 */
	public int findCPS(String st) {
	    // dp[i][j] will be 'true' if the string from index 'i' to index 'j' is a
	    // palindrome
	    boolean[][] dp = new boolean[st.length()][st.length()];
	    int count = 0;

	    // base case : every string with one character is a palindrome
	    for (int i = 0; i < st.length(); i++) {
	      dp[i][i] = true;
	      count++;
	    }

	    //typical loop direction for palindrome qns
	    for (int startIndex = st.length() - 1; startIndex >= 0; startIndex--) {
	      for (int endIndex = startIndex + 1; endIndex < st.length(); endIndex++) {
	        
	    	if (st.charAt(startIndex) == st.charAt(endIndex)) {
	          // when 2 chars are same : there are 2 possibilities
	    	  // if it's a two character string 
	    	  //	or the remaining string is a palindrome too
	          if (endIndex - startIndex == 1 || dp[startIndex + 1][endIndex - 1]) {
	            dp[startIndex][endIndex] = true;
	            count++;
	          }
	        }
	      }
	    }

	    return count;
	 }



}
