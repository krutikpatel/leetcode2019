/* medium
43. Multiply Strings
DescriptionHintsSubmissionsDiscussSolution

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
*/
/*
Approach:
If we break it into steps, it will have the following steps. 
1. compute products from each pair of digits from num1 and num2. 
2. carry each element over. (arr will have nums 1 to 99 possible)
3. output the solution.

Things to note:

    The product of two numbers cannot exceed the sum of the two lengths. (e.g. 99 * 99 cannot be five digit)

int d1 = num1.charAt(i) - '0';
int d2 = num2.charAt(j) - '0';
products[i + j + 1] += d1 * d2;

*/
class Solution {
    public String multiply(String num1, String num2) {
        
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        //doing everything in same loop
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
                int carry = i + j;
                int sum_pos = i + j + 1;
                int sum = mul + pos[sum_pos];

                pos[carry] += sum / 10;
                pos[sum_pos] = (sum) % 10;
            }
        }  

        StringBuilder sb = new StringBuilder();
        for(int p : pos){ 
            sb.append(p);
        }
        //remove zeros from front
        while (sb.length() != 0 && sb.charAt(0) == '0') 
            sb.deleteCharAt(0);
        
        return sb.length() == 0 ? "0" : sb.toString();
        
        /*
        int n1 = num1.length(), n2 = num2.length();
        int[] products = new int[n1 + n2];//res len can max be sum of both lengths
        
        //dont worry about carry in this loop, just gather mult of individual digits, beginning from end in both nums, like we do manually
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;//this result index is trick in this solution. remember it.
            }
        }
        
        //now separate each digits and carry
        int carry = 0;
        for (int i = products.length - 1; i >= 0; i--) {
            int tmp = (products[i] + carry) % 10;
            carry = (products[i] + carry) / 10;
            products[i] = tmp;
        }
        
        //
        StringBuilder sb = new StringBuilder();
        for (int num : products) 
            sb.append(num);
        
        //remove zeros from front
        while (sb.length() != 0 && sb.charAt(0) == '0') 
            sb.deleteCharAt(0);
        
        return sb.length() == 0 ? "0" : sb.toString();
        */
    }
}
