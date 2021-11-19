/* medium
Word Search
Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
Example:
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
*/
class Solution {
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0)
            return false;
        if(word == null || word.length() == 0)
            return false;
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        //begin at all first matching char
        for (int y=0; y<board.length; y++) {
            for (int x=0; x<board[y].length; x++) {
                if (helper(0, word, y, x, board, visited)) return true;
            }
        }
        return helper(0, word, 0, 0, board, visited);
    }
    
    private boolean helper(int soFar, String word, int row, int col, char[][] board, boolean[][] visited){
        //if(soFar.equals(word)){   //its too much code to create string each time
        if(soFar == word.length()){
            return true;
        }

        //there can be common validation. which is, if cell is valid.
        if( (row<0) || (row>= board.length) || (col <0) || (col >= board[0].length) || (board[row][col] != word.charAt(soFar)) || (visited[row][col] == true) )
            return false;
        
        //mark visited
        visited[row][col] = true;
    
        //possible movement choices
        if(helper(soFar+1, word, row, col+1, board, visited)     //right
            || helper(soFar+1, word, row+1, col, board, visited) // down
            || helper(soFar+1, word, row, col-1, board, visited) // left
            || helper(soFar+1, word, row-1, col, board, visited) // up
          ){
            return true;
        }
        //clear
        visited[row][col] = false;
        
        return false;
    }   
}
