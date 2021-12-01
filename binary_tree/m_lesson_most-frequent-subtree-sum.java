/* medium
508. Most Frequent Subtree Sum
DescriptionHintsSubmissionsDiscussSolution

Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3

return [2, -3, 4], since all the values happen only once, return all of them in any order.

Examples 2
Input:

  5
 /  \
2   -5

return [2], since 2 happens twice, however -5 only occur once.

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
    //sum,count
    Map<Integer, Integer> count = new HashMap<Integer, Integer>();
    int maxCount = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);
        List<Integer> res = new ArrayList<>();
        
        for (int s : count.keySet()) {
            if (count.get(s) == maxCount)   //return all with max sum
                res.add(s);
        }
        
        return res.stream().mapToInt(i->i).toArray();
    }

    /*
    lesson - imp - we dont call do sum for every node separately, we only call it once and record it in map before returning from method
    Also, we dont care about with node had what sum, but even then it wud be same method
    
    lesson - DFS - gives state of all smaller trees, it goes thru all smaller trees from bottom
    */
    private int dfs(TreeNode root) {
        if (root == null) 
            return 0;
        int s = dfs(root.left) + dfs(root.right) + root.val;
        
        count.put(s, count.getOrDefault(s, 0) + 1);
        maxCount = Math.max(maxCount, count.get(s));
        return s;
    }
}
