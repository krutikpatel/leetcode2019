/* easy
20. Valid Parentheses
DescriptionHintsSubmissionsDiscussSolution

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.

Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true

Example 2:

Input: "()[]{}"
Output: true

Example 3:

Input: "(]"
Output: false
*/
class Solution {
    public boolean isValid(String s) {
        if(s == null || s.length() == 0)
            return true;
        
        Stack<Character> st = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '(' || c == '{' || c == '[')
                st.push(c);
            else{
                if(st.isEmpty())
                    return false;
                else if(c == ')' && st.peek() == '(') {
                    st.pop();
                    continue;
                }                    
                else if(c == '}' && st.peek() == '{') {
                    st.pop();
                    continue;
                }
                else if(c == ']' && st.peek() == '[') {
                    st.pop();
                    continue;
                }
                else
                    return false;                
            }
        }
        
        return st.isEmpty();//because stack can just be opening brackets
    }
}
