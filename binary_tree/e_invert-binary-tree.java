/* easy
nvert Binary Tree
DescriptionHintsSubmissionsDiscussSolution

Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9

Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
*/
/*
Pre order and post order both possible
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
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;
        
        helper2(root);
        return root;
    }
    
    //pre
    private void helper(TreeNode r){
        if(r == null)
            return;
        //process curr
        TreeNode temp = r.left;
        r.left = r.right;
        r.right = temp;
        
        helper(r.left);
        helper(r.right);
    }
    
    //post
    private TreeNode helper2(TreeNode r){
        if(r == null)
            return r;
        
        TreeNode left = helper2(r.left);
        TreeNode right = helper2(r.right);
        
        r.left = right;
        r.right = left;
        
        return r;
    }
}
