/*
297. Serialize and Deserialize Binary Tree
DescriptionHintsSubmissionsDiscussSolution

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"

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
Both ser and de-ser will follow same order recursion.
Visually looking: pre-order fits this problem.

Use Queue<> for deser, put all nodes in Q and just recursively recreate tree in Pre-order fashion
*/
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serHelper(root,sb);
        return sb.toString();
    }

    private void serHelper(TreeNode n, StringBuilder sb) {
        if(n == null) {
            sb.append("#,");
            return;
        }
        //System.out.println("pre-order :"+n.val);
        
        //rep-order
        sb.append(Integer.toString(n.val) + ",");   //note: last elem will have this unwanted ,
        //System.out.println("pre-order sb =:"+sb.toString());
        
        serHelper(n.left,sb);
        serHelper(n.right,sb);
    }
    
    // Decodes your encoded data to tree.
    //note: our serialized list follows in-order traversal, so if you keep on creating tree in in-order fashion by taking one elem at a time from this list, that should re-form the original tree
    //we could have used list/arraylist instead of queue, but in that case, we have to pass around curr-index to remove/fetch elem
    public TreeNode deserialize(String data) {
        //System.out.println(data);
        String[] nodes = data.split(",");
        Queue<String> q = new LinkedList<>(Arrays.asList(nodes));
        //return deserHelper(nodes,0);
        return deserHelper(q);
    }
    
    //DFS, prepare child nodes and provide to parent
    private TreeNode deserHelper(Queue<String> q) {
        String curr = q.remove();
        if(curr.equals("#"))
            return null;
        
        TreeNode n = new TreeNode(Integer.parseInt(curr));
        n.left = deserHelper(q);
        //System.out.println("deser: "+n.val + " left set");        
        n.right = deserHelper(q);
        
        return n;
    }
/*
    
    Note: it will not work with arg as index,curr.
        -because recursion tree with stop at first leaf
    private TreeNode deserHelper(String[] nodes, int curr) {
    
    
    Solution:
    int x = 0;
    we need to maintain index as class variable, to aactually track how far we are..
    But, that is not nice solution, so dont do it this way..
    
    int x = 0;
    private TreeNode deserHelper(String[] nodes, int curr) {
        if(x > nodes.length-2) {
            return null;
        } else if (nodes[x].equals("#")) {
            System.out.println("deser: curr = "+curr);
            System.out.println("deser: x= "+x);
            return null;
        }
        
        //System.out.println("deser: "+nodes[curr]);
        TreeNode n = new TreeNode(Integer.parseInt(nodes[x]));
        curr++;
        x++;
        n.left = deserHelper(nodes,curr);
        //System.out.println("deser: "+n.val + " left set");
        
        curr++;
        x++;
        n.right = deserHelper(nodes,curr);
        //System.out.println("deser: "+n.val + " right set");
            
        return n;
    }
*/
/*
working version without queue, since we need index, and callstack wont let us use int arg, we need object reference here for this purpose.
so just use length-1 array to store that index

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] node=data.split(" ");
        int[] d=new int[1];
        return dfs(node,d);
    }
    
    //d[0] stores/passes curr index for node[] to pull next node
    private TreeNode dfs(String[] node, int[] d) {
        if (node[d[0]].equals("null")) {
            d[0]++;
            return null;
        }
        TreeNode x=new TreeNode(Integer.valueOf(node[d[0]]));
        d[0]++;
        x.left=dfs(node,d);
        x.right=dfs(node,d);
        return x;
    }
    
*/
    
    
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
