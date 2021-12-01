/* easy
Lowest Common Ancestor of a Binary Search Tree - good BST property lesson

Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]

Example 1:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6
*/
/*
Leverage the fact that its BST
-if it was just normal BT, we would need to figure out exactly where each node/val is
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
        
        int parent = root.val;
        int pval = p.val;
        int qval = q.val;
        
        if(pval < parent && qval < parent){
            //go left
            return lowestCommonAncestor(root.left, p,q);
        }else if(pval > parent && qval > parent){
            //go right
            return lowestCommonAncestor(root.right, p,q);
        } else{
            return root;
        }
    }
}
