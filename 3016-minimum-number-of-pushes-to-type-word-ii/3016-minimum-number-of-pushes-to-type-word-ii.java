import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        
        Arrays.sort(freq);
        
        int pushes = 0;
        int used = 0;
        
        for (int i = 25; i >= 0 && freq[i] > 0; i--) {
            // Assign 8 letters per push tier (1st press, 2nd press, etc.)
            pushes += freq[i] * (used / 8 + 1);
            used++;
        }
        
        return pushes;
    }
}