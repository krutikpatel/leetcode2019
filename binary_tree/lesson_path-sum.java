/*
Path Sum
DescriptionHintsSubmissionsDiscussSolution

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1

return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

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
    /*
    Key:
    decrementing from sum, avoids passing around sum sofar
    
    But, both ways can work as seen below
    */
    int sum;
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;                
        }
        
        sum -= root.val;
        if(sum == 0 && root.left == null && root.right == null)
            return true;
        
        return hasPathSum(root.left,sum) || hasPathSum(root.right,sum);
        
        /*
        return helper(root, sum, 0);
        */
    }
    
    private boolean helper(TreeNode root, int sum, int sofar) {
        if(root == null){
            return false;                
        }
        
        sofar += root.val;
        if(sum == sofar && root.left == null && root.right == null)
            return true;
        
        return helper(root.left,sum, sofar) || helper(root.right,sum, sofar);
    }
}
