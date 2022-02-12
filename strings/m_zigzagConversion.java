/* medium
ZigZag Conversion
https://leetcode.com/problems/zigzag-conversion/description/

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);

Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I

*/

class Solution {
    public String convert(String s, int numRows) {
        /*
        Try to program what we do visually
        
        one pass
        */
        
        char[] c = s.toCharArray();
        int len = c.length;
        
        StringBuffer[] rows = new StringBuffer[numRows];
        //setup eacah row
        for (int i = 0; i < rows.length; i++) 
            rows[i] = new StringBuffer();

        int i = 0;
        while (i < len) {
            // vertically down
            for (int r = 0; r < numRows && i < len; r++){    //notice : multiple conditions in for loop
                rows[r].append(c[i]);
                i++;
            }
            // Trick - obliquely up - no first and last rows
            for (int r = rows.length-2; r >= 1 && i < len; r--){
                rows[r].append(c[i]);
                i++;
            }
        }
        for (int r = 1; r < rows.length; r++)
            rows[0].append(rows[r]);
        
        return rows[0].toString();
    }
    
    /////////////// easier approach
    public String convert(String s, int numRows) {
        
        if (numRows == 1) return s;
        int r = numRows;
        
        int len = s.length();
        StringBuilder sb[] = new StringBuilder[r];
        for(int i = 0; i<r;i++) {
            sb[i] = new StringBuilder();
        }

        int currRow = 0;
        int dir = 1;
        for (char c : s.toCharArray()) {
            sb[currRow].append(c);
            
            if(currRow == r-1){
                dir = -1;//start going up
            } else if(currRow == 0) {
                dir = 1;//start going down
            }
            currRow = currRow+dir;
        }

        StringBuilder sbb = new StringBuilder();
        int i=0;
        for(; i<r;i++) {
            sbb.append(sb[i]);
        }

        return sbb.toString();

    }
    
}
