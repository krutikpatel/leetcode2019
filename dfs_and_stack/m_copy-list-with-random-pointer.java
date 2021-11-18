/* medium
Copy List with Random Pointer
DescriptionHintsSubmissionsDiscussSolution

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

 

Example 1:

Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.
*/
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
    public Node copyRandomList(Node head) {
        /*
        Solution is like clone Graph
        DFS
        
        map of visited nodes, store new copy of that node.
        
        -at eaach node, if part of visited, use that.
        -else
            -copy next
            -copy random
        */
        HashMap<Node,Node> visited = new HashMap<>();
        return helper(visited,head);
    }
    
    private Node helper(HashMap<Node,Node> visited, Node n) {
        if(n==null)
            return n;
        
        if(visited.containsKey(n)) {
            return visited.get(n);
        }
        
        Node nn = new Node();
        visited.put(n,nn);
        
        Node next = helper(visited,n.next);
        Node random = helper(visited,n.random);
        
        nn.val = n.val;
        nn.next = next;
        nn.random = random;
        return nn;
    }
}
