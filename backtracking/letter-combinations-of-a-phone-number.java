/*
Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

*/
/*
making dictionary in proper way is kind of good trick
*/
class Solution {
    HashMap<Character, char[]> dict;
    
    public List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList();
        
        if(digits == null || digits.length() == 0)
            return ret;
        
        dict = new HashMap();
        dict.put('2', new char[]{'a','b','c'});
        dict.put('3', new char[]{'d','e','f'});
        dict.put('4', new char[]{'g','h','i'});
        dict.put('5', new char[]{'j','k','l'});
        dict.put('6', new char[]{'m','n','o'});
        dict.put('7', new char[]{'p','q','r','s'});
        dict.put('8', new char[]{'t','u','v'});
        dict.put('9', new char[]{'w','x','y','z'});
        
        helper("",digits,ret);
        return ret;
    }
    
    private void helper(String soFar, String n, List<String> ret){
        //System.out.println(soFar);
        if(soFar.length() == n.length()){
            ret.add(new String(soFar));
            return;
        }
        
        //for(int i=soFar.length();i<n.length();i++){ - we dont want string to begin with 2nd digit.            
        int i=soFar.length();
        char[] cc = dict.get(n.charAt(i));
        //out only choice is chars for given digit
        for(int j = 0;j<cc.length;j++){
            //make choice
            soFar = soFar + (cc[j]);

            //take further
            helper( soFar,n,ret);

            //backtrack last move. remove last char
            soFar = soFar.substring(0,soFar.length()-1);
        }
        
    }
}
