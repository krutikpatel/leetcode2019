/*
Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
*/
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        arrange(list, "", 0, 0, n);
        return list;
    }
    
    //our choices are n '(' and n ')'
    //call takes care of looping it n times. remember rec call arg is our first loop. and we need only one loop.
    public void arrange(List<String> list, String str, int open, int close, int max){
        
        // goal case
        if(str.length() == max*2){
            list.add(str);
            //System.out.println(str);//can visual the call stack sequence
            return;
        }
        
        //we put choices in order that is correct ( and then )
        if(open < max)
            arrange(list, str+"(", open+1, close, max);
                
        //Key point of bracker problem : this helps ensure right num of closing. 
        // at any point in correct closre, we wont have ( count greater than ) count.
        // because that will sooner orlater lead into )( - which is invalid
        if(close < open)
            arrange(list, str+")", open, close+1, max);
        
        //return from function works as backtracking when we unwind the call stack. 
    }
}