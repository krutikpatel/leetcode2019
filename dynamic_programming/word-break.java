/*
Word Break

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
*/
class Solution {
    /*
    If you just match what u found first, result will be wring: Think:
        "catscat"
        ["cats","cat"]
        
    dp[i] = dp[j] && dp[j to i]    
    
    -if you think, this problem sound recursive, but good example to see how its done iteratively.
        -2 loops and respective indexes in both loop
        
    Both are order of O(n^2)    
    */
    
    //simpler version of DP
    public boolean wordBreak(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }
    public boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end, memo)) {
                memo[start] = true;
                return true;
            }
        }
                
        memo[start] = false;
        return false;
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean wordBreak2(String s, List<String> wordDict) {
        if(s== null | s.length()==0)
            return false;
        
        HashSet<String> set = new HashSet(wordDict);
        boolean[] memo = new boolean[s.length()+1];
        //we know
        memo[0] = true;//empty word
        
        /*
        imp i<=length because j should be able to go till last char, last time
        */
        for(int i=0;i<=s.length();i++){
            /*
            we need to see if sub-string (0 till i) is valid in any way/sub-way
            So, split this substring, at each possible index, and see if its good/valid soln
            -we use j as plit index
            */
            for(int j=0;j<i;j++){
                //use memo and dict to solve this sub-string j to i
                //memo[j] tells whether substring till j is valid
                // 0->j-1 valid j->i valid
                if(memo[j] && set.contains(s.substring(j,i))){  //till j is good, and j to i is also good
                    
                    memo[i] = true; // substring till i has solution
                    break; //so break from curr loop
                }
            }    
        }
        
        //if substring till s.length is good
        return memo[s.length()];
    }
    
    
    
}
