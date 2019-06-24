/*
Longest Common Prefix
https://leetcode.com/explore/learn/card/array-and-string/203/introduction-to-string/1162

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"

Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Note:

All given inputs are in lowercase letters a-z.

*/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        
        /*
        -find min and max "value" . we dont need to sort them just for this        
        NOTE: we dont want min/max length string ! because we want first and last of alphabetically "sorted".
            Example: ["c","acc","ccc"] , min by length = "c", max by length can be "ccc", BUT we want "acc", otherwise we get wrong ans.
        So, we need to use: str.compareTo() function.    
        
        -then compare common chars in them
        
        -Corner cases: [""], ["a"], ["a",""], ["a","b"]
        */
        
        if(strs == null || strs.length == 0)
            return "";
        
        String min = strs[0];
        String max = strs[0];
        
        for(int i=0;i < strs.length;i++){
            if(strs[i].compareTo(min) < 0){
                min = strs[i];
            } else if(strs[i].compareTo(max) > 0){
                max = strs[i];
            }
        }
        
        //now just find common prefix for min and max
        int i=0;
        while (i < min.length() && min.charAt(i) == max.charAt(i))
            i++;
        
        return min.substring(0, i);
    }
}
