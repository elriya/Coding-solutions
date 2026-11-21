import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] origAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origAdj[parent[i]].add(i);
        }

        int[] newParent = new int[n];
        newParent[0] = -1;
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        Deque<Integer> stack = new ArrayDeque<>();
        int[] savedPrev = new int[n];
        stack.push(0);

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (curr >= 0) {
                int c = s.charAt(curr) - 'a';
                if (curr > 0) {
                    newParent[curr] = (lastSeen[c] != -1) ? lastSeen[c] : parent[curr];
                }
                savedPrev[curr] = lastSeen[c];
                lastSeen[c] = curr;

                stack.push(-(curr + 1));
                for (int next : origAdj[curr]) {
                    stack.push(next);
                }
            } else {
                int u = -(curr + 1);
                int c = s.charAt(u) - 'a';
                lastSeen[c] = savedPrev[u];
            }
        }

        List<Integer>[] newAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            newAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            newAdj[newParent[i]].add(i);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        List<Integer> order = new ArrayList<>(n);
        stack.push(0);

        while (!stack.isEmpty()) {
            int u = stack.pop();
            order.add(u);
            for (int v : newAdj[u]) {
                stack.push(v);
            }
        }

        for (int i = order.size() - 1; i >= 0; i--) {
            int u = order.get(i);
            if (u > 0) {
                ans[newParent[u]] += ans[u];
            }
        }

        return ans;
    }
}