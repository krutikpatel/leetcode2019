package dplocal.palindromic;
/*
 * Given a sequence, find the length of its Longest Palindromic Subsequence (LPS). In a palindromic subsequence, 
 * elements read the same backward and forward.
 * Example 1:

Input: "abdbca"
Output: 5
Explanation: LPS is "abdba".

 */
public class _1LongestPalindromicSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

/*
 * Bruteforce:
 * 
 * A basic brute-force solution could be to try all the subsequences of the given sequence. 
 * We can start processing from the beginning and the end of the sequence. So at any step, we have two options:

a. If the element at the beginning and the end are the same, we increment our count by two 
	and make a recursive call for the remaining sequence.
b. We will skip the element either from the beginning or the end to make two recursive calls for the remaining subsequence.
	
 */
	//use start and end index for recursive calls
	public int findLPSLength(String st) {
	    return findLPSLengthRecursive(st, 0, st.length()-1);
	}

	private int findLPSLengthRecursive(String st, int startIndex, int endIndex) {
	    if(startIndex > endIndex)
	      return 0;

	    // every sequence with one element is a palindrome of length 1
	    if(startIndex == endIndex)
	      return 1;

	    // case 1: elements at the beginning and the end are the same
	    if(st.charAt(startIndex) == st.charAt(endIndex))
	      return 2 + findLPSLengthRecursive(st, startIndex+1, endIndex-1);

	    // case 2: skip one element either from the beginning or the end
	    int c1 =  findLPSLengthRecursive(st, startIndex+1, endIndex);
	    int c2 =  findLPSLengthRecursive(st, startIndex, endIndex-1);
	    return Math.max(c1, c2);
	}
//////////////////	  
	
/*
 * Memoization:
 * -we can see above that if we had cached results for prob(start,end), we cud use that. so do that
 * 
 * Time - complexity:
 * What is the time and space complexity of the above solution? 
 * Since our memoization array dp[st.length()][st.length()] stores the results for all the subproblems, 
 * we can conclude that we will not have more than N*N subproblems (where ‘N’ is the length of the input sequence). 
 * This means that our time complexity will be O(N^2)

 * top-down - recursive	
 */
	public int findLPSLength2(String st) {
	    Integer[][] dp = new Integer[st.length()][st.length()];
	    return findLPSLengthRecursive(dp, st, 0, st.length()-1);
	}

	private int findLPSLengthRecursive(Integer[][] dp, String st, int startIndex, int endIndex) {
	    if(startIndex > endIndex)
	      return 0;

	    // every sequence with one element is a palindrome of length 1
	    if(startIndex == endIndex)
	      return 1;

	    if(dp[startIndex][endIndex] == null) {
	      // case 1: elements at the beginning and the end are the same
	      if(st.charAt(startIndex) == st.charAt(endIndex)) {
	        dp[startIndex][endIndex] = 2 + findLPSLengthRecursive(dp, st, startIndex+1, endIndex-1);
	      } else {
	        // case 2: skip one element either from the beginning or the end
	        int c1 =  findLPSLengthRecursive(dp, st, startIndex+1, endIndex);
	        int c2 =  findLPSLengthRecursive(dp, st, startIndex, endIndex-1);
	        dp[startIndex][endIndex] = Math.max(c1, c2);
	      }
	    }

	    return dp[startIndex][endIndex];
	  }

//////////////////
/*
 * Bottom - up iterative	
 * 
 * -loop directions not intuitive
 * 	-we start loop indices in that way because:
 * 		-we need to fetch know subproblem solution in iterative method. we cant recurse to figure that out.
 * 		-visualize matrix. startIndex is ROW, and endIndex is COL
 * 			-at last char/row, we know solution
 * 			-then, we expand the substrings
 */
	public int findLPSLength3(String st) {
	    // dp[i][j] stores the length of LPS from index 'i' to index 'j'
	    int[][] dp = new int[st.length()][st.length()];

	    // known base case : every sequence with one element is a palindrome of length 1
	    for (int i = 0; i < st.length(); i++)
	      dp[i][i] = 1;

	    //imp  - start from right
	    for (int startIndex = st.length() - 1; startIndex >= 0; startIndex--) {
	    	
	    	//end : from: start+1 to len. not starting from j=i, because that we already know ans = 1
	      for (int endIndex = startIndex + 1; endIndex < st.length(); endIndex++) {
	    	  // case 1: elements at the beginning and the end are the same
	        if (st.charAt(startIndex) == st.charAt(endIndex)) {
	          dp[startIndex][endIndex] = 2 + dp[startIndex + 1][endIndex - 1];//DP is all about levelraging prev solns. so here: 2+ prevsoln.
	          																//for i, prev is i+1, for j prev is j-1. because of their loop directions
	        } else { 
	        	// case 2: skip one element either from the beginning or the end
	          dp[startIndex][endIndex] = Math.max(dp[startIndex + 1][endIndex], 
	                                              dp[startIndex][endIndex - 1]);//max of prev 2 popssibilities
	        }
	      }
	    }
	    
	    //dp i,j where loop ends
	    return dp[0][st.length() - 1];
	}
}
