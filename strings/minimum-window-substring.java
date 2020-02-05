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
    /*
    public String minWindow(String s, String t) {
        if(t == null || s == null || t.length() == 0 || s.length() == 0)
            return "";
        
        System.out.println("t.length() = "+t.length());
        
        //to maintain window state, and represent target to check against
        HashMap<Character,Integer> target = new HashMap<>();//ONLY t's CHAR WILL HAVE ENTRY IN THIS MAP
        HashMap<Character,Integer> window = new HashMap<>();//ONLY t's CHAR WILL HAVE ENTRY IN THIS MAP
        String ret="";
        
        //prepare target
        for(char c : t.toCharArray()) {
            target.put(c, target.getOrDefault(c,0)+1);
        }
        
        int min=s.length();
        
        //done chars
        int done = 0;
        
        //window
        int i=0;        
        for(int j=0;j<s.length();j++) {
            if(!target.containsKey(s.charAt(j))) {
                continue;
            }
                       
            //add to window
            window.put(s.charAt(j), window.getOrDefault(s.charAt(j),0) + 1);
            
            //increase done count
            done++;
            
            //if done == all, slide from left till not done
            //while slide ONLY till its not done by one char
            while(done == t.length()) {
                //calc min
                min = Math.min(min,j-i+1);
                ret = s.substring(i,j+1);
                
                System.out.println(ret);
                
                //slide - 
                problem in this logic and i++ 
                if(target.containsKey(s.charAt(i)) ) { //window char count must not go beyond target
                    
                    if(window.get(s.charAt(i)) >= target.get(s.charAt(i))) {
                        window.put(s.charAt(i), window.get(s.charAt(i)) - 1);
                        done--;    
                    }                                        
                }
                i++;
            }
        }
        
        return ret;
    }
    */
    public String minWindow(String s, String t) {
        // corner case
        if(s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) return "";
        
        // track min indices so that we can trturn min substring
        int minLeft = 0;
        int minRight = 0;
        
        //track min substring len
        int min = s.length();
        boolean flag = false;
        
        //record state of pattern in map. then dec/inc as we go thru each char 
        Map<Character, Integer> map = new HashMap<>();
        
        //this tells us when we have match and till how long we have match when we slide window to squeeze
        int count = t.length(); // the number of characters that I need to match
        for(char c : t.toCharArray()) 
            map.put(c, map.getOrDefault(c, 0) + 1);
        
        // unfixed sliding window, 2 pointers
        int i = 0;
        int j = 0;
        while(j < s.length()){
            char c = s.charAt(j);
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);//Step1: keep decrementing regardless/ we will take care of -ve vals, while squeezin window. 
                if(map.get(c) >= 0) // we matched one char so count--. But since we allow -ve vals, the dont count in match
                    count--; 
            }
            
            // if found a susbtring - we need to squeeze from left TILL its no longer matched
            //IMP: once we ahve match, all entries in map will be 0 or negative. once any entry is 1, no loonger match
            while(count == 0 && i <= j){
                // update global min
                flag = true;
                int curLen = j + 1 - i;
                
                //record min and indices
                if(curLen <= min){
                    minLeft = i;
                    minRight = j;
                    min = curLen;
                }
                
                // shrink left pointer
                char leftC = s.charAt(i);
                if(map.containsKey(leftC)){
                    map.put(leftC, map.get(leftC) + 1);
                    if(map.get(leftC) >= 1) 
                        count++;//Step2 - when entry goes >=1. our string/window dont match anymore
                }
                i++;
            } 
            j++;
        }
        
        return flag == true ? s.substring(minLeft, minRight + 1): "";
    }
}
