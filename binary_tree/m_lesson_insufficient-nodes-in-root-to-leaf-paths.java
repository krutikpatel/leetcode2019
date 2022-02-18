/*
1080. Insufficient Nodes in Root to Leaf Paths
DescriptionHintsSubmissionsDiscussSolution

Given the root of a binary tree, consider all root to leaf paths: paths from the root to any leaf.  (A leaf is a node with no children.)

A node is insufficient if every such root to leaf path intersecting this node has sum strictly less than limit.

Delete all insufficient nodes simultaneously, and return the root of the resulting binary tree.

 

Example 1:


Input: root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1

Output: [1,2,3,4,null,null,7,8,9,null,14]

Example 2:


Input: root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22

Output: [5,4,8,11,null,17,4,7,null,null,null,5]

 

Example 3:


Input: root = [1,2,-3,-5,null,4,null], limit = -1

Output: [1,null,-3,4]

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
    lesson:
    systematic think of what info needs to pass down, and what info needs to come back up
    top-down - passs sum
    bottom-up - if node to be removed - null if removed, node if not.. -> we make links again while coming up
    
    Also, IMP to note that we do : DFS in POST order traversal way. ie, we go to leaf first, we call left and right first,
    then we can decide whether a parent is sufficient or not
    */
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if(root == null)
            return null;
        return helper(root,limit,0);
    }
    
    private TreeNode helper(TreeNode root, int limit, int sofar) {
        if(root == null)
            return null;
        sofar+=root.val;
        
        //we return from leaf
        if(root.left == null && root.right == null) {            
            if(sofar < limit)
                return null;
            else 
                return root;//we still need to make this connection, because links are made while coming up
        }
        
        /* does not work if limit to begin with is greater. we really need to find sum till leaf
        if(sofar < limit)
            return null;
        */
        root.left = helper(root.left, limit,sofar);
        root.right = helper(root.right, limit,sofar);
        
        //as mentioned in question, if all router through this node are null/insufficient, this is also null
        if(root.left == null && root.right == null)
            return null;
        return root;
    }
}
