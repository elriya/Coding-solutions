class Solution {
    public String smallestPalindrome(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder firstHalf = new StringBuilder();
        String middleChar = "";

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                middleChar = String.valueOf((char) ('a' + i));
            }
            // Add half of the count of character 'a' + i
            for (int j = 0; j < count[i] / 2; j++) {
                firstHalf.append((char) ('a' + i));
            }
        }

        // Mirror the first half
        StringBuilder secondHalf = new StringBuilder(firstHalf).reverse();

        return firstHalf.toString() + middleChar + secondHalf.toString();
    }
}