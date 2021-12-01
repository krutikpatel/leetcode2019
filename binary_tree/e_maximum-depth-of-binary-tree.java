/* easy
https://leetcode.com/problems/maximum-depth-of-binary-tree/description/

try to solve iterativesly and recursively
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
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        int levels = 0;
        while(!q.isEmpty()){
            int qsize = q.size();
            while(qsize > 0){
                TreeNode curr = q.poll();
                if(curr.left!=null)
                    q.offer(curr.left);
                if(curr.right!=null)
                    q.offer(curr.right);
                
                qsize--;
            }
            levels++;//one level done
        }
        return levels;
    }
}
