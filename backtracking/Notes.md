
IMP Link:
https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)

Think of time complexities

Good page for mathematical formula for permutations and combinations

Permutation: order matters
Combination: order does not matter

1. Permutation : n!
2. Picking m out of n (Permutations) : n!/(n − r)!
3. Picking m out of n (combinations) : n!/r!(n − r)!


Note:
For any int array, to avoid duplicates:
a. Sort array
b. if i>0 && A[i] == A[i-1] , skip


Another way to think about choices look and loop begin index:
-Just think of one round, and that should help figure out loops needed and loop begin index
Example:
  combinations :
    -for digit 0, choices i = 1 to n
    -for digit 1, choices i = 2 to n
    
    That concludes "loop begin index" is variable, and needs to be passed to function
