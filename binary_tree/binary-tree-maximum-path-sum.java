/*
Binary Tree Maximum Path Sum

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6

Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfshelper(root);
        return res;
    }
    
    private int dfshelper(TreeNode n){
        if(n == null)
            return 0;
        
        //important to take 0 if return val is negative, so that currPath does not become less due to -ve children
        int l = Math.max(dfshelper(n.left),0);
        int r = Math.max(dfshelper(n.right),0);
        
        int currPath = n.val + Math.max(l,r);
        int currTotal = n.val + l + r;
        if(currTotal > res){
            res = currTotal;
        }
        
        return currPath;
    }
}
