/*
428. Serialize and Deserialize N-ary Tree
DescriptionHintsSubmissionsDiscussSolution

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree

as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this format.

Or you can follow LeetCode's level order traversal serialization format, where each group of children is separated by the null value.

For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].

You do not necessarily need to follow the above suggested formats, there are many more different formats that work so please be creative and come up with different approaches yourself.

 

Constraints:

    The height of the n-ary tree is less than or equal to 1000
    The total number of nodes is between [0, 10^4]
    Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.


*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
/*
String in form of :
val,Size,Val,Size
*/
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        List<String> list=new LinkedList<>();
        serializeHelper(root,list);
        String s = String.join(",",list);//uses , as delimiter betn each elem of list
        //System.out.println(s);
        return s;
    }

    //pre-order. imp -we add size too for deser to know -> because its not binary tree
    private void serializeHelper(Node root, List<String> list){
        if(root==null){
            return;
        }else{
            list.add(String.valueOf(root.val));
            list.add(String.valueOf(root.children.size()));
            for(Node child:root.children){
                serializeHelper(child,list);
            }            
        }
    }
    
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty())
            return null;
        
        String[] ss=data.split(",");
        
        //add everything to Q - we need first-in first-out for level order traversal
        Queue<String> q=new LinkedList<>(Arrays.asList(ss));
        
        //level order traversal
        return deserHelper(q);
    }
    
    private Node deserHelper(Queue<String> q) {
        if(q.isEmpty())
            return null;
        
        int curr = Integer.valueOf(q.poll());
        int size = Integer.valueOf(q.poll());
        
        Node n = new Node(curr, new ArrayList<Node>());
        for(int i=0;i<size;i++) {
            n.children.add(deserHelper(q));//recursive call
        }
        return n;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
