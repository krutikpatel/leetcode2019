/*
257. Binary Tree Paths
DescriptionHintsSubmissionsDiscussSolution

Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        dfs(root,ret,"");
        return ret;
    }
    private void dfs(TreeNode n, List<String> ret, String sofar) {
        if(n==null){
           //if you add sofar to ret list here, you will end up adding same sofar twice, at leaf, because this will be called for both left and right null children of leaf
            return;
        }
       
        sofar +=n.val+"->";
        if(n.left == null && n.right == null) {
            System.out.println(sofar);
            ret.add(sofar.substring(0,sofar.length()-2));
        }
        dfs(n.left,ret,sofar);
        dfs(n.right,ret,sofar);
        
        //Lesson: do not have to remove char from sofar, because it is String, 
        //and Java makes another copy rahter than maintaining ref for string
    }
}
