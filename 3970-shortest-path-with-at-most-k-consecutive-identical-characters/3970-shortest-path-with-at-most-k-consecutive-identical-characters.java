import java.util.*;

class Solution {
    static class State implements Comparable<State> {
        int cost;
        int u;
        int count;

        State(int cost, int u, int count) {
            this.cost = cost;
            this.u = u;
            this.count = count;
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<State> pq = new PriorityQueue<>();

        dist[0][1] = 0;
        pq.add(new State(0, 0, 1));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int cost = curr.cost;
            int u = curr.u;
            int count = curr.count;

            if (u == n - 1) {
                return cost;
            }

            if (cost > dist[u][count]) {
                continue;
            }

            char uLabel = labels.charAt(u);

            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];
                char vLabel = labels.charAt(v);

                int nextCount = (vLabel == uLabel) ? count + 1 : 1;

                if (nextCount <= k) {
                    if (cost + weight < dist[v][nextCount]) {
                        dist[v][nextCount] = cost + weight;
                        pq.add(new State(dist[v][nextCount], v, nextCount));
                    }
                }
            }
        }

        return -1;
    }
}