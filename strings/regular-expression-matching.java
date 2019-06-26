/*
Regular Expression Matching

Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

Note:

    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like . or *.

*/
class Solution {
    /*
    Recognize that problem can be broken into subproblems,
    Thus, can be solved recursively.
    So, dont think iterative that is more complicated solution to write.
    
    DP soln:
    https://www.youtube.com/watch?v=l3hda49XcDE&list=PLrmLmBdmIlpuE5GEMDXWf0PWbBD9Ga1lO
    
    This DP sol is good with explanation
    https://leetcode.com/problems/regular-expression-matching/discuss/5651/Easy-DP-Java-Solution-with-detailed-Explanation
    
    */
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) 
            return text.isEmpty();
        
        boolean first_char_match = (!text.isEmpty() &&
                               (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
    
        //if second char is * in pattern
        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            
            //need to consider 2 possibilities. 
            //Note: we dont have to check what is there in "text", just put all solns with OR operators ! :)
            return (isMatch(text, pattern.substring(2)) ||  //if zero matching char in text for * in pattern. Eg: bb and pattern a*b
                    (first_char_match 
                     && isMatch(text.substring(1), pattern))); //first char matches, then : just remove 1st char and use same pattern bcoz *
            
        } else {
            //second char is not * so just solve/match 1st chars of both and proceed to second of both
            return first_char_match && isMatch(text.substring(1), pattern.substring(1));            
        }
    }
}
