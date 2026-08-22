class Solution {
    public boolean checkDivisibility(int n) {
        int temp = n;
        int digitSum = 0;
        int digitProd = 1;
        
        // Extract digits and calculate both sum and product
        while (temp > 0) {
            int digit = temp % 10;
            digitSum += digit;
            digitProd *= digit;
            temp /= 10;
        }
        
        // Add the digit sum and digit product together
        int total = digitSum + digitProd;
        
        // Check for divisibility
        return n % total == 0;
    }
}