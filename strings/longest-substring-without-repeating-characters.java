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

/*
Optimized sliding window O(n), above soln is O(2n)

*/
public int lengthOfLongestSubstringFaster(String s) {
        
        Map<Character, Integer> map= new HashMap<>();
        int start=0, len=0;
        
        // abba
        //window is [start,i]
        for(int i=0; i<s.length(); i++) { //expanding window from right
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (map.get(c) >= start)    // Key trick. only if i > start
                    start = map.get(c) + 1; //key trick. advance start to +1 from repeated char on left. SLIDING window from left
            }
            len = Math.max(len, i-start+1);
            map.put(c, i);    //we put char to map every time with updated index
        }
        
        return len;
}
