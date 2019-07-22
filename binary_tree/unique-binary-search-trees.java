/*
Unique Binary Search Trees
DescriptionHintsSubmissionsDiscussSolution

Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/
/*
Trick Memoization/DP question
*/
class Solution {
    public int numTrees(int n) {
        int[] memo = new int[n+1];
        memo[0] = 1; //null node
        memo[1] = 1;// one node
        
        //for each i, find its memo/solution
        for(int i=2;i<=n;i++){
            //sol(i) = sum of solutions found with taking root at each val(0 to i)
            for(int j=1;j<=i;j++){  //note: we began from 1
                    //F(i, n) = G(i-1) * G(n-i)	1 <= i <= n 
                memo[i] += memo[j-1] * memo[i-j];   //reason for i-j : ways to make right subtree for no. of nodes i-j. 
                                                    //does not matter what they are as long as they are sorted-in-order. 
                                                    //it comes from observation that its same as solution for right bustree-> j+1 -> i
                                                    //no subtrees for (1,2) nodes is same as no of subtrees for (2,3)
            }
        }
        
        return memo[n];
    }
}
