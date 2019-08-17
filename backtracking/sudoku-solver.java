/*
Sudoku Solver

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

    Each of the digits 1-9 must occur exactly once in each row.
    Each of the digits 1-9 must occur exactly once in each column.
    Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.

Empty cells are indicated by the character '.'.
*/
class Solution {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0)
            return;
        solve(board);
    }
    
    /*
    backtrack function is little different here. rather than continuing row and col values,
    everytime, we begin from 0,0 and go down.
    improvement:
    Start loop from next possible position rather than from the very beginning every time. - not done because its little convoluted.
    */
    private boolean solve(char[][] board){
        
        //rows
        for(int i=0;i<9;i++){
            //cols
            for(int j=0;j<9;j++){
                if(board[i][j] == '.'){
                    //each nums
                    for(char n='1';n<='9';n++){ //interesting way to iterate over 1-9 chars
                        if(isValid(i,j,n,board)){
                            
                            //place elem
                            board[i][j] = n;
                            
                            //solve further as much as we can. for some combination, it will be till end !
                            if(solve(board))
                                return true;    //because we reached till end without reaching ANY false !
                            else{
                                //clear and try next num. THAT IS OUR BACKTRACK
                                board[i][j] = '.';                                
                            }
                                
                        }
                    }
                    //if we reach here, we could no place nums correctly. sO RETURN FALSE SO THAT WE CAN BACKTRACK/UNDO LAST CHOICE AND TRY
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(int row, int col, char n, char[][] b){
        //validate against row
        for(int i = 0; i < 9; i++){
            if(b[row][i] != '.' && (b[row][i]) == n)
                return false;
        }
        
        //validate against col
        for(int i = 0; i < 9; i++){
            if(b[i][col] != '.' && (b[i][col]) == n)
                return false;
        }
        
        //validate against sub-square
        //given row,col figure out which cube to check (range of rows and cols)
        int rr = 3 * (row/3);
        int cc = 3 * (col/3);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                //System.out.println("rr = "+rr + " , cc = "+cc);
                if(b[rr+i][cc+j] != '.' && (b[rr+i][cc+j]) == n)
                    return false;
            }
        }
        
        return true;
    }
}
