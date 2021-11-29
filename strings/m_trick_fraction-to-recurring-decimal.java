/* medium
166. Fraction to Recurring Decimal
DescriptionHintsSubmissionsDiscussSolution

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"

Example 2:

Input: numerator = 2, denominator = 1
Output: "2"

Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"

*/
/*
Approach:
-explained well by diagram in leetcode.
		-> main trick : if remainder starts repeating, we know its repeating and finish
		-> we need to know where the repeating part starts. index of each digit in fraction -> do it by hashMap<Long, index>
		-> we use Long for all calculations to cover int overflows 
		-> how do we keep reducing remainder? -> this is just plain division operation.
			long remainder = dividend % divisor;
			-> while(ramainder ! = 0){
				ramainder *= 10; --> whi mult by 10? that is how we proceed manunal division
				remainder %= divisor;
			}
	
Edgecases:
0/1
1/0
-1/-1
-1/1
-ve and +ve int overflows
*/
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder fraction = new StringBuilder();

        // If either one is negative (not both)
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }

        // Convert to Long or else abs(-2147483648) overflows
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));

        fraction.append(String.valueOf(dividend / divisor));
        long remainder = dividend % divisor;
        if (remainder == 0) {
            return fraction.toString();
        }
        
        fraction.append(".");
        Map<Long, Integer> map = new HashMap<>();//fraction digit and its index
        while (remainder != 0) {
            //we have seen this digit in fraction/result before, so its repeating now
            if (map.containsKey(remainder)) {
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            
            map.put(remainder, fraction.length());
            
            //division process
            remainder *= 10;
            fraction.append(String.valueOf(remainder / divisor));
            remainder %= divisor;
        }
        return fraction.toString();
    }
    
///////////////////
    public String fractionToDecimalOld(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        
        // integral part
        res.append(num / den);
        
        //imp
        num %= den;
        if (num == 0) {
            return res.toString();
        }
        
        // remainder is non-zero. 1/3 remainder is 1. fractional part
        res.append(".");
        
        //numerator or num we have seen, and its index in stringBuilder
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(num, res.length());
        
        while (num != 0) {
            //make it bigger so that we can divide
            num *= 10;
            
            //save result in String
            res.append(num / den);
            
            num %= den;
            
            //if we have seen this number, insert ( before that num
            if (map.containsKey(num)) {
                int index = map.get(num);
                res.insert(index, "(");//sb provide "insert"
                res.append(")");
                break;
            }
            else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    
    }
}
