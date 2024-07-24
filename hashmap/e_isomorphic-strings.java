/* easy
Isomorphic Strings

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true

Example 2:

Input: s = "foo", t = "bar"
Output: false

Example 3:

Input: s = "paper", t = "title"
Output: true
*/
class Solution {
    public boolean isIsomorphic(String s, String t) {
        /*
        -put all chars of 's' in map
        -map same indexed char with char of t.
            -while doing that if you find its already mapped to some other char, return false
        
        BUT single side cannot dictate result, above condtion must be true both ways s to t and t to s.
        */
        if(s == null || t == null || s.length() != t.length())
            return false;
        
        HashMap<Character,Character> map = new HashMap<>();
        HashMap<Character,Character> map2 = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char a = s.charAt(i);
            char b = t.charAt(i);
            if(map.containsKey(a)){
                if(map.get(a) != b)
                    return false;
            }else{
                map.put(a,b);
            }
            
            //map2
            if(map2.containsKey(b)){
                if(map2.get(b) != a)
                    return false;
            }else{
                map2.put(b,a);
            }
            
        }
        return true;
    }
    
    public boolean isIsomorphic(String s, String t){
        //int arr as char map
        //we store char count at ASCII'th index
        int [] ss = new int[256];
        int [] tt = new int[256];

        //if we dont want to do i+1
        //Arrays.fill(ss,-1);
        //Arrays.fill(tt,-1);
        
        for(int i = 0; i< s.length();i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            //if same char has diff mapping to other char. think aa, ab
            if(ss[c1] != tt[c2]){
                return false;
            }
            
            //add entries in respective maps
            //we need a var like i to catch case like: bbbaaaba and aaabbbba. same no of letters but not isomorphic.
            //and need a const rand number , 1 or anything else
            //The "i + 1" made me a bit confused, then realized "+1" is to exclude 0 from valid index. Why not initialzing the array as -1, and simply keep i in the array. This also avoids the overflow issue. AC code as below:
            //https://leetcode.com/problems/isomorphic-strings/solutions/57796/my-6-lines-solution/comments/59442

            //but ss[c1]=0 will be ambiguous. if we dont initialize array to -1 (OR do i+1), we dont know if its present or just not initialized
            ss[c1] = i+ 1;
            tt[c2] = i + 1;
            
            //ss[c1] = i;//we want to use 0th index, but ss[c1]=0 should mean that its present. if we dont initialize array to -1 (OR do i+1), we dont know if its present or just not initialized
            //tt[c2] = i;
        }
        return true;
    }
    
    public boolean isIsomorphicLogicFriendly(String s, String t) {
        
        int[] mappingDictStoT = new int[256];
        Arrays.fill(mappingDictStoT, -1);
        
        int[] mappingDictTtoS = new int[256];
        Arrays.fill(mappingDictTtoS, -1);
        
        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            
            // Case 1: No mapping exists in either of the dictionaries
            if (mappingDictStoT[c1] == -1 && mappingDictTtoS[c2] == -1) {
                mappingDictStoT[c1] = c2;
                mappingDictTtoS[c2] = c1;
            }
            
            // Case 2: Ether mapping doesn't exist in one of the dictionaries or Mapping exists and
            // it doesn't match in either of the dictionaries or both 
            else if (!(mappingDictStoT[c1] == c2 && mappingDictTtoS[c2] == c1)) {
                return false;
            }
        }
        
        return true;
    }
}
}
