/* Medium
274. H-Index
DescriptionHintsSubmissionsDiscussSolution

Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

Example:

Input: citations = [3,0,6,1,5]
Output: 3 
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had 
             received 3, 0, 6, 1, 5 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, her h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

*/
class Solution {
    public int hIndex(int[] citations) {
        /*
        int[] c = citations;
        int n = c.length;
        if(c == null || n == 0)
            return 0;
        
        Arrays.sort(c);
        int ret = 1;//arr size = 1, ans is 1 if A[0] = 0
        for(int i=0;i<c.length;i++){
            if(c[i] == n-i){
                ret = c[i];
            }
        }
        return ret;
        */
        
        int n = citations.length;
        //h can never be > total no of papers
        int[] buckets = new int[n+1];
        
        for(int c : citations) {
            
            //c >=n goes in last bucket
            if(c >= n) {
                buckets[n]++;
            } else {
                buckets[c]++;
            }
        }
        
        //since we want greater/max index, we start from last. count all papers
        int count = 0;
        for(int i = n; i >= 0; i--) {
            count += buckets[i];
            if(count >= i) {
                return i;
            }
        }
        return 0;
    }
}
