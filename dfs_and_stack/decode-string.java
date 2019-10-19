/*
Decode String
DescriptionHintsSubmissionsDiscussSolution

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

*/
class Solution {
    
    //Note
    /*
    String before num to be used once - pop
    String after num - to be repeated - curr or res
    
    we use stack to cover brackets inside brackets
    
    -consider possiblity of multi-digit count (need multiple chars to make one integer)
    */
    public String decodeString(String s) {
        if(s==null || s.length()==0)
            return s;
        
        Stack<Integer> repeat_count = new Stack();
        Stack<String> repeat = new Stack();
        
        String res = "";//imp to maintain, there can be brackets inside brackets etc
        
        int i = 0;
        //process one char at a time
        while(i < s.length()){
            
            if(Character.isDigit(s.charAt(i))){
                //gather all nums
                int count = 0;
                while(i <s.length() && Character.isDigit(s.charAt(i))){
                    count = (count * 10) + (s.charAt(i) - '0');
                    i++;
                }
                repeat_count.push(count);
            
            } else if(s.charAt(i) == '['){  //store string whatever you have and begin new
                //store string so far
                repeat.push(res); //imp
                res = "";
                //System.out.println("pushed");
                i++;
                
            } else if (s.charAt(i) == ']'){
                //get repeat_count
                int count = repeat_count.pop();
                String ss = repeat.pop();
                
                StringBuilder temp = new StringBuilder(ss);//key
                
                for(int j = 0;j<count;j++){
                    temp.append(res);//key
                }
                res = temp.toString();
                
                i++;
            } else {
                //keep building curr string
                res+=s.charAt(i);        
                i++;
            }
        }
                      
        return res;
    }
    
    /*
    More readable
    
    Stack<Integer> intStack = new Stack<>();
    Stack<StringBuilder> strStack = new Stack<>();
    
    StringBuilder cur = new StringBuilder();
    int k = 0;
    
    for (char ch : s.toCharArray()) {
        if (Character.isDigit(ch)) {
            k = k * 10 + ch - '0';
        } else if ( ch == '[') {
            intStack.push(k);
            strStack.push(cur);
            cur = new StringBuilder();
            k = 0;
        } else if (ch == ']') {
            StringBuilder tmp = cur;
            cur = strStack.pop();
            for (k = intStack.pop(); k > 0; --k) cur.append(tmp);
        } else cur.append(ch);
    }
    return cur.toString();
        
    */
}
