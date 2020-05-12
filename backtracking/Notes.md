
IMP Link:
https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)

Think of time complexities

Good page for mathematical formula for permutations and combinations

Permutation: order matters
Combination: order does not matter

1. Permutation : n!
2. Picking m out of n (Permutations) : n!/(n − r)!
3. Picking m out of n (combinations) : n!/r!(n − r)!
4. No of subsets for n elems = 2^n

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
    
### For recursive calls

- primitive data types (int, double, long) and immutable classes (String, Integer etc), will not have to be backtracked. Each call stack preserves their own value,

- so we dont have to manually revert it.
