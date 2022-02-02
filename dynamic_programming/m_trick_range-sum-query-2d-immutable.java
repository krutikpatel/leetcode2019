/*
304. Range Sum Query 2D - Immutable
DescriptionHintsSubmissionsDiscussSolution

Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:

Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12

Note:

    You may assume that the matrix does not change.
    There are many calls to sumRegion function.
    You may assume that row1 ≤ row2 and col1 ≤ col2.

*/
/*
this problem is advanced version of :
https://leetcode.com/problems/range-sum-query-immutable/description/
*/
class NumMatrix {

    
    /* Algo
    Try to see the 2D matrix as mmm rows of 1D arrays. To find the region sum, we just accumulate the sum in the region row by row.
    */
    private int[][] dp;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        
        //notice col+1 size for each row, we do that so that when col = 0 is given we dont run into trouble by doing col1-1
        //the whole row if offset to +1 in dp array
        dp = new int[matrix.length][matrix[0].length + 1];
        
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 1; c < matrix[0].length; c++) {
                dp[r][c + 1] = dp[r][c] + matrix[r][c];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        //calc sum for each row and just sum-up
        for (int row = row1; row <= row2; row++) {
            sum += dp[row][col2 + 1] - dp[row][col1];
        }
        return sum;
    }

}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
