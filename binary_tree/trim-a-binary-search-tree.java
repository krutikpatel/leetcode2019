/*
Trim a Binary Search Tree

Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

Example 1:

Input: 
    1
   / \
  0   2

  L = 1
  R = 2

Output: 
    1
      \
       2

Example 2:

Input: 
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output: 
      3
     / 
   2   
  /
 1
*/
/*
Leverage BST property
-based on range we can ignore out of lower bound left and out of upper bound right nodes/subtrees
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
    public TreeNode trimBST(TreeNode root, int L, int R) {        
        if(root == null)
            return root;
        
        if(root.val < L){
            //go right, since BST, whole left side can be ignored
            return trimBST(root.right, L, R);
        } else if(root.val > R){
            //go left, whole right side can be ignored
            return trimBST(root.left, L, R);
        } else {
            //no need to create new node, this node is valid.
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        }       
    }
}
