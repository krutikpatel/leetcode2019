/* medium
250. Count Univalue Subtrees
DescriptionHintsSubmissionsDiscussSolution

Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example :

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

Output: 4
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
    int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if(root == null)
            return 0;
        helper(root);
        return count;
    }
    
    /*
    its needs more/whole subtree data to see if its true/univalue
    BUT, to see that its NOT univalue tree, its easy, whenever node val diff from parent. so use that 
    */
    private boolean helper(TreeNode n){
        if(n == null)
            return true;
        
        boolean r = helper(n.right);
        boolean l = helper(n.left);
        if(r && l){
            //checking false condition, which is easy. whenever curr val and child val dont match
            if(n.right !=null && n.right.val != n.val)
                return false;
            if(n.left !=null && n.left.val != n.val)
                return false;
            
            count++;
            return true;
        }
        return false;
    }
}
