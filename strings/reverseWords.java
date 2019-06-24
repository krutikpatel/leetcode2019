/*
Reverse Words in a String
https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1164

Given an input string, reverse the string word by word. 

Example 1:

Input: "the sky is blue"
Output: "blue is sky the"

Example 2:

Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:

Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

 

Note:

    A word is defined as a sequence of non-space characters.
    Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
    You need to reduce multiple spaces between two words to a single space in the reversed string.

*/
class Solution {

    //This one is relatively slower
    public String reverseWords(String s) {
        /*
        Two pointers"
        -settle i at beginning of word (skip all spaces)
        -begin j from i till space or end of string, and isolate the word.
        
        -add this word on stack.
        -At end form new string from stack
        */
        if(s == null || s.length() == 0)
            return s;
        
        int i=0;
        int j=0;
        
        Stack<String> ss = new Stack();
        
        while(i<s.length()){
            if(s.charAt(i)==' '){
                i++;
            }else{
                //now get the word
                j=i+1;
                while(j<s.length() && s.charAt(j) != ' '){
                    j++;
                }
                //not get that word and put on stack
                String word = s.substring(i,j);
                System.out.println(word);
                ss.push(word);
                
                               //modify i
                i = j;
            }
        }
        
        //now make new string
        StringBuilder sb = new StringBuilder();
        int size = ss.size();
        for(int a = 0;a<size;a++){
            sb.append(ss.pop());
            if(a+1 != size)
                sb.append(" ");
        }
            
        return sb.toString();
    }
}
