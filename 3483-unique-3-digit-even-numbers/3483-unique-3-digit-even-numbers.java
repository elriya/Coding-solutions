import java.util.HashMap;

class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];
        for (int d : digits) {
            count[d]++;
        }

        int distinctCount = 0;

        for (int num = 100; num < 1000; num += 2) {
            int h = num / 100;          // Hundreds place
            int t = (num / 10) % 10;    // Tens place
            int u = num % 10;           // Units place

            int[] tempCount = new int[10];
            tempCount[h]++;
            tempCount[t]++;
            tempCount[u]++;

            boolean possible = true;
            for (int i = 0; i < 10; i++) {
                if (tempCount[i] > count[i]) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                distinctCount++;
            }
        }

        return distinctCount;
    }
}