/* easy
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2

Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1

Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

*/
class Solution {
    
    /*
    Note: this cant be solved with two pointer technique due to corner cases
    
    remember to mention Robin Karps's rolling hash, meanign still form correct hash by removing and adding chars as you slide.
    */
    public int strStr(String haystack, String needle) {
        
        if (needle.isEmpty()) 
            return 0; // edge case: "",""=>0  "a",""=>0
        
        int h = haystack.length();
        int n = needle.length();
        
        for (int i = 0; i <= h-n; i++) {
            //for every char, try to see if its needle
            for (int j = 0; j < n && haystack.charAt(i + j) == needle.charAt(j); j++)
                if (j == needle.length() - 1) return i;
        }
        return -1;
    }
}
