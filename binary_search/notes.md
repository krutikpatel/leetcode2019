**while(l<r)**

- Moves are m or m+1
- We may keep recording the desired/matching value BEFORE going left (i.e., last seen good value), since we narrow down towards result, at the end we can return the recorded val.
- Use this kind of loop to narrow down to l.

**while(l<=r)**

- Initial Condition: left = 0, right = length - 1
- Moves are m+1 and m-1
- This kind of loop will go over each element and answer has to be found in the loop itself.
- What remains in l or r is not relevant after loop.
- Useful if we can derive answer just by looking at mid node during loop. Example: [Valid Perfect Square](https://leetcode.com/problems/valid-perfect-square/)

**while(l+1<r)**

- r = nums.length-1
- Use the element's neighbors to determine if the condition is met and decide whether to go left or right
- Guarantees Search Space is at least 3 in size at each step
- Post-processing required. Loop/Recursion ends when you have 2 elements left. Need to assess if the remaining elements meet the condition.
