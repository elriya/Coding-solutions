import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String digits = "123456789";
        
        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();
        
        // Iterate through all possible lengths of sequential numbers
        for (int length = lowLen; length <= highLen; length++) {
            // Slide a window of 'length' over the digits string
            for (int start = 0; start <= 9 - length; start++) {
                String sub = digits.substring(start, start + length);
                int num = Integer.parseInt(sub);
                
                // If the generated number is within the range, add it to our list
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }
}