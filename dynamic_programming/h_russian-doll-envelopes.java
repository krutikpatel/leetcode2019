/*
354. Russian Doll Envelopes
DescriptionHintsSubmissionsDiscussSolution

You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3 
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

*/
class Solution {
    /*
        Sort by width,
        if width same, sort by height : SO THAT WE CAN HAVE LONGEST INCREASING SUBARRRAY (which is our ans)
    */
    /*
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
                
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
           } 
        });
        
        //find longest increasing sub-array
        //we need DP for that
        int dp[] = new int[envelopes.length];
        int len = 0;
        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if(index < 0)
                index = -(index + 1);
            dp[index] = envelope[1];
            if(index == len)
                len++;
        }
        return len;    
    }
    */
        
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null) 
            return 0;
        if (envelopes.length <= 1) 
            return envelopes.length;
        
        int max = 1, n = envelopes.length;      
        
        Arrays.sort(envelopes, (x, y) -> Integer.compare(y[0]*y[1], x[0]*x[1])); // sort by area
        
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            dp[i] = 1;
            int[] inner = envelopes[i];
            for(int j = 0; j < i; j++) {
                int[] outer = envelopes[j];
                if (outer[0] > inner[0] && outer[1] > inner[1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }       
        return max;
    }    
}
