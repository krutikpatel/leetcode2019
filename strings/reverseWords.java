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
    /*
    may be for faster approach :
    https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1164/discuss/47797/In-place-Java-solution-with-comments-just-for-fun
    
    -reverse the whole string, and then take word by word and reverse them
    
     Note:
        solution wihtour using stack is fast.
        -reverse whole string
        -then reverse each word.
    */
    public String reverseWords(String s) {
        int i=0;
        int j = s.length()-1;
        
        //trim from left
        while(i<=j && s.charAt(i)==' ')
            i++;
        s = s.substring(i);
        
        //trim from right
        j = s.length()-1;
        while(j>=0 && s.charAt(j)==' ')
            j--;
        s = s.substring(0,j+1);

        //reverse s 
        s = reverse(s);
        
        //reverse words, ocnsider multiple spaces, so in case of space move one by one
        i=0;
        j=0;
        StringBuilder sb = new StringBuilder();
        while(i<s.length()){
            if(s.charAt(i) == ' ')
                i++;
            else {
                
                //now get the word
                j=i;
                while(j<s.length() && s.charAt(j) != ' '){
                    j++;
                }
                //not get that word
                String word = s.substring(i,j);
                
                if(sb.length()!=0)
                    sb.append(" ");
                sb.append(reverse(word));

                j++;
                i=j;                
            }
        }
        
        return sb.toString();
    }
    
    //private void reverse(char[] c, int i, int j){
    private String reverse(String s){
        char[] c = s.toCharArray();
        int i = 0;
        int j = s.length()-1;
        while(i<j){
            char temp = c[i];
            c[i] = c[j];
            c[j] = temp;
            i++;
            j--;
        }
        return new String(c);
    }
    public String reverseWords2(String s) {
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
            if(s.charAt(i)==' '){ //This is cleaner way to do loop. rather than introducing another while loop 
                                  //to skip all spaces for i, just do iteration by iteraiton of outer loop of i
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
