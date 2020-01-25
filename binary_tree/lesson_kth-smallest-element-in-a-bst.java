/*
230. Kth Smallest Element in a BST
DescriptionHintsSubmissionsDiscussSolution

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
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
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack();
        if(root == null)
            return Integer.MIN_VALUE;
/*                
        TreeNode n = root;
        while(n != null || s.size()>0){
            while(n!= null){
                s.push(n);
                n = n.left;
            }
            
            n = s.pop();
            k -= 1;
            if(k==0)
                return n.val;
            
            n = n.right;
        }
        
        return Integer.MIN_VALUE;
*/
        int[] count = {0,Integer.MIN_VALUE};
        helper(root,k,count);
        return count[1];
    }
    
    /*
    without class elem, use array object to store your class variables
    */
    private void helper(TreeNode root, int k, int[] count) {
        if(root == null)
            return;//Integer.MIN_VALUE;
        
        helper(root.left, k, count);
        
        count[0]++;
        if(count[0] == k){
            count[1] = root.val;
            return;
        }
        helper(root.right, k, count);
        
    }
}
