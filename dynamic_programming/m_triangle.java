/*
Triangle

Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]

The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
*/
class Solution {
    /*
    There is flaw in top -down parsing in triangle (imagine negative nums on bottom rows which are not adjacent)
    So, need to start from bottom row.
    */
    public int minimumTotal(List<List<Integer>> triangle) {
        /*
        memo will record min sum soFar for that indexed elem. 
        We reuse same arr for each level, because we just want to store sum in arr till last level
        
        -as we move up the triangle, solution converges on memo[0] when we reach top of triangle !
        */
        int[] memo = new int[triangle.size()];
        
        /*
        initialize memo with know soln
        -base/know is: last row as it is
            -start from last row.
		    -for last row we know soln, ans is node itself (base case each node is the only node in triangle)
        */
        List<Integer> lastList = triangle.get(triangle.size()-1);
        for(int i=0;i<lastList.size();i++){
            memo[i] = lastList.get(i);
        }
        
        /*
            -going bottom up in triangle            
        */
        for(int i=triangle.size()-2;i>=0;i--){
            List<Integer> l = triangle.get(i);
            /*               
            //check each node and see what sum is using each node and record min, for this level,
                we try to fill dp array with min sum soFar for that index that level, so for upper layer we can use it to make optimum choice
                for each j in curr List, we pick min of neighbors from row/list below it. 
                Memo becomes sum at that index till this row
                
                -At this point, dp[] stores ands till level i-1 (level below i)
                -and with this iteration, we replace values with ans of curr level
            */            
            for(int j=0; j<l.size(); j++) {                
                //System.out.println(Arrays.toString(memo));
                
                /*
                dp[j] = list.get(j) ==>curr elem + lowest of j's neighbors from level below 
				lower neighbor indexes for j are : j and j+1 (see visually in triangle)
                */
                memo[j] = l.get(j) + Math.min(memo[j], memo[j+1]);
                
                //System.out.println(Arrays.toString(memo));
                //System.out.println("==");
            }
            //System.out.println("____");            
        }
        
        //because we record sum at each iteration, last iteration is for list size1, so 0th node has min sum we found
        return memo[0];
    }
}
