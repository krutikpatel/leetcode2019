/* easy
Closest Binary Search Tree Value
DescriptionHintsSubmissionsDiscussSolution
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
Note:
    Given target value is a floating point.
    You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:
Input: root = [4,2,5,1,3], target = 3.714286
    4
   / \
  2   5
 / \
1   3
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
    /*
    Think as Binary Search - Because its BST ! :)
    
    -curr node is pivoting factor to decide whether to look left of right, because its BST.
    if(target > currVal)
        go right - because either ans is root or something smaller on right side. everything on left subtree is bigger
    else
        go left
    
    Note: 
    its easy to find closest difference, but more convoluted to find that node.
    */
    public int closestValue(TreeNode root, double target) {
        if(root == null)
            return Integer.MAX_VALUE;
        
        int ret = root.val;        
        
        while(root != null){
            //if root diff is smaller, update ret
            if(Math.abs(target - root.val) < Math.abs(target - ret)){
                ret = root.val;
            } 
            
            //safe to compare double and int
            if(target > root.val){
                //search right tree
                root = root.right;
            }else{
                //left subtree
                root = root.left;
            }
        }
        
        return ret;
    }
    
}
