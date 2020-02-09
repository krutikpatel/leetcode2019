/*
348. Design Tic-Tac-Toe
DescriptionHintsSubmissionsDiscussSolution

Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:

    A move is guaranteed to be valid and is placed on an empty block.
    Once a winning condition is reached, no more moves is allowed.
    A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.

Example:

Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |
*/
class TicTacToe {

    /*
    Brute force move()
        -checks everything 
        
    Hint 1: trade extra space such that move() operation can be done in O(1) ?
    Hint 2: You need two arrays: int rows[n], int cols[n], plus two variables: diagonal, anti_diagonal.
    
    The key observation is that in order to win Tic-Tac-Toe you must have the entire row or column. 
    Thus, we don't need to keep track of an entire n^2 board. 
    We only need to keep a "count"/sum for each row and column. 
    If at any time a row or column matches the size of the board then that player has won.

    IMP : To keep track of which player, I add one for Player1 and -1 for Player2. 
There are two additional variables to keep track of the count of the diagonals. Each time a player places a piece we just need to check the count of that row, column, diagonal and anti-diagonal.

    */
    //int[][] board;
    int[] rowSum;
    int[] colSum;
    int diagSum;
    int antiDiagSum;
    int n;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {        
        rowSum = new int[n];
        colSum = new int[n];
        diagSum = 0;
        antiDiagSum = 0;
        this.n = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    
    //for sum counting purposes, plyer 2 = -1
    /*
    rowSum needs to sum each of its col cell
    colSum needs to sum each of its row cell
    */
    public int move(int row, int col, int player) {
        if(player == 1) {
            rowSum[col]+=1;
            colSum[row]+=1;
            
            //imp
            if(row + col == n-1)
                diagSum+=1;
            
            if(row == col)
                antiDiagSum+=1;                        
            
        } else {
            rowSum[col]-=1;
            colSum[row]-=1;
            
            //imp
            if(row + col == n-1)            
                diagSum-=1;
            
            if(row == col)
                antiDiagSum-=1;
                    
        }
    
        //check if player wins
        if(rowSum[col] == n || colSum[row]== n || diagSum == n || antiDiagSum == n ||
          rowSum[col] == -n || colSum[row]== -n || diagSum == -n || antiDiagSum == -n)
            return player;
        
        return 0;
    }
    
    
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
