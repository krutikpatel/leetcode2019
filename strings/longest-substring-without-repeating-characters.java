/*
longest-substring-without-repeating-characters

Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 

Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length() == 0)
            return 0;
        
        Set<Character> set = new HashSet();
        int ret = 0;
        
        //extend window [i,j]        
        int i = 0, j = 0;
        while (i < s.length() && j < s.length()) {
            if(!set.contains(s.charAt(j))){ //extend window from j side
                set.add(s.charAt(j));
                j++;
                ret = Math.max(ret,j-i);                
            }else{
                //slide window from i side
                set.remove(s.charAt(i)); //imagine case: pwwkew. imp to remove charAt i, that is our "sliding"
                                         //in next iteration, j will add it to set, notice, we did not move j.
                i++;
            }            
        }
        
        return ret;
    }
}
