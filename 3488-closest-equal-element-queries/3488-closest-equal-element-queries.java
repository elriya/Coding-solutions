import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> answer = new ArrayList<>();

        for (int qIdx : queries) {
            int targetVal = nums[qIdx];
            List<Integer> indices = indexMap.get(targetVal);

            if (indices.size() <= 1) {
                answer.add(-1);
                continue;
            }

            int pos = Collections.binarySearch(indices, qIdx);
            
            int minDistance = Integer.MAX_VALUE;

            int leftIdx = (pos - 1 + indices.size()) % indices.size();
            int rightIdx = (pos + 1) % indices.size();

            for (int neighbor : new int[]{indices.get(leftIdx), indices.get(rightIdx)}) {
                int directDist = Math.abs(qIdx - neighbor);
                int circularDist = Math.min(directDist, n - directDist);
                minDistance = Math.min(minDistance, circularDist);
            }

            answer.add(minDistance);
        }

        return answer;
    }
}