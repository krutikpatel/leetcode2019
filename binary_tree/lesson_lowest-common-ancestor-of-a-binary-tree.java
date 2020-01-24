/*
236. Lowest Common Ancestor of a Binary Tree
DescriptionHintsSubmissionsDiscussSolution

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //if root is p or q, that is LCA
        if( root == p || root == q || root == null)
            return root;
        
        TreeNode left = lowestCommonAncestor( root.left,  p,  q);
        TreeNode right = lowestCommonAncestor( root.right,  p,  q);
                
        if(left == null){
            //both not found under left so, both are under right
            return right;
        }
        else if (right == null){
            //both not found under right so, both are under left
            return left;
        }            
        else {
            //both left and right are non-null, meaning this is ancestor
            return root;
        }                
    }
}
