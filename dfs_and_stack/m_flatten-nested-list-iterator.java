/* medium
Flatten Nested List Iterator
DescriptionHintsSubmissionsDiscussSolution

Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,1,2,1,1].

Example 2:

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,4,6].
*/
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

/*
Stack is better : 
-take adv of recursion using stack. we dont flatten in advance, and only till we find next int
-but we want fifo order to insert in stack in certain order,  we have to push in stack from last.

-> dont flatten whole list in advance using queue, that is space wastage.
->rather flatten whenever hasNext is called.

Algo:
createHelperMethod - to push in stack in reverse list order
hasNext
  -return true only if we find some integer
  -for that, we have to empty stack till we encounter int
  -if we encounter List inbetween, call addListToStack that we did on constructor

next
  -just return top
=============  
pesudocode:
define function iterativeDepthFirstSearch(nestedList):
    result = []

    stack = a new Stack
    push all items in nestedList onto stack, in reverse order

    while stack is not empty:
        nestedInteger = pop top of stack
        if nestedInteger.isInteger():
            append nestedInteger.getInteger() to result
        else:
            list = nestedInteger.getList()
            push all items in list onto stack, in reverse order

    return result
    
*/
public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> st;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        this.insert(nestedList);
    }

    private void insert(List<NestedInteger> nestedList) {
        for(int i = nestedList.size()-1;i>=0;i--){
            st.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        NestedInteger ni = st.pop();        
        return ni.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()) {
            NestedInteger curr = st.peek();
            if(curr.isInteger()) {
                return true;
            }
            curr = st.pop();
            //add curr list to st
            insert(curr.getList());
        }
        return false;        
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

/*
Queue based iterative solution:
--> but downside is, we create new storage for entire nestedList again, rather that going thru given input.

Queue<Integer> queue;

public NestedIterator(List<NestedInteger> nestedList) {
    queue = new LinkedList<Integer>();
    flatten(nestedList);
}

public Integer next() {
    return queue.poll();
}

public boolean hasNext() {
    return !queue.isEmpty();
}

public void flatten(List<NestedInteger> nestlst){
    for(NestedInteger i:nestlst){
        if(i.isInteger())
            queue.add(i.getInteger());
        else
            flatten(i.getList());
    }
}

*/
