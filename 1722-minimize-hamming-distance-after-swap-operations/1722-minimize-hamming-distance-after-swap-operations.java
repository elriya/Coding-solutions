import java.util.*;

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        DSU dsu = new DSU(n);

        for (int[] swap : allowedSwaps) {
            dsu.union(swap[0], swap[1]);
        }

        Map<Integer, List<Integer>> components = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            components.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        int minHammingDistance = 0;

        for (List<Integer> indices : components.values()) {
            Map<Integer, Integer> sourceCount = new HashMap<>();
            
            for (int i : indices) {
                sourceCount.put(source[i], sourceCount.getOrDefault(source[i], 0) + 1);
            }

            for (int i : indices) {
                int targetVal = target[i];
                if (sourceCount.getOrDefault(targetVal, 0) > 0) {
                    sourceCount.put(targetVal, sourceCount.get(targetVal) - 1);
                } else {
                    minHammingDistance++;
                }
            }
        }

        return minHammingDistance;
    }

    class DSU {
        int[] parent;

        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]); 
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
            }
        }
    }
}