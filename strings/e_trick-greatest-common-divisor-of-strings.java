/* easy
1071. Greatest Common Divisor of Strings
DescriptionHintsSubmissionsDiscussSolution

For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)

Return the largest string X such that X divides str1 and X divides str2.

 

Example 1:

Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"

Example 2:

Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
*/
class Solution {
    /*
    just like done for gcd of int
    if(b==0)
        return a;
    return gcd(a,a%b); // Euclids method
    
    */
    public String gcdOfStrings(String str1, String str2) {
        if (str1.length() < str2.length()) { return gcdOfStrings(str2, str1); } // make sure str1 is not shorter than str2.
        else if (!str1.startsWith(str2)) {
                return ""; 
        } // shorter string is not common prefix.
        else if (str2.isEmpty()) { 
            return str1; 
        } // gcd string found.
        else {
            return gcdOfStrings(str1.substring(str2.length()), str2); 
        } // cut off the common prefix part of str1.
    }
}
