/*
You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:

Connect: A cell is connected to adjacent cells horizontally or vertically.
Region: To form a region connect every 'O' cell.
Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
A surrounded region is captured by replacing all 'O's with 'X's in the input matrix board.

*/
class Solution {
    /**
    - DFS from the border 0’s because those are “unsurrounded and will make connected 0 unsurrounded.”
    - what will be left is surrounded

    -this reverse way is much cleaner to do than figuring out what cell is NOT on border and DFS from it... and still check if it reaches border or not
 */
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];//If dont want to use visited, we need to mark cell with 'T' and then flip it back

        for(int r = 0;r<m;r++){
            for(int c=0;c<n;c++){
                if((r ==0 || c == 0 || r==m-1 || c == n-1 ) && board[r][c] == 'O') {
                    dfs(r,c,board, visited);
                }
            }
        }

        //now convert the other ones which are not visited to X
        for(int r = 0;r<m;r++){
            for(int c=0;c<n;c++){
                if(board[r][c] == 'O' && visited[r][c] == false){
                    board[r][c] = 'X';
                }
            }
        }
    }

    private void dfs(int r, int c, char[][] board, boolean[][] visited) {
        int m = board.length;
        int n = board[0].length;
        if(r <0 || c <0 || r>=m || c>=n || visited[r][c] || board[r][c] == 'X'){
            return;
        }
        visited[r][c] = true;
        dfs(r+1,c,board,visited);
        dfs(r-1,c,board,visited);
        dfs(r,c+1,board,visited);
        dfs(r,c-1,board,visited);
    }
}
