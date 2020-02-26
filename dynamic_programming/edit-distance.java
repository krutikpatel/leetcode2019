/*
72. Edit Distance
DescriptionHintsSubmissionsDiscussSolution

Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

    Insert a character
    Delete a character
    Replace a character

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

*/
class Solution {
    /*
    Let following be the function definition :-

    f(i, j) := minimum cost (or steps) required to convert first i characters of word1 to first j characters of word2

    Case 1: word1[i] == word2[j], i.e. the ith the jth character matches.

        f(i, j) = f(i - 1, j - 1)

    Case 2: word1[i] != word2[j], then we must either insert, delete or replace, whichever is cheaper

        f(i, j) = 1 + min { f(i, j - 1), f(i - 1, j), f(i - 1, j - 1) }

        f(i, j - 1) represents insert operation
        f(i - 1, j) represents delete operation
        f(i - 1, j - 1) represents replace operation

    */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        //setup memo - considering empty string sol
        int[][] cost = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++)
            cost[i][0] = i;
        for(int i = 1; i <= n; i++)
            cost[0][i] = i;
        
        //now start filling 
        //IMP- dp[i+1][j+1] is smaller/substring 
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                //if same char, ans is same as below
                if(word1.charAt(i) == word2.charAt(j))
                    cost[i + 1][j + 1] = cost[i][j];    //IMP- dp[i+1][j+1] is smaller/substring 
                else {
                    //we are standing at (i+1, j+1)
                    int a = cost[i][j]; //replace
                    int b = cost[i][j + 1]; // delete (from target)
                    int c = cost[i + 1][j]; // insert (insert to source)
                    cost[i + 1][j + 1] = 1 + Math.min(a, Math.min(b,c)); // min of 3                    
                }
            }
        }
        
        return cost[m][n];
    }
}
