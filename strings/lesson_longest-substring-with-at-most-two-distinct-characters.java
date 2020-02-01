/*
159. Longest Substring with At Most Two Distinct Characters
DescriptionHintsSubmissionsDiscussSolution

Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.

Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
*/
class Solution {
    /*
    v good inout test case: "abaccc"
    
    Lesson: go with HashMap<char, int> . here we store index, sometimes we store count
    
    Note:
    -we might be tempted to <char.lastindex>
    -and when we find 3rd char, just remove/disard leftmost char and everything till lastIndex of leftmost char. 
    But that is flawed. example: abbbbbac
        -as soon as we c, we cant just jump iorleft pointer to c.
    
    -so we need to remove leftmost (from right, ie, "finished")  character (that is min entry in map)    
    
    Lesson: for sliding window, dont get ambitious and greedy, while sliding. Only slide minimum, conservatively.    
    
    */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        //char and latest(right most) index for that char
        HashMap<Character,Integer> map = new HashMap<>();
        int max=0;
        int i=0;
        int j=0;
        
        while(j<s.length()) {
            
            if(map.size() <3) {                
                //add to map
                char curr = s.charAt(j);
                map.put(curr,j);                               
            }
            
            //if map overflows, make correction
            if(map.size()==3) {
                //remvoe from i side
                //find leftmost char to remove. for that, find leftmost index
                int leftmost = s.length();//imp to se something obvious high                
                for(char c: map.keySet()) {
                    leftmost = Math.min(leftmost, map.get(c));
                }
                char leftmostChar = s.charAt(leftmost);                
                
                map.remove(leftmostChar);
                i = leftmost+1;                
            }
            
            //record max in either case
            max = Math.max(max, j-i+1);
            j++;
        }
        
        return max;
    }
    
    ///////////////////////////////////////////////////////////////////////
    public int lengthOfLongestSubstringTwoDistinct_nonclean(String s) {
        Set<Character> set = new HashSet<>();
        String window = "";
        int max=0;
        int i=0;
        int j=0;
        
        while(i<=j && j<s.length()) {
            if(set.contains(s.charAt(j))) {
                //good. extend window
                window = window + s.charAt(j);
                max = Math.max(max, j-i);
                j++;
                
            } else if(set.size()<2){
                //add to set, extend window
                window = window + s.charAt(j);
                set.add(s.charAt(j));
                max = Math.max(max, j-i);
                j++;
                
            } else {                
                char toremove = 'a';
                //count unique chars in window, i-- till its 1
                while(!isOneunique(window) && i<j) {
                    toremove = s.charAt(i);
                    i++;
                    window = window.substring(1);
                }
                //now we can add char-j
                set.remove(toremove);
                set.add(s.charAt(j));
                max = Math.max(max, j-i);
                j++;
            }
        }
        
        return max;
    }
    
    public boolean isOneunique(String window) {
        char x = window.charAt(0);
        for(char c:window.toCharArray()) {
            if(x!=c)
                return false;
        }
        return true;
    }
}
