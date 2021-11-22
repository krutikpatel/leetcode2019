/*
Reverse Words in a String III

Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:

Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"

Note: In the string, each word is separated by single space and there will not be any extra space in the string. 

*/

class Solution {
    /*
    Another nice application of 2 pointers, similar to reverseWords
    */
    public String reverseWords(String s) {
        if(s == null || s.length() == 0)
            return s;
        
        int i=0;
        int j=0;
        
        char[] arr = s.toCharArray();
        
        while(i<s.length()){
            if(arr[i] == ' '){
                i++;
            }else{
                j=i+1;
                while(j<s.length() && arr[j] != ' '){
                    j++;                    
                }
                //now reverse that word
                reverse(arr,i,j-1);
                i=j;
            }
        }
        
        return new String(arr);
    }
    
    private void reverse(char[] arr, int i, int j){
        while(i<j){
            //swap
            char t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
            i++;
            j--;
        }
    }
}
