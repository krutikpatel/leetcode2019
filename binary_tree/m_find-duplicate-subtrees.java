/* medium
652. Find Duplicate Subtrees
DescriptionHintsSubmissionsDiscussSolution

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
/*
representation of tree(segment) can be in terms os String with all nodes in some order (post order here)
-also include null nodes
-ONLY add dplicate trees to result once
*/
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> ret = new ArrayList<>();
        buildPathPostOrder(root, new HashMap<>(), ret);
        return ret;
    }
    
    private String buildPathPostOrder(TreeNode r, HashMap<String,Integer> map, List<TreeNode> ret) {
        if(r == null) {
            return "#";
        }
        
        String currPath = String.valueOf(r.val) + buildPathPostOrder(r.left,map,ret) + buildPathPostOrder(r.right,map,ret);
        if(map.containsKey(currPath))  {
            if(map.get(currPath) == 1){
                ret.add(r);
                map.put(currPath,2);
            }
        } else {
            map.put(currPath,1);
        }
        
        return currPath;
    }
}
