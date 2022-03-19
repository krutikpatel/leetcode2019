//https://leetcode.com/problems/remove-invalid-parentheses
//return all valid strings, by removal of min braces
/*
		-BFS:
		-Since optimal soluion is asked, we should think of BFS.
			-meaning : parallelly explore removing one parantheses from each position, and keep doing that at each level
			-strings resulting by removing one paranthesis from each positions, becomes my neoghbors for next level BFS
		-maintain Set of visited strings 
			// generate all possible states, as next BFS level
			for (int i = 0; i < s.length(); i++) {
			  // ignore non-paranthesis chars
			  if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;
			
			  String t = s.substring(0, i) + s.substring(i + 1);
			
			  if (!visited.contains(t)) {
				// for each state, if it's not visited, add it to the queue
				queue.add(t);
				visited.add(t);
			  }
			}
		-trick for validity : 
			if ( count++
			if ) count--
			if(count <0) -> invalid/false
		-no need to worry about char inbetween. parantheses validity will be same regardless of chars between them.
		
		DFS: TODO
		-count invalid open and close braces.
		-Construct string from bottom up, dfs, using those counters.
			-
      
*/
class Solution {
    /*
    Followable DFS solution:
    https://leetcode.com/problems/remove-invalid-parentheses/discuss/75034/Easiest-9ms-Java-Solution
    -good for understanding of open close bracket counts and recursion calls
    
    */
    /*
    -Since optimal soluion is asked, we should think of BFS.
    -meaning : parallelly explore removing one parantheses from each position, and keep doing that at each level
        -strings resulting by removing one paranthesis from each positions, becomes my neoghbors for next level BFS
    */
    public List<String> removeInvalidParentheses(String s) {
      List<String> res = new ArrayList<>();
      
      // sanity check
      if (s == null) return res;
      
      Set<String> visited = new HashSet<>();
      Queue<String> queue = new LinkedList<>();
      
      // initialize
      queue.add(s);
      visited.add(s);
      
      boolean found = false;
      
      while (!queue.isEmpty()) {
        s = queue.poll();
        
        if (isValid(s)) {
          // found an answer, add to the result
          res.add(s);
          found = true;
        }
      
        if (found) continue;
      
        // generate all possible states, as next BFS level
        for (int i = 0; i < s.length(); i++) {
          // ignore non-paranthesis chars
          if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;
        
          String t = s.substring(0, i) + s.substring(i + 1);
        
          if (!visited.contains(t)) {
            // for each state, if it's not visited, add it to the queue
            queue.add(t);
            visited.add(t);
          }
        }
      }
      
      return res;
    }
    
    // helper function checks if string s contains valid parantheses
    boolean isValid(String s) {
      int count = 0;
    
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == '(') count++;
        //if (c == ')' && count-- == 0) return false;
        else if(c==')'){
			if(count == 0){
				return false;
			}
			count--;
		}
      }
    
      return count == 0;
    }
    
    ////////////////
    /// DFS or bottom up building way ///
    public List<String> removeInvalidParenthesesDfs(String s) {
        int rmL = 0, rmR = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                rmL++;
            } else if (s.charAt(i) == ')') {
                if (rmL != 0) {
                    rmL--;
                } else {
                    rmR++;
                }
            }
        }
        List<String> res = new ArrayList<>();
        dfs(s, 0, res, "", rmL, rmR, 0, '&');
        return res;
    }

    public void dfs(String s, int i, List<String> res, String temp, int rmL, int rmR, int open, char lastSkipped) {
        if (rmL < 0 || rmR < 0 || open < 0) {
            return;
        }
        if (i == s.length()) {
            if (rmL == 0 && rmR == 0 && open == 0) {
                res.add(temp);
            }
            return;
        }

        char c = s.charAt(i);

        if (c == '(') {
            if (rmL > 0)
                dfs(s, i + 1, res, temp, rmL - 1, rmR, open, '(');		    // not use (

            if (lastSkipped != '(')
                dfs(s, i + 1, res, temp + c, rmL, rmR, open + 1, '&');       // use (

        } else if (c == ')') {
            if (rmR > 0)
                dfs(s, i + 1, res, temp, rmL, rmR - 1, open, ')');	            // not use  )

            if (open > 0 && (lastSkipped != ')'))
                dfs(s, i + 1, res, temp + c, rmL, rmR, open - 1, '&');  	    // use )

        } else {
            dfs(s, i + 1, res, temp + c, rmL, rmR, open, '&');
        }
    }
}
