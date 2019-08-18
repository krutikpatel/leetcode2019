/*
Sqrt(x)
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2

Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
*/
class Solution {
    public int mySqrt(int x) {
        if(x == 1 || x == 0)
            return x;
        
        int l = 1;
        int r = x/2;
        
        //while l<r
        //if mid square then good
        //if mid square not equal BUT mid+1 square > x, mid is ans.
        //be careful of overload for squares - use long. ==> this has some flaw and does not give correct result for overflows
        //another way around overflow is divide
        
        /* way -1
        while(l<=r){
            int mid = l + (r-l)/2;
            
            if(mid == x/mid)
                return mid;
            
            if(mid > x/mid){
                r = mid-1;    
            } else {                
                if((mid+1) > x/(mid+1))
                    return mid;
                else
                    l = mid+1;
            }
        }
        return -1;
        */
        
        //way -2
        while(l<r){
            int mid = l + (r-l)/2;
            
            if(mid == x/mid)
                return mid;
            
            if(mid < x/mid) {
                if((mid+1) > x/(mid+1))
                    return mid;
                else
                    l = mid+1;                
            } else {
                r = mid;
            }
        }
        
        return l;
    }
}
