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
    
    at -i: way1 = just that char
            way2 = i-1,i (if that is betn 10 to 26)
            
    way1 = dp[i-1] , just continuiing that string
    way2 = dp[i-2] plus 2-digit num , just continuiing that string
    
    so dp[i] = way1 + way2

    212 can be
    BAB
    Or
    BL
    Or
    SB
    
    */    
    public int numDecodings(String s) {
        // DP array to store the subproblem results
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;//empty string
        
        // Ways to decode a string of size 1 is 1. Unless the string is '0'.
        // '0' doesn't have a single digit decode.
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        //notice begin index = 2, easier to follow 
        for(int i = 2; i < dp.length; i++) {
            //Note: the order of these 2 operations does not matter since its addition
            // option1: Check if successful single digit decode is possible.
            if (s.charAt(i - 1) != '0') {
                //dp[i-1] and curr char
               dp[i] = dp[i - 1];  //notice - we just conitue same value, we dont increment because we are just forming string as one option
            } else {
                //dp[i] is still 0
            }
            
            // option2: Check if successful two digit decode is possible.
                //dp[i-2] and 2 digit num
            int twoDigit = Integer.valueOf(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];//we add prev dp, because this leads to another way to form string
            }
        }
        
        return dp[s.length()];
    }
    
    public int numDecodingsOld(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        /*
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
        */
        
        int n = s.length();
        int[] dp = new int[n];
        
        dp[0] = s.charAt(0) != '0' ? 1 : 0;
        
        for(int i = 1; i < n; i++) {
            int first = Integer.valueOf(s.substring(i, i+1));   //1th char
            int second = Integer.valueOf(s.substring(i-1, i+1));//0,1 chars
            
            int currWays = 0;//dp[i], creating var for more clarity
            //consider curr one char
            if(first!=0){
                //continue same sentense/way
                currWays = dp[i-1];
            }
            
            //consider curr and prev char to form a-z if possible
            if(second >= 10 && second <= 26) {
                if(i>=2)
                    currWays = currWays + dp[i-2];//we found another way to create string at ith position.
                else
                    currWays += 1;
                                
            }
            dp[i] = currWays;
        }
        
        return dp[n-1];
    }
}
