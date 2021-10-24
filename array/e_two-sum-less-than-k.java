/* Easy
1099. Two Sum Less Than K
DescriptionHintsSubmissionsDiscussSolution

Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.

 

Example 1:

Input: A = [34,23,1,24,75,33,54,8], K = 60
Output: 58
Explanation: 
We can use 34 and 24 to sum 58 which is less than 60.

Example 2:

Input: A = [10,20,30], K = 15
Output: -1
Explanation: 
In this case it's not possible to get a pair sum less that 15.

*/
class Solution {
    /*
    lesson - simple binary move like 3 sum problem. NOT binary search which involves mid
    Sort array.
    -for each a, find K-x 
    since sorted we dont have to do binary search using mid.
    we can squeeze in binary fashion - like 3 sum problem
    
    Note: we dont want eaxct K sum even if available, so rule it out during binary move
    */
    public int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A);

        int max = -1;
        
        int l = 0;
        int r = A.length-1;
        while(l<r){
            int curr = A[l] + A[r];
            
            if(curr < K) {
                max = Math.max(max,curr);
                l++;
            }else {
                //equal or greaeter moved out
                r--;
            }
        }
        
        return max;
        /*
        for(int i =0;i<A.length-1;i++){
            int a = A[i];
            int target = K-a;// find target or closest smaller val
            
            int left = i+1;
            int right = A.length-1;
            while(left<=right) {
                int mid = left+(right-left)/2;
                if(A[mid] == target)
                    return K;
                if(A[mid] > target) {
                    //go left
                    right = mid-1;
                } else {
                    left = mid+1;
                }                    
            }
            if(A[left] + a < K) {
                min = Math.min(min,A[left] + a);
            }
        }
        
        if(min == Integer.MAX_VALUE){
            return -1;
        } else {
            return min;
        } 
        */
    }
}
