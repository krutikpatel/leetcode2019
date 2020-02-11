/*
572. Subtree of Another Tree
DescriptionHintsSubmissionsDiscussSolution

Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2

Given tree t:

   4 
  / \
 1   2

Return true, because t has the same structure and node values with a subtree of s.

Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0

Given tree t:

   4
  / \
 1   2

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
    node by node comparision
    -just go all directions - bruteforce
    */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isSame(s, t)) 
            return true;
        
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        
        if (s.val != t.val) 
            return false;
        
        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }
    
    /*
    converting tree to string
    IMP - order matters. inorder will not work.
    ONLY preorder will work. its node by node comarision from top.
    ALSO: we need to count null leafs in string too, to avoid wrong comparisions, which gives false true.
        -to avoid returning true for example 2 above
    */
    public boolean isSubtreeNeedsWork(TreeNode s, TreeNode t) {
        if (s == null) return false;
        
        String ss = inorder(s);
        String tt = inorder(t);
        
        System.out.println(ss);
        System.out.println(tt);
        if(ss.length() >= tt.length()){
            return ss.indexOf(tt) >= 0 ? true : false;
        } else {
            return tt.indexOf(ss) >= 0 ? true : false;
        }
    }
    
    private String inorder(TreeNode n) {
        if(n==null)
            return "";
        String l = Integer.toString(n.val);
         l = l +inorder(n.left);        
        String r = inorder(n.right);
        return l+r;
    }
}
