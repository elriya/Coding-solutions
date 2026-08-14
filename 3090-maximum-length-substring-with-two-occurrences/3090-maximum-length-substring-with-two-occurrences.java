class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            count[currentChar - 'a']++;

            // Shrink window if any character appears more than twice
            while (count[currentChar - 'a'] > 2) {
                count[s.charAt(left) - 'a']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}