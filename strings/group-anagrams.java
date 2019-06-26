/*
group anagrams

Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

*/
/*
Solution time complexity:
Time Complexity: O(NKlog⁡K)O(NK \log K)O(NKlogK), where NNN is the length of strs, and KKK is the maximum length of a string in strs. 
The outer loop has complexity O(N)O(N)O(N) as we iterate through each string. Then, we sort each string in O(Klog⁡K)O(K \log K)O(KlogK) time.
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<>();
        
        if(strs == null || strs.length == 0)
            return ret;
        
        HashMap<String,List<String>> map = new HashMap<>();
        for(String s: strs){
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            
            if(map.containsKey(key)){
                map.get(key).add(s);
            }else{
                List<String> l = new ArrayList();
                l.add(s);
                map.put(key,l);
            }
        }
        
        return new ArrayList<>(map.values());//notice inline creation and setting vals
    }
}
