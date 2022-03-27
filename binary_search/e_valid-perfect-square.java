/* easy
Valid Perfect Square

Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Output: true

Example 2:

Input: 14
Output: false
*/
class Solution {
    /*
    We have to be careful of integer overflow.
    We also have to be careful of int-float. 5/2 is 2 which is not desired.
    
    Use long, thus avoid division operation at all.
    There is no problem of int and long comparision.
    */
    public boolean isPerfectSquare(int num) {
        long l = 1;
        long r = num/2;
        
        while(l<r){
            long mid = l + (r-l)/2;
            long sq = mid * mid;
            if(num == sq){
                return true;
            } else if(num < sq){
                //go left
                r = mid;
            } else {
                l = mid +1;
            }
        }
        
        // for 1 and 4 we need this check. when r itself is square-root of num. at end of above loop, l=num/2
        if(num == l * l)
            return true;
        else
            return false;
    }
}
