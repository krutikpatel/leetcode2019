/*
Minimum Window Substring

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

Note:

    If there is no such window in S that covers all characters in T, return the empty string "".
    If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

*/
class Solution {
    /*
    Algo:
    -maintain window [i,j]
    -begin from zero and find your first eligible window.
    -once found
        -remove one char at time from left side (i), and see if still eligible
        -How do we know if window found ?
            -we maintain matchedCharSoFarCount, once == targetMap.size() , match found
            
    -once not eligible
        -move right pointer (j). 
        
    Imp Tool:
    -In order to check against desired set of chars(String t), we need to make HashMap<char,countInt>
    -For our sliding window, to check if we have match instantly, we maintain another HashMap<char,countInt>
        -here we only put desired chars, not the unncessary chars
    
    Note:
    always check if char key present in map before updating, there are cases where count ecame zero and it wont be present ?
    Advanced:
        that HashMap can be replaced with int[26] 
    */
    public String minWindow(String s, String t) {
        if(t == null || s == null || t.length() == 0 || s.length() == 0)
            return "";
        
        System.out.println("s.length() = "+s.length());
        
        //to maintain window state, and represent target to check against
        HashMap<Character,Integer> target = new HashMap<>();
        HashMap<Character,Integer> window = new HashMap<>();
        
        //fit target map
        for(char c: t.toCharArray()){
            int currCount = target.getOrDefault(c,0);   //Good utility method of map
            target.put(c,currCount+1);
        }        
        
        //window two pointers
        int i=0,j=0;
        int matchedCharSoFarCount = 0;  //done chars
        int minLen = Integer.MAX_VALUE;
        String min = "";
        
        for(j=0;j<s.length();j++){    //i only moves conditionally
            char c = s.charAt(j);
                
            //update window count
            int currCount = window.getOrDefault(c,0);   //Good utility method of map
            window.put(c,currCount+1);                

            //update matchedCharSoFarCount
            if(window.get(c) == target.get(c)){  //IMP - Only update once. Think 'a' count matched, if we find more 'a' we must not incement matchedCharSoFarCount
                matchedCharSoFarCount++;
                System.out.println("matchedCharSoFarCount = "+matchedCharSoFarCount);
            }             
            
            //if found target, shrink from left until invalid
            while(matchedCharSoFarCount == target.size() && i<=j){

                //since we found target, record min
                if(matchedCharSoFarCount == target.size()){
                    if(j-i+1 < minLen){
                        minLen = j-i+1;
                        min = s.substring(i,j+1);                            
                        System.out.println("min = "+min);
                    }
                }

                //remove i'th char - slide from left
                char ci = s.charAt(i);
                //NOTE : only update window if c is in target, otherwise just i++
                if(target.containsKey(ci)){                                               
                    window.put(ci, window.get(ci)-1); 

                    //update matchedCharSoFarCount
                    if(window.get(ci) < target.get(ci)){ //if we have more chars than reqd, we are still good. otherwise only we need update
                        matchedCharSoFarCount--;
                    }
                }
                i++;
            }                 
        }
        
        return min;
    }
}
