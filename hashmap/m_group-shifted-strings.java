/* medium
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
/*
Basically we need to form some sort of key for each word to group them. (Remember the idea of group all anagrams?)

Consider acf and pru. Now notice the differnce between each characters.
acf = 0->2->3, and pru = 0->2->3. So these two form same group. So in this case, you can simply use integers ASCII difference to form key.

Now consider corner case, az and ba, where az = 0->25 and ba = 0->-1. When it goes negative, 
just make it positive(rotate or mod equivalent) by adding 26. So it becomes, ba = 0->25, which forms same group.
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
