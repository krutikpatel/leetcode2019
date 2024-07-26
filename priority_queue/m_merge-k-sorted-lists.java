/*
23. Merge k Sorted Lists
DescriptionHintsSubmissionsDiscussSolution

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null)
            return null;
        if(lists.length == 1)
            return lists[0];
        
        Comparator<ListNode> cmp = new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1, ListNode o2) {            
                return o1.val-o2.val;
            }
        };
        
        Queue<ListNode> pq = new PriorityQueue(cmp);
        
        //add 1st nodes of all to pq
        for(ListNode l : lists){
            if(l!=null)
                pq.add(l);
        }
        
        //now add all nodes to pq and remove to create merged list at same time
        ListNode ret = new ListNode(0);
        ListNode curr = ret;
        
        while(!pq.isEmpty()){
            curr.next = pq.remove();
            curr = curr.next;
            
            //we just add one elem at time form remaining list, because they are sorted, it works, rather than adding everything in pq upfront.
            if(curr.next!=null){ //there is list here
                pq.add(curr.next);
            }
        }
        
        return ret.next;
    }
}
