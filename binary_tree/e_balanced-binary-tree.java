/* easy
 Balanced Binary Tree

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

    a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7

Return true.

Example 2:

Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4

Return false.
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
 /*
 Bottom up problem
 -need external var to store result
 */
/*
    Lesson
    Key:
    bottom-up OR post order way - helper method checks height AND imabalance
    
    using heightOfBTree() helper method,
    but using -1 as return value for non-balanced case.
    
    This avoids complexity to whether return boolean or height
    
    O(n) complexity, each node visited once, post order
    */
class Solution {
    
    public boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        
        if(height(root) == -1)
            return false;
        else
            return true;
    }
    
    private int height(TreeNode n){
        if(n == null)
            return 0;
        
        int l = height(n.left);
        int r = height(n.right);
        
        //if imbalance in children, this node also imbalanced
        if(l == -1 || r == -1)
            return -1;
    
        //check for tree imbalance
        if(Math.abs(l-r) > 1)
            return -1;
     
        //return height of this node
        return 1 + Math.max(l,r);
    }
}
