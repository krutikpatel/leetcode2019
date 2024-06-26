/* medium
227. Basic Calculator II
DescriptionHintsSubmissionsDiscussSolution

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7

Example 2:

Input: " 3/2 "
Output: 1

Example 3:

Input: " 3+5 / 2 "
Output: 5

*/
class Solution {
    public int calculate(String s) {
        int len;
        if(s==null || (len = s.length())==0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        
        int num = 0;
        char sign = '+';
        for(int i=0;i<len;i++){
            if(Character.isDigit(s.charAt(i))){
                num = num*10+s.charAt(i)-'0';
            } else {
                //if not digit, AND its not space OR its last char. if its last char, the whole num we need to put in stack
                if(s.charAt(i) != ' ') || i==len-1){
                    //process last sign and generate one operand, because we have new sign now
                    //approach : evaluate * and / in the loop (BECAUSE THEY HAVE HIGHER PRECEDENCE), then what remians is just series of +ve and -ve numbers to add-up
                    if(sign=='-'){
                        stack.push(-num);
                    }
                    if(sign=='+'){
                        stack.push(num);
                    }
                    if(sign=='*'){
                        stack.push(stack.pop()*num);
                    }
                    if(sign=='/'){
                        stack.push(stack.pop()/num);
                    }

                    //take new sign for next iteration
                    sign = s.charAt(i);
                    num = 0;
                }
            }
        }

        int re = 0;
        for(int i:stack){
            re += i;
        }
        return re;
    }
}
