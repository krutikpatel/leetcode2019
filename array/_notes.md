# Useful notes on Array problems

## Imp problem categories
    - Two pointers
    - Sliding window
    - Prefix sum
    
## generalized solution for kSums in JAVA
https://leetcode.com/problems/4sum/discuss/8609/My-solution-generalized-for-kSums-in-JAVA

Idea: we know 2sum solution using 2 pointers (left and right)
    So,
    
    1. 2sum Problem
    2. Reduce K sum problem to K – 1 sum Problem

    For each elem [i], solve k-1 problem recursively, at k=2, solve 2sum problem
    
    Time complexity is O(N^(K-1)).

### Note
2sum problem is binary move, BUT Not binary search which involves "mid"

### Related questions
https://leetcode.com/problems/two-sum-less-than-k/description/

3 sum

https://leetcode.com/problems/3sum-smaller/description/

https://leetcode.com/problems/3sum-closest/description/

https://leetcode.com/problems/two-sum-less-than-k/description/

## Some additional lesson questions
find second highest or lowest elem in array
https://www.geeksforgeeks.org/find-second-largest-element-array/

-one pass possible with tracking 2 max vals
-same thing we can do with binary tree too

-Brute force : Looking at all possible subarrays is always O(n^2). Two loops are sufficient to look at all possible subarrays.
    -i from 0 to n
        -j from 1 to n
