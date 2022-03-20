/*
https://leetcode.com/problems/word-break-ii/ ---------- V IMP to understand - recursion and DP - add to recursion and dp lists 
		-https://leetcode.com/problems/word-break-ii/discuss/44167/My-concise-JAVA-solution-based-on-memorized-DFS
		
		Algo:
		-solve bruteforce for better understanding. 
			-char by char, form work and lookup in dict.
			-recursively cann for substring i-to-end 
			
		-DP- brute force DFS is slow, So we need to memoize.
			-what to memo? - if we have answer for given (prefix) string 
			HashMap<String, List<String> answers>
			
			-Here also 2 approaches
			a. recursively solveing postfix substring (i-to-end) is slower
			b. rather than looking up dict match for substring from begin-to-i, look for substring i-to-end
				-and recursively solve for substring 0-to-i
				-add sublist strings + " "+postfix word 
	
*/
class Solution {
    /*
    brute force recursion - char by char string formation and lookup in dict.
    -if found, recursive call from next char - as if we are given new string from i+1 th char
    -maintain sofar string whcih we can add to result when begin ==s.length 
    */
    public List<String> wordBreakBF(String s, List<String> wordDict) {
        List<String> ret = new ArrayList<>();
        Set<String> dict = new HashSet<>();
        dict.addAll(wordDict);
        
        helperBF(s,0,"",dict,ret);
        return ret;
    }
    private void helperBF(String s, int begin, String sofar, Set<String> wordDict, List<String> ret){
        if(begin >= s.length()){
            ret.add(sofar);
            return;
        }

        String curr ="";
        for(int i=begin;i<s.length();i++){
            // s.substring(begin-1,i);
            curr += s.charAt(i);
            //check if curr is present in dict 
            if(wordDict.contains(curr)){
                //recursive 
                String next = sofar.length()==0?"":sofar+" ";
                helperBF(s,i+1,next+curr,wordDict, ret);
            }
        }
    }
    
///////////////////////    
/// DP ///
    
    
    //recursive call will be made on substring on same method signature
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ret = new ArrayList<>();
        Set<String> dict = new HashSet<>();
        dict.addAll(wordDict);
        
        //memo solution to substrings
        HashMap<String,List<String>> map = new HashMap<String,List<String>>();
        
        ret = helperDp(s,dict,map);
        return ret;
    }
    
    public List<String> helperDp(String s, Set<String> dict, HashMap<String,List<String>> map) {
        List<String> currRet = new ArrayList<>();
        if(s.length()==0){
            return currRet;
        }
        
        if(map.containsKey(s)){
            return map.get(s);
        }
        
        //corner case
        if(dict.contains(s)) 
            currRet.add(s);
        
        /*
        recursively solveing postfix substring (i-to-end) is slower
        //try all substrings
        for(int i=1;i<s.length();i++){
            //check each substring from beginning
            String currWord = s.substring(0,i);
            
            if(dict.contains(currWord)){
                //prep next rec call string
                String next = s.substring(i);
                List<String> sublist = helperDp(next,dict,map);
                
                for (String sub : sublist){
                    //preparing bigger string with spaces - intermediate results
                    currRet.add(currWord + (sub.isEmpty() ? "" : " ") + sub);
                }
                
            }
        }
        */
        //rather than looking up dict match for substring from begin-to-i, look for substring i-to-end
        for(int i = 1 ; i < s.length() ; i++) {
            String post = s.substring(i);
            if(dict.contains(post)) {
                //recursively solve prefix ie, 0-to-i substring
                List<String> sublist = helperDp(s.substring(0 , i) , dict,map);
                for (String sub : sublist){
                    //preparing bigger string with spaces - intermediate results
                    currRet.add(sub +" "+post);
                }
            }
        }
        
        map.put(s,currRet);
        return currRet;
    }
///////////////////////
    /*
    Brute force:
    keep adding one char
    -at each char, check if match in dict
    -if so, make dfs/recursive call
    */  
    public List<String> wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        set.addAll(wordDict);
        List<String> ret = new ArrayList<>();
        
        helper2(0,"",s,set,ret);
        return ret;
    }
    
    private void helper2(int start, String soFar, String s, Set<String> wordDict, List<String> ret) {
        if(start == s.length()){
            ret.add(soFar);
            return;
        }
            
        String curr = "";
        for(int i=start;i<s.length();i++) {
            curr += s.charAt(i);
            if(wordDict.contains(curr)){
                String next = soFar;
                if(soFar.length() != 0) {
                    next = soFar+" "+curr;
                } else {
                    next = curr;
                }
                helper2(i+1,next, s, wordDict, ret);
            }
        }
    }
}
