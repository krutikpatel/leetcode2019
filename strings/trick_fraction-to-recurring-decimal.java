/*
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
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
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
