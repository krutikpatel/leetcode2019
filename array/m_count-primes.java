/* Medium
trick

204. Count Primes
DescriptionHintsSubmissionsDiscussSolution

Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

*/
class Solution {
    /*
    -maintain bool[] to mark prime/non-prime
    -any multiple of prime num is non-prime, so keep markign those
    */
    public int countPrimes(int n) {
        if(n==1)
            return 0;
        
        //array of non-prime because we want to mark non-prime = true.
        boolean[] nonPrime = new boolean[n+1];        
        
        int ret=0;
        for(int i=2;i<n;i++) {
            if(nonPrime[i] == false) {
                ret++;
                
                //mark all multiple false
                for(int j=2;(i*j)<=n;j++){
                    nonPrime[i*j] = true;
                }
            }
        }
        
        return ret;
    }
}
