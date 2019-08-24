/*
Decode Ways
DescriptionHintsSubmissionsDiscussSolution

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26

Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).

Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/
class Solution {
    /*
    IMP note:
    12 is NOT decoded as 1 and 2
        It could be decoded as "AB" (1 2) or "L" (12).
        
    Think: 202 (ans 1)    
    303 (ans 0)
    */    
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        
        dp[0] = 1;  //empty string as 1, why ???
        dp[1] = s.charAt(0) != '0' ? 1 : 0; //conside char = 0
        
        if(dp[1] == 0)
            return 0;   //cant do anything with string beginning with 0
        
        //we need to continue from dp[2] which is for char index 1
        //because we want to see string till/before i-2 and string till/before i-1, and make decision based on curr char
        for(int i = 2; i <= n; i++) {

            int first = s.charAt(i-1) - '0';
            int second = Integer.valueOf(s.substring(i-2, i));

            int way1 = 0;
            if(first != 0) {
               way1 = dp[i-1];  //continuing i-1 string with curr char (0 is not any ASCII a-z), no more solution added
            }
            
            int way2 = 0;
            if(second >= 10 && second <= 26) {
                //that forms 1 separate char to dp[i-2]. but cant simply add 1 to dp[i-2] because we dont know what dp[i]
                //dp[i] += dp[i-2]; //adding dp[i-1] to whatever we found before as dp[i], 0 or dp[i-1]   
                way2 = dp[i-2];// continuig string from i-2, adding one char
            }
            dp[i] = way1 + way2;
        }
        return dp[n];
    }
}
