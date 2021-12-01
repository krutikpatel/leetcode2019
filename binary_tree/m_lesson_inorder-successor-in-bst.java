/* medium
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
    /*
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
    */
        
    /*
    Leverage BST property.
    iteration with conditions suites binary search, deciding whether go left or right
    
    /*
    Leverage BST property.
    iteration with conditions suites binary search, deciding whether go left or right
    
    Algo
    -We start from the root, utilizing the property of BST:

    -If current node's value is less than or equal to p's value, we know our answer must be in the right subtree.
    -If current node's value is greater than p's value, current node is a candidate. Go to its left subtree to see if we can find a smaller one.
    -If we reach null, our search is over, just return the candidate.

    */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode target) {
        
        TreeNode ret = null;
        
        //just traverse top to bottom in right direction.
        while(root!=null){
            if(target.val >= root.val){                            
                //go right
                root = root.right;
            }else{
                
                ret = root; // why ????? anytime you go left, save the node first (as parent/bigger elem), because left of it might be null, so parent becomes successor
                //go left
                root = root.left;
            }
        }
        
        return ret;
    }
    
}
