** while(l<r)

-moves are m or m+1

-use this kind of loop to narrow down to l. 
  
** whie(l<=r)

-moves are m+1 and m-1

-this kind of loop will go over each elem and ans has to be found in the loop itself.

-what remains in l or r is not relevant after loop.

-useful if we can derive answer just by looking at mid node during loop. example: https://leetcode.com/problems/valid-perfect-square/
