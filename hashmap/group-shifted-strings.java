/*
Group Shifted Strings

Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"

Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output: 
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
*/
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        /*
        main trick is to form HashMap key
        -make key as String using diff in ASCII of to chars % 26 (because it has to be circular)
        */
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();  	
        
        for (String s : strings) {
            String key = "";
            for (int i = 1; i < s.length(); i++) 
                key += String.format("%2d", (s.charAt(i) - s.charAt(i-1) + 26) % 26);//Difference from the previous char.
            
            if (!map.containsKey(key)) 
                map.put(key, new ArrayList<String>());
            
            map.get(key).add(s);    		
        } 
        
        return new ArrayList<List<String>>(map.values());
    }
}
