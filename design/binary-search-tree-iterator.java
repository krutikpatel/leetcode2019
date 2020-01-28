/*
173. Binary Search Tree Iterator
DescriptionHintsSubmissionsDiscussSolution

Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

 

Example:

BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false

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
class BSTIterator {

    Stack<TreeNode> s;
    public BSTIterator(TreeNode root) {
        s  = new Stack();
        
        if(root == null)
            return;
        
        //Stack<TreeNode> t = new Stack();
        TreeNode curr = root;
/*        
        //fill the stack
        //while(curr != null && !t.isEmpty()){
        while(!t.isEmpty()){
            
            //go far right - reverse of what we want because its stack
            while(curr != null){
                t.push(curr);
                curr = curr.right;
            }
            
            //process curr
            curr = t.pop();
            System.out.println("curr = "+curr.val);  
            s.push(curr);
            
            //add left - no need of null check
            //if(curr.left != null){
                t.push(curr.left);    
            //}                        
        }
        System.out.println("ctr done");
*/
        while(curr != null){
            s.push(curr);
            curr = curr.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        //System.out.println("next");
        //System.out.println("s size = "+s.size());
        TreeNode n = s.pop();
        int ret = n.val;
                        
        //push everything on left of right again
        n = n.right;
        while(n != null){
            s.push(n);
            n = n.left;
        }
        
        return ret;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        //System.out.println("has next");
        return !s.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
