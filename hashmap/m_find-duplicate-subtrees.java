/* medium
Find Duplicate Subtrees

Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4

The following are two duplicate subtrees:

      2
     /
    4

and

    4

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
    Map<String, Integer> count;
    List<TreeNode> ans;
    /*
    Store string representation of each node in map. serialized
    If String key occurs second time in map, add that node to ans list
    
    recursive call is O(n square)
    */
        
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        count = new HashMap();
        ans = new ArrayList();
        
        collect(root);
        
        return ans;
    }

    /*
    pre-order recursive calls : root - node paths
    */
    public String collect(TreeNode node) {
        if (node == null) 
            return "#";
        String serial = node.val + "," + collect(node.left) + "," + collect(node.right);
        
        count.put(serial, count.getOrDefault(serial, 0) + 1);
        
        if (count.get(serial) == 2)
            ans.add(node);
        
        return serial;
    }
}
