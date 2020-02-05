/*
890. Find and Replace Pattern
DescriptionHintsSubmissionsDiscussSolution

You have a list of words and a pattern, and you want to know which words in words matches the pattern.

A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.

(Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.)

Return a list of the words in words that match the given pattern. 

You may return the answer in any order.

 

Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
since a and b map to the same letter.
*/
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ret = new ArrayList<>();
        for(String s:words) {
            if(isPatternMatch(pattern,s)) {
                ret.add(s);
            }
        }
        return ret;
    }
    
    /*
    lesson - one map pattern to string map not sufficient
    -we need 2 way map
    
    map1 : a->c
    map2 : b-> not mapped. BUT char other side is c which is mapped to something else.
    so that if pattern  = abb
    and s = ccc
    
    so, we need 2 way checking. 
    */
    private boolean isPatternMatch(String p, String s) {        
        if(p.length()!=s.length())
            return false;
        
        HashMap<Character,Character> map1 = new HashMap<>();
        HashMap<Character,Character> map2 = new HashMap<>();
        for(int i=0;i<p.length();i++) {
            char pp = p.charAt(i);
            char ss = s.charAt(i);
            if(map1.containsKey(pp)){
                if(ss != map1.get(pp))
                    return false;
            } else {
                if(map2.containsKey(ss) && pp != map2.get(ss))
                    return false;
                
                map1.put(pp,ss);
                map2.put(ss,pp);
            }
        }
        return true;
    }
}
