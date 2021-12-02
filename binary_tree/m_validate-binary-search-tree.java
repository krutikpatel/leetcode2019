/* medium
98. Validate Binary Search Tree
DescriptionHintsSubmissionsDiscussSolution

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.

 

Example 1:

    2
   / \
  1   3

Input: [2,1,3]
Output: true

*/
/*
lesson - good way to specify low and high ranges, is node objects themselves,
initially u can pass null and avoid check.
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
    public boolean isValidBST(TreeNode root) {
        //return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        
        //Note, dont use MIN and MAX, they fail if node val is min or max        
        return helper(root, null, null);
    }
    
    //DFS with top-down/In-order processing
    private boolean helper(TreeNode n, TreeNode low, TreeNode high){
        if(n == null)
            return true;
        
        if(low != null && n.val <= low.val) return false;
        if(high != null && n.val >= high.val) return false;
        
        boolean l = helper(n.left, low, n);
        boolean r = helper(n.right, n, high);
        
        return l && r;
    }
}
