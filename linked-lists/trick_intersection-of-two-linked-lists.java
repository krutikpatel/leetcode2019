/*
160. Intersection of Two Linked Lists
DescriptionHintsSubmissionsDiscussSolution

Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:

begin to intersect at node c1.

 

Example 1:

Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
Output: Reference of the node with value = 8
Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.

*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /*
    ab
    ba
    -if they intersect, their end will be same.
    -both have same lengths so easy to traverse wihtout worrying abt different lengths
    */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        
        while(a != null || b != null){
            if(a != null){
                a = a.next;
            }else{
                //trick : we dont have to physically link a and b, we can just iterate them that many times to cover diff in length
                headB = headB.next;
            }
            
            if(b != null){
                b = b.next;
            }else{
                //trick : we dont have to physically link a and b, we can just iterate them that many times to cover diff in length
                headA = headA.next;
            }                                    
        }
        
        while(headA != headB){
            headA = headA.next;
            headB = headB.next;
        }
        
        return headA;
    }
}
