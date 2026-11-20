import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            int extendToNext = dp.getOrDefault(x, 0) + 1;
            int extendToCurr = dp.getOrDefault(x - 1, 0) + 1;

            int updatedNext = Math.max(dp.getOrDefault(x + 1, 0), extendToNext);
            int updatedCurr = Math.max(dp.getOrDefault(x, 0), extendToCurr);

            dp.put(x + 1, updatedNext);
            dp.put(x, updatedCurr);

            ans = Math.max(ans, Math.max(updatedNext, updatedCurr));
        }

        return ans;
    }
}