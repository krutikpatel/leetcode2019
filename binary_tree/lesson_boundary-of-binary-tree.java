/*
545. Boundary of Binary Tree
DescriptionHintsSubmissionsDiscussSolution

Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.  (The values of the nodes may still be duplicates.)

Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Example 1

Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Input:
    ____1_____
   /          \
  2            3
 / \          / 
4   5        6   
   / \      / \
  7   8    9  10  
       
Ouput:
[1,2,4,7,8,9,10,6,3]

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
    Boundary = dfs(left) + leafs + dfs(right)
    
    duplicates allowed:
    -right side is reverse order, so use stack ! simple
    -so, just avoid last elems of left and right edge, because leaves list will have it.
    -corners: no left side, no right side, only root
    
    */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if(root == null)
            return ret;
        if(root.left == null && root.right == null){
            ret.add(root.val);
            return ret;
        }
            
        List<Integer> left  = new ArrayList<>();
        getLeftEdge(root.left,left);
        
        //because we want right side in reverse order 
        Stack<Integer> right  = new Stack<>();
        getRightEdge(root.right,right);
        
        List<Integer> leaves  = new ArrayList<>();
        getLeaves(root,leaves);
        ret.add(root.val);
             
        //stitch left - skip last
        for(int i=0;i<left.size()-1;i++) {
            ret.add(left.get(i));
        }
                
        //leaves
        for(int i=0;i<leaves.size();i++) {
            ret.add(leaves.get(i));
        }
        
        //right
        //always remove first
        if(!right.isEmpty())
            right.pop();
        while(!right.isEmpty()){
            ret.add(right.pop());
        }
        
        return ret;
    }
    
    /*
    if left null go right - reverse order
    */
    private void getLeftEdge(TreeNode root, List<Integer> leftList) {
        if(root == null) {
            return;
        }
        leftList.add(root.val);
        if(root.left != null) {
            getLeftEdge(root.left, leftList);
        } else if(root.right != null) {
            getLeftEdge(root.right, leftList);
        }
    }
    
    /*
    if right null go left
    */
    private void getRightEdge(TreeNode root, Stack<Integer> rightList) {
        if(root == null) {
            return;
        }
        rightList.push(root.val);
        if(root.right != null) {
            getRightEdge(root.right, rightList);
        } else if(root.left != null) {
            getRightEdge(root.left, rightList);
        }
    }
    
    private void getLeaves(TreeNode root, List<Integer> leaves) {
        if(root == null)
            return;
        if(root.left == null && root.right == null) {
            leaves.add(root.val);
            return;
        }
        getLeaves(root.left, leaves);
        getLeaves(root.right, leaves);
    }
}
