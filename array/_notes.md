# Useful notes on Array problems

## generalized solution for kSums in JAVA
https://leetcode.com/problems/4sum/discuss/8609/My-solution-generalized-for-kSums-in-JAVA

Idea: we know 2sum solution using 2 pointers (left and right)
    So,
    
    1. 2sum Problem
    2. Reduce K sum problem to K – 1 sum Problem

    For each elem [i], solve k-1 problem recursively, at k=2, solve 2sum problem
    
    Time complexity is O(N^(K-1)).

# Note
2sum problem is binary move, BUT Not binary search which involves "mid"

# Related questions
https://leetcode.com/problems/two-sum-less-than-k/description/
3 sum
https://leetcode.com/problems/3sum-smaller/description/
https://leetcode.com/problems/3sum-closest/description/
https://leetcode.com/problems/two-sum-less-than-k/description/
