import java.util.*;

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int initialZeros = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') initialZeros++;
        }

        if (initialZeros == 0) return 0;

        TreeSet<Integer> evenUnvisited = new TreeSet<>();
        TreeSet<Integer> oddUnvisited = new TreeSet<>();
        for (int i = 0; i <= n; i++) {
            if (i == initialZeros) continue;
            if (i % 2 == 0) evenUnvisited.add(i);
            else oddUnvisited.add(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(initialZeros);
        
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            while (size-- > 0) {
                int z = queue.poll();
                
                int minI = Math.max(0, k - (n - z));
                int maxI = Math.min(k, z);
                
                int left = z + k - 2 * maxI;
                int right = z + k - 2 * minI;

                TreeSet<Integer> targetSet = (left % 2 == 0) ? evenUnvisited : oddUnvisited;
                
                Integer nextZ = targetSet.ceiling(left);
                while (nextZ != null && nextZ <= right) {
                    if (nextZ == 0) return steps;
                    queue.add(nextZ);
                    targetSet.remove(nextZ); 
                    nextZ = targetSet.ceiling(left);
                }
            }
        }

        return -1;
    }
}