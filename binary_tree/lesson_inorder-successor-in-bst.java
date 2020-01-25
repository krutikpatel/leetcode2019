/*
285. Inorder Successor in BST
DescriptionHintsSubmissionsDiscussSolution

Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

The successor of a node p is the node with the smallest key greater than p.val.

 

Example 1:

Input: root = [2,1,3], p = 1
Output: 2
Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.

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
    
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null)
            return null;
        if(root.val <= p.val){
            return inorderSuccessor(root.right, p);
        }else{
            TreeNode l = inorderSuccessor(root.left, p);
            //this might lead us to null after correct node so check
            if(l == null)
                return root; // leaf node
            else
                return l;
        }
    }
    
    public TreeNode inorderSuccessorItr(TreeNode root, TreeNode p) {
        
        TreeNode ret = null;
        
        //just traverse top to bottom in right direction.
        while(root!=null){
            if(root.val <= p.val){
                //ret = root;
                //go right
                root = root.right;
            }else{
                ret = root; // why ????? anytime you go left, save the node first, because left of it might be null
                //go left
                root = root.left;
            }
        }
        
        return ret;
    }
    
}
