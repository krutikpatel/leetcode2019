/*
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
Stack is better : suits more for "next" operation of itertor.
but for that, we have to push in stack from last.

my solution below is simpler
*/
public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> st;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        this.insert(nestedList);
    }

    /*
    Dont parse whole thing, that is not good/optimized idea
    just put all heads in stack
    */
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
            //take one by one till find some int
            NestedInteger curr = st.peek(); //dont take out, we just want to see if its there
            if(curr.isInteger()) {
                return true;
            }
            curr = st.pop();
            //add curr list to st, and continue the process
            insert(curr.getList());
        }
        return false;        
    }
}

/*
Using Deque : works as stack AND also can insertAt end of stack for reverse insert.

public class NestedIterator implements Iterator<Integer> {

    //List and pos ?
    Deque<Integer> s;
    public NestedIterator(List<NestedInteger> nestedList) {
        s = new ArrayDeque<>();
        //fill stack
        parse(nestedList);
    }

    private void parse(List<NestedInteger> nestedList){
        for(NestedInteger n : nestedList){
            if(n.isInteger()){
                s.addLast(n.getInteger());// NOte: since its stack, reverse insert is at End, for queue its at front
            }else {
                parse(n.getList());       
            }
        }
    }
    
    @Override
    public Integer next() {
        return s.pop();
    }

    @Override
    public boolean hasNext() {
        return !s.isEmpty();
    }
}

*/
/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
