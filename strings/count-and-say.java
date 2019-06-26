/*
Count and Say

The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.
*/

class Solution {
    public String countAndSay(int n) {
        if(n==0)
            return "";
        
        String prev = "1";
        for(int i = 2;i<=n;i++){
            //form curr String using prev
            StringBuilder sbCurr = new StringBuilder();
            
            for(int j = 0;j<prev.length();){
                //get count for this char
                char digit = prev.charAt(j);
                int count = 1;
                
                int k=j+1;
                while(k<prev.length() && digit == prev.charAt(k)){  //Note: realize the convenience of while loop in such situation rather than for loop
                    count++;
                    k++;
                }
                
                j = k; //IMP j will be incremented here
                
                //now make string
                sbCurr.append(Integer.toString(count));
                sbCurr.append(digit);
            }
            
            //once done
            prev = sbCurr.toString();
            //System.out.println("i = "+ i + " and string = " + prev );
        }
        
        return prev;
    }
}
