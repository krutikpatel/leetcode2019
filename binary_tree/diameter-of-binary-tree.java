/*
543. Diameter of Binary Tree
DescriptionHintsSubmissionsDiscussSolution

Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree

          1
         / \
        2   3
       / \     
      4   5    

Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3]. 
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
DFS - post order tarversal
time complexity O(n)
*/
class Solution {    
    
    int max = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }
    
    /*
    max depth that we want to compute/return for calculating diameter, jusrt happens to be "height" of that subtree.
    So method here is same as getHeight, but we use local results to calculate diameter and store in class var
    */
    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        max = Math.max(max, left + right);
        
        //pick bigger side for future calculation
        return Math.max(left, right) + 1;
    }
}
