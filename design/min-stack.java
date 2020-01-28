/*
155. Min Stack
DescriptionHintsSubmissionsDiscussSolution

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.

 

Example:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

*/
class MinStack {

    Stack<Integer> st;
    Stack<Integer> minst;
    
    /** initialize your data structure here. */
    /*
    Maintain additional stack for mins
    1. easy to just store min for every entry made in main stack
    2. or just add to min stack when its lesser-or-equal to existing min
    
    so, we add to min stack when x<= peek
    we remove from min stack when x == peek
    */
    public MinStack() {
        st = new Stack();
        minst = new Stack();
    }
    
    public void push(int x) {
        st.push(x);
        if(minst.isEmpty()){
            minst.push(x);
        } else if(x<= minst.peek()){
            minst.push(x);
        }
    }
    
    public void pop() {
        int x = st.pop();
        if(x == minst.peek()){
            minst.pop();
        }
        //st.pop();
    }
    
    public int top() {
        return st.peek();
    }
    
    public int getMin() {
        return minst.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
