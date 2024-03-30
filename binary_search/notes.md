** while(l<r)

-moves are m or m+1

-we may keep recording the desired/matchin value BEFORE going left (ie, last seen good value), since we narrow down towards result, at the end we can return the recorded val.

-use this kind of loop to narrow down to l. 
  
** whie(l<=r)

-moves are m+1 and m-1

-this kind of loop will go over each elem and ans has to be found in the loop itself.

-what remains in l or r is not relevant after loop.

-useful if we can derive answer just by looking at mid node during loop. example: https://leetcode.com/problems/valid-perfect-square/


** while(l+1<r)
r = nums.length-1

-Use the element's neighbors to determine if the condition is met and decide whether to go left or right

-Guarantees Search Space is at least 3 in size at each step

-Post-processing required. Loop/Recursion ends when you have 2 elements left. Need to assess if the remaining elements meet the condition.
