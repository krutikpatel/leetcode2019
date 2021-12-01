/* easy
145. Binary Tree Postorder Traversal
DescriptionHintsSubmissionsDiscussSolution

Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]

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
    public List<Integer> postorderTraversalRecusrsive(TreeNode root) {
        if(root == null)
            return new ArrayList<Integer>();
        List<Integer> l = new ArrayList<Integer>();
        
        //left
        l.addAll(postorderTraversal(root.left));
        //right
        l.addAll(postorderTraversal(root.right));
        //curr
        l.add(root.val);
        return l;
    }
    
    /*
    
    Ref:
    https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45551/Preorder-Inorder-and-Postorder-Iteratively-Summarization
    
    we know stack can help print pre-order output.
    we just want that BUT in reverse fashion. so 
    1. use Q to add nodes, but for reverse order, use q.addFirst()
    2. because, we want to print each level left to right,
        instead of s.push left and then right, push right and then left
    */
    public List<Integer> postorderTraversal(TreeNode root) {
    
        LinkedList<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        TreeNode p = root;
        while(!stack.isEmpty() || p != null) {
            
            while(p != null) {
                stack.push(p);
                result.addFirst(p.val);  // Reverse the process of preorder
                p = p.right;             // Reverse the process of preorder
            } 

            TreeNode node = stack.pop();
            p = node.left;           // Reverse the process of preorder

        }
        return result;
    }
    
    /*
    ref in-order loop
            while(p != null){
                s.push(n);
                n = n.left;
            }
            
            n = s.pop(); // take out last elem
            ret.add(n.val);
            
            //if(n.right != null)
            //    s.push(n.right);
            n = n.right;    //dont oush here, push only happens in first while loop.
            
            
    */
}
