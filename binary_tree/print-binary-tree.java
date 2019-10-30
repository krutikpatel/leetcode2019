/*
Print Binary Tree
DescriptionHintsSubmissionsDiscussSolution

Print a binary tree in an m*n 2D string array following these rules:

    The row number m should be equal to the height of the given binary tree.
    The column number n should always be an odd number.
    The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
    Each unused space should contain an empty string "".
    Print the subtrees following the same rules.

Example 1:

Input:
     1
    /
   2
Output:
[["", "1", ""],
 ["2", "", ""]]

Example 3:

Input:
      1
     / \
    2   5
   / 
  3 
 / 
4 
Output:

[["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]

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
understand the pattern
width is always odd num
heigt =1, width = 1
heigt =2, width = 3
heigt =3, width = 7

    1
   2_3
 4_5_6_7
 
=>see spaces rep as underscores above 

*/
class Solution {
    public List<List<String>> printTree(TreeNode root) {
        int height = height(root);
        int width = (int)width(height);
        
        List<List<String>> result = new ArrayList<>();
        //prepare blank matrix
        for (int i = 0; i < height; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                list.add("");
            }
            result.add(list);
        }
        
        //fill nodes
        fill(result, root, 0, 0, width - 1);
        
        return result;
    }
    
    private int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private double width(int height) {
        return Math.pow(2,height)-1;
    }
    
    private void fill(List<List<String>> result, TreeNode root, int row, int left, int right) {
        if (root == null) 
            return;
        int center = (left + right) / 2;//root at any level goes to center, as if that is the start of tree
        
        result.get(row).set(center, String.valueOf(root.val));
        
        
        fill(result, root.left, row + 1, left,center);
        fill(result, root.right, row + 1, center + 1, right);
    }
}
