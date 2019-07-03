/*
N-Queens II

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/

class Solution {
    
    int count = 0;
    //Search space : row times columns
    //first loop is rows in call args
    //second loop- loop over cols in backtrack method
    
    public int totalNQueens(int n) {
        
        //state storing metadata
        boolean[] cols = new boolean[n];     // columns   |
        boolean[] d1 = new boolean[2 * n];   // diagonals \
        boolean[] d2 = new boolean[2 * n];   // diagonals /
        
        backtracking(0, cols, d1, d2, n);
        
        return count;
    }
    
    //because metadata is in callstack, when we return from call, its cleared.
    //row in args is our fist loop
    //this particular porblem: we  need to find place of queen in each row, so if you reach to last tow and find soln, you have found one solution.
    public void backtracking(int row, boolean[] cols, boolean[] d1, boolean []d2, int n) {
        
        if(row == n) {  // there can be other ways to check this too.
            count++; 
            return;
        }   

        for(int col = 0; col < n; col++) {
            int id1 = col - row + n;    // this is / diagonal for that point. col -row can be negative, so if we want to store it in array, 
                                        // we have to make it +ve offset by adding n to each
            
            int id2 = col + row;        // this is \ diagonal for that point
            
            // checking if curr position is valid for queen.
            if(cols[col] || d1[id1] || d2[id2]) //then its invalid. will check next column.
                continue;

            //curr position is valid
            cols[col] = true; d1[id1] = true; d2[id2] = true;
            
            //move down curr path because it is valid. Calling on next row
            backtracking(row + 1, cols, d1, d2, n);
            
            //whether we found ans or not, we need to clear out curr path : this is our backtracking
            //after this step, our call returns, which is backtrackin in a way.
            cols[col] = false; d1[id1] = false; d2[id2] = false;
        }
    }   
}
