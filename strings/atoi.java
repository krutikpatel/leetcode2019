/*
String to Integer (atoi)

 Take care of following constraints:
    0. remove spaces from front and back - trim
    1. sign in front - may or may not be present
    2. Integer overflow min and max. Store temp result on "long"
    3. look for non digit chars. once encounter, break/stop
    
*/
class Solution {
    /*
    Take care of following constraints:
    0. remove spaces from front and back - trim
    1. sign in front - may or may not be present
    2. Integer overflow min and max. Store temp result on "long"
    3. look for non digit chars. once encounter, break/stop
    */
    public int myAtoi(String str) {
        if (str == null || str.length() == 0)
            return 0;
        
        //remove all spaces
        str = str.trim();
            
        //trim might have made string empty if orig string wasa "   "
        if (str.length() == 0)
            return 0;
        
        int sign = 1, start = 0, len = str.length();
        
        //TRick: storing result in long, to cover overflows
        long sum = 0;
        
        //cehck and get sign. may or may not have sign
        char firstChar = str.charAt(0);
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }
        
        //Notice: we dont have to start from left side. because we will keep multiplying result by 10 each time
        for (int i = start; i < len; i++) {
            //stop when u see non digits
            if (!Character.isDigit(str.charAt(i)))
                //return (int) sum * sign;
                break;
            
            sum = sum * 10 + str.charAt(i) - '0';
            
            //take care of overflow
            if (sign == 1 && sum > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (sign == -1 && (-1) * sum < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
        }

        return (int) sum * sign;

    }
}
