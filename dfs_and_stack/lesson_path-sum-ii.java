/*
113. Path Sum II
DescriptionHintsSubmissionsDiscussSolution

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1

Return:

[
   [5,4,11,2],
   [5,8,4,5]
]


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
    
    List<List<Integer>> ret;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ret = new ArrayList<>();
        dfsHelper(root,sum,0,new ArrayList<>());
        return ret;
    }
    
    private void dfsHelper(TreeNode root, int sum, int sofarSum, List<Integer> sofar) {
        /* doing here prints same route twice, because from leaf, we made 2 calls left and right, both null, and sum as we want
        so dont do it here , we need to do it as soon as we found leaf
        if(sofarSum == sum && root == null) {
            ret.add(new ArrayList<>(sofar));
            return;
        }
        */
        if(root == null)
            return;
        
        sofar.add(root.val);
        sofarSum+=root.val;
        
        //check for leaf and return to avoid duplicate ans, that may result by calling with left and right as null roots
        if(root.left == null && root.right == null && sofarSum == sum) {
            ret.add(new ArrayList<>(sofar));   
            sofar.remove(sofar.size()-1);//imp to remove last elem in this case too - backtrack
            return;
        }
        
        //go left and right
        dfsHelper(root.left,sum,sofarSum,sofar);
        dfsHelper(root.right,sum,sofarSum,sofar);
        
        //remove curr node - backtrack
        //BUT notice, we dont decrement/backtrack on sofarSum, that value is i htink preserved correctly by callstack itself. same with String parameter  
        sofar.remove(sofar.size()-1);
    }
}
