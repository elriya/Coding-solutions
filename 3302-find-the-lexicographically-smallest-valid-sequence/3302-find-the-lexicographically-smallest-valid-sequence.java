class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // positions[c] = all indices where character c occurs in word1
        int[][] positions = new int[26][];

        // First count occurrences
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[word1.charAt(i) - 'a']++;
        }

        // Allocate
        for (int c = 0; c < 26; c++) {
            positions[c] = new int[count[c]];
        }

        // Fill positions
        int[] ptr = new int[26];
        for (int i = 0; i < n; i++) {
            int c = word1.charAt(i) - 'a';
            positions[c][ptr[c]++] = i;
        }

        
        // These let us find the nearest character != c in O(1).
        
        int[] runStart = new int[n];
        int[] runEnd = new int[n];

        for (int i = 0; i < n; i++) {
            if (i > 0 && word1.charAt(i) == word1.charAt(i - 1)) {
                runStart[i] = runStart[i - 1];
            } else {
                runStart[i] = i;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (i + 1 < n && word1.charAt(i) == word1.charAt(i + 1)) {
                runEnd[i] = runEnd[i + 1];
            } else {
                runEnd[i] = i + 1;
            }
        }

        // exact[j]: largest index from which word2[j..] can be matched exactly.
        // almost[j]: largest index from which word2[j..] can be matched with at most one mismatch.
        // n represents the empty suffix.
        int[] exact = new int[m + 1];
        int[] almost = new int[m + 1];

        exact[m] = n;
        almost[m] = n;

        for (int j = m - 1; j >= 0; j--) {
            char target = word2.charAt(j);

            // Exact match for word2[j]
            exact[j] = lastOccurrence(
                    positions[target - 'a'],
                    exact[j + 1]
            );

            //Case 1:
            
            int useLaterMismatch = lastOccurrence(
                    positions[target - 'a'],
                    almost[j + 1]
            );

            //Case 2:
        
            int useMismatchNow = lastDifferent(
                    word1,
                    runStart,
                    exact[j + 1],
                    target
            );

            almost[j] = Math.max(useLaterMismatch, useMismatchNow);
        }

        // No valid sequence exists.
        if (almost[0] == -1) {
            return new int[0];
        }

        int[] answer = new int[m];

        int previous = -1;
        boolean mismatchUsed = false;

        for (int j = 0; j < m; j++) {
            char target = word2.charAt(j);
            int start = previous + 1;

            //Candidate 1: use an exact occurrence.
          
            int exactCandidate = firstOccurrence(
                    positions[target - 'a'],
                    start
            );

            if (mismatchUsed) {
                // No mismatch remains, so suffix must be exact.
                int bound = exact[j + 1];

                if (exactCandidate == -1 ||
                    bound == -1 ||
                    exactCandidate >= bound) {
                    return new int[0];
                }

                answer[j] = exactCandidate;
                previous = exactCandidate;
            } else {
                //Candidate 2: use a mismatch at this position.
                
                int mismatchCandidate = -1;

                if (start < n) {
                    if (word1.charAt(start) != target) {
                        mismatchCandidate = start;
                    } else {
                        // Skip the whole run of target characters.
                        int next = runEnd[start];
                        if (next < n) {
                            mismatchCandidate = next;
                        }
                    }
                }

                // If we take an exact character, the remaining suffix may still use the mismatch.
                
                boolean exactPossible =
                        exactCandidate != -1 &&
                        almost[j + 1] != -1 &&
                        exactCandidate < almost[j + 1];

                // If we use a mismatch now, the remaining suffix must be matched exactly.
                
                boolean mismatchPossible =
                        mismatchCandidate != -1 &&
                        exact[j + 1] != -1 &&
                        mismatchCandidate < exact[j + 1];

                if (!exactPossible && !mismatchPossible) {
                    return new int[0];
                }

                // We want the smallest INDEX, regardless of whether it is an exact match or the mismatch.

                if (mismatchPossible &&
                    (!exactPossible || mismatchCandidate < exactCandidate)) {

                    answer[j] = mismatchCandidate;
                    previous = mismatchCandidate;
                    mismatchUsed = true;

                } else {
                    answer[j] = exactCandidate;
                    previous = exactCandidate;
                }
            }
        }

        return answer;
    }

    // Largest occurrence of c strictly before 'bound'.
    private int lastOccurrence(int[] arr, int bound) {
        if (bound <= 0) {
            return -1;
        }

        int lo = 0;
        int hi = arr.length - 1;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] < bound) {
                ans = arr[mid];
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    // Smallest occurrence of c >= start.
    private int firstOccurrence(int[] arr, int start) {
        int lo = 0;
        int hi = arr.length - 1;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] >= start) {
                ans = arr[mid];
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    private int lastDifferent(
            String word1,
            int[] runStart,
            int bound,
            char target) {

        int i = bound - 1;

        if (i < 0) {
            return -1;
        }

        if (word1.charAt(i) != target) {
            return i;
        }

        return runStart[i] - 1;
    }
}