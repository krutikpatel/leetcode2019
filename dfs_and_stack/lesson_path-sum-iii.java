/*
437. Path Sum III
DescriptionHintsSubmissionsDiscussSolution

You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

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
    int count  = 0;
/*  there are many redundant sums in brute force. use prefix-sum store technique
so still go top-bottom, but also we need to store what sumSoFar we have seen, so htat we can conclude that we can make target. use hashmap for that.

Map<sumSoFar, frequency>
    how does this entry help find if we can meet target?
        -think of path so-far as an array. for paht: 10,5,3,3 -> sum sofar  = 10,15,18,21. 
        At 18, we can ask, have we seen 18-target, if so, we have reqd sequence. index 1 to index2

That too, backtrack from map too before returning from curr node. because curr path is not part of some other branch
*/
    public int pathSum(TreeNode root, int sum) {
        //Map<sumSoFar, frequency> prefix sum technique
        Map<Integer, Integer> map = new HashMap<>();
        
        map.put(0, 1);  //Default sum = 0 has one count
        
        return backtrack(root, 0, sum, map); 
    }
    
    //BackTrack one pass. And using return value to sum up count
    public int backtrack(TreeNode root, int sumSoFar, int target, Map<Integer, Integer> map){
        if(root == null)
            return 0;
        sumSoFar += root.val;
        
        int res = map.getOrDefault(sumSoFar - target, 0);    //See if there is a subarray sum equals to target
        
        map.put(sumSoFar, map.getOrDefault(sumSoFar, 0)+1);
        
        //Extend to left and right child
        res += backtrack(root.left, sumSoFar, target, map) + backtrack(root.right, sumSoFar, target, map);
        
        //backtrack result from map, removing this node from path to be discovered later.
        map.put(sumSoFar, map.get(sumSoFar)-1);   //Remove the current node so it wont affect other path
        
        return res;
    }
    
    
//if left subtree has been scanned, preSum has to remove this path, because this path is not the prefix path of the right subtree. Same as the left subtree, when right subtree is scanned, the path should be removed too.    
/*    
    //dfs
    private void helper(TreeNode root, int sum, HashMap<TreeNode,Integer> map) {
        int l = pathSum(root.left, sum);
        int r = pathSum(root.right, sum);
        
        if(root.val + l == sum)
            count ++;
        if(root.val + r == sum)
            count++;
        
        //what is return val for topper level ?
    }
*/
    
/* brute force   ------------------- 
    //recurisve n-square solution    
    public int pathSum(TreeNode root, int sum) {
        if (root == null) 
            return 0;
        
        //calulate 3 sums: form current, fomr left, form right. this is done recursively
        calcSumFrom(root, sum);
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        
        return  count;
    }
    
    //O(n) work
    private void calcSumFrom(TreeNode node, int sum) {
        if (node == null) 
            return;
        if (node.val - sum == 0)
            count++;
        
        calcSumFrom(node.left, sum - node.val);
        calcSumFrom(node.right, sum - node.val);
    }
*/    
}
