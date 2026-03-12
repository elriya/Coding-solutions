import java.util.*;

class Solution {
    public int maxStability(int n, int[][] edges, int k) {
        if (!isAcyclicMustEdges(n, edges)) return -1;

        int low = 1, high = 200000;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canFormSpanningTree(n, edges, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean isAcyclicMustEdges(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        int mustCount = 0;
        for (int[] e : edges) {
            if (e[3] == 1) {
                if (!dsu.union(e[0], e[1])) return false;
                mustCount++;
            }
        }
        return mustCount < n;
    }

    private boolean canFormSpanningTree(int n, int[][] edges, int k, int X) {
        DSU dsu = new DSU(n);
        int edgesUsed = 0;
        int upgradesUsed = 0;

        for (int[] e : edges) {
            if (e[3] == 1) {
                if (e[2] < X) return false; 
                dsu.union(e[0], e[1]);
                edgesUsed++;
            }
        }

        for (int[] e : edges) {
            if (e[3] == 0 && e[2] >= X) {
                if (dsu.union(e[0], e[1])) {
                    edgesUsed++;
                }
            }
        }

        for (int[] e : edges) {
            if (e[3] == 0 && e[2] < X && 2 * e[2] >= X) {
                if (upgradesUsed < k) {
                    if (dsu.union(e[0], e[1])) {
                        edgesUsed++;
                        upgradesUsed++;
                    }
                }
            }
        }

        return edgesUsed == n - 1;
    }

    class DSU {
        int[] parent;
        int components;

        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
            components = n;
        }

        int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }

        boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
                components--;
                return true;
            }
            return false;
        }
    }
}