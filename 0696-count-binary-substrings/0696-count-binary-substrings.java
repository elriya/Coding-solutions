class Solution {
    public int countBinarySubstrings(String s) {
        int count = 0;
        int prevGroupLength = 0;
        int currGroupLength = 1;
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currGroupLength++;
            } else {
                count += Math.min(prevGroupLength, currGroupLength);
                prevGroupLength = currGroupLength;
                currGroupLength = 1;
            }
        }
        
        return count + Math.min(prevGroupLength, currGroupLength);
    }
}