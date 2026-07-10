import java.util.*;

public class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // 1. Get sorted unique values
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        int m = set.size();
        int[] uniqueVals = new int[m];
        int idx = 0;
        for (int val : set) {
            uniqueVals[idx++] = val;
        }
        
        // 2. Compute the immediate next hop for each unique value
        int[] nxt = new int[m];
        for (int i = 0; i < m; i++) {
            int target = uniqueVals[i] + maxDiff;
            int insertionPoint = Arrays.binarySearch(uniqueVals, target);
            if (insertionPoint >= 0) {
                nxt[i] = insertionPoint;
            } else {
                nxt[i] = -insertionPoint - 2;
            }
        }
        
        // 3. Build Binary Lifting Table
        int maxLog = 18; 
        int[][] up = new int[m][maxLog];
        for (int i = 0; i < m; i++) {
            up[i][0] = nxt[i];
        }
        
        for (int k = 1; k < maxLog; k++) {
            for (int i = 0; i < m; i++) {
                up[i][k] = up[up[i][k - 1]][k - 1];
            }
        }
        
        // 4. Process Queries
        int[] answer = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];
            
            // Case 1: Same node
            if (u == v) {
                answer[q] = 0;
                continue;
            }
            
            // Case 2: Different nodes, same value -> Direct edge always exists
            if (nums[u] == nums[v]) {
                answer[q] = 1;
                continue;
            }
            
            int valA = nums[u];
            int valB = nums[v];
            if (valA > valB) {
                int temp = valA;
                valA = valB;
                valB = temp;
            }
            
            int startIdx = Arrays.binarySearch(uniqueVals, valA);
            int targetIdx = Arrays.binarySearch(uniqueVals, valB);
            
            int curr = startIdx;
            int steps = 0;
            
            // Lift upwards using powers of 2
            for (int k = maxLog - 1; k >= 0; k--) {
                if (up[curr][k] < targetIdx) {
                    steps += (1 << k);
                    curr = up[curr][k];
                }
            }
            
            // Take one final step if it can reach or overshoot the targetIdx
            if (curr < targetIdx && up[curr][0] >= targetIdx) {
                steps++;
                curr = up[curr][0];
            }
            
            if (curr < targetIdx) {
                answer[q] = -1;
            } else {
                answer[q] = steps;
            }
        }
        
        return answer;
    }
}