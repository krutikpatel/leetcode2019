/*
199. Binary Tree Right Side View
DescriptionHintsSubmissionsDiscussSolution

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

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
    last elems form each level
    
    
Observaition : right side is nothing but last elem of each level
gotcha: trying ti find right edge will not work. se following case:
    1
  2   4
 3 
 
 right edge is : 1,4
 right view is : 1,4,3

    
    */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList();
        if(root == null)
            return ret;
        //helper(root,ret);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int len = q.size();
                        
            for(int i=0;i<len;i++){
                TreeNode curr = q.poll();
                if(i==len-1) {
                    ret.add(curr.val);
                }
                if(curr.left!=null)
                    q.offer(curr.left);
                if(curr.right!=null)
                    q.offer(curr.right);
            }
        }
        
        return ret;
    }
    
    private void helper(TreeNode root, List<Integer> ret) {
        if(root == null) {
            return;
        }
        ret.add(root.val);
        //ideally go right, but it right null go left
        if(root.right != null) {
            helper(root.right,ret);
        }else {
            helper(root.left,ret);
        }
    }
}
