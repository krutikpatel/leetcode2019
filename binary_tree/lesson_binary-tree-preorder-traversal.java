/*
144. Binary Tree Preorder Traversal
DescriptionHintsSubmissionsDiscussSolution

Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]

Follow up: Recursive solution is trivial, could you do it iteratively?

*/
//Iterative solution - using stack
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList();
        if(root == null)
            return ret;
        
        Stack<TreeNode> s = new Stack();
        TreeNode n = root;
        /*
        while(n != null || s.size() > 0){
            while(n!=null){
                //process first.
                ret.add(n.val);
                s.push(n);
                n = n.left;                
            }
            n = s.pop();
            n = n.right;
        }
        */
        s.push(n);
        /*
        Adding nodes to stack exactly in the order that we want, visually.
        */
        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            if (node != null) {
                ret.add(node.val);
                s.push(node.right);
                s.push(node.left);
            }
        }
        return ret;
    }
}
