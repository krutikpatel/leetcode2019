/*
129. Sum Root to Leaf Numbers
DescriptionHintsSubmissionsDiscussSolution

Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
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
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        if(root == null)
            return sum;
        //dfs(root, new ArrayList<Integer>());
        
        sumNumberUtil(root,root.val);
        return sum;
    }
    
    private void dfs(TreeNode n, ArrayList<Integer> sofar) {
        if(n == null)
            return;
        
        sofar.add(n.val);
        if(n.left == null && n.right == null) {
            int currsum = 0;
            for(int i:sofar) {
                currsum = currsum*10 + i;
            }
            sum += currsum;
        }
        
        dfs(n.left,sofar);
        dfs(n.right,sofar);
        
        //remove last elem, backtrack
        sofar.remove(sofar.size()-1);
    }
    
    ////
    private void sumNumberUtil(TreeNode root, int currentSum){
        
        if(root == null)
            return;
        
        if(root.left == null && root.right == null){
            sum += currentSum;
        }
        if(root.left != null){
            int temp = currentSum*10 + root.left.val;
            sumNumberUtil(root.left, temp);
        }
        if(root.right != null){
            int temp = currentSum*10 + root.right.val;
           sumNumberUtil(root.right, temp);
        }
    }
    
}
