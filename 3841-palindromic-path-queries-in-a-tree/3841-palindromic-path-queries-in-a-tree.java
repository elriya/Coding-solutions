import java.util.*;

class Solution {
    class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int n, int[] initial) {
            this.n = n;
            tree = new int[4 * n];
            build(1, 0, n - 1, initial);
        }

        void build(int node, int start, int end, int[] initial) {
            if (start == end) {
                tree[node] = initial[start];
                return;
            }
            int mid = (start + end) / 2;
            build(2 * node, start, mid, initial);
            build(2 * node + 1, mid + 1, end, initial);
            tree[node] = tree[2 * node] ^ tree[2 * node + 1];
        }

        void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
                return;
            }
            int mid = (start + end) / 2;
            if (idx >= start && idx <= mid) {
                update(2 * node, start, mid, idx, val);
            } else {
                update(2 * node + 1, mid + 1, end, idx, val);
            }
            tree[node] = tree[2 * node] ^ tree[2 * node + 1];
        }

        int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) {
                return 0;
            }
            if (l <= start && end <= r) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            return query(2 * node, start, mid, l, r) ^ query(2 * node + 1, mid + 1, end, l, r);
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] heavy = new int[n];
        int[] head = new int[n];
        int[] pos = new int[n];
        int[] sz = new int[n];

        Arrays.fill(heavy, -1);

        dfs1(0, -1, 0, adj, parent, depth, sz, heavy);

        int[] curPos = new int[]{0};
        dfs2(0, -1, 0, adj, head, pos, heavy, curPos);

        int[] initialMasks = new int[n];
        for (int i = 0; i < n; i++) {
            initialMasks[pos[i]] = 1 << (s.charAt(i) - 'a');
        }

        SegmentTree st = new SegmentTree(n, initialMasks);
        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                st.update(1, 0, n - 1, pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int pathXor = queryPath(u, v, head, pos, parent, depth, st, n);
                result.add(pathXor == 0 || (pathXor & (pathXor - 1)) == 0);
            }
        }

        return result;
    }

    private void dfs1(int u, int p, int d, List<Integer>[] adj, int[] parent, int[] depth, int[] sz, int[] heavy) {
        parent[u] = p;
        depth[u] = d;
        sz[u] = 1;
        int maxSub = 0;
        for (int v : adj[u]) {
            if (v != p) {
                dfs1(v, u, d + 1, adj, parent, depth, sz, heavy);
                sz[u] += sz[v];
                if (sz[v] > maxSub) {
                    maxSub = sz[v];
                    heavy[u] = v;
                }
            }
        }
    }

    private void dfs2(int u, int p, int h, List<Integer>[] adj, int[] head, int[] pos, int[] heavy, int[] curPos) {
        head[u] = h;
        pos[u] = curPos[0]++;
        if (heavy[u] != -1) {
            dfs2(heavy[u], u, h, adj, head, pos, heavy, curPos);
        }
        for (int v : adj[u]) {
            if (v != p && v != heavy[u]) {
                dfs2(v, u, v, adj, head, pos, heavy, curPos);
            }
        }
    }

    private int queryPath(int u, int v, int[] head, int[] pos, int[] parent, int[] depth, SegmentTree st, int n) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] > depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }
            res ^= st.query(1, 0, n - 1, pos[head[v]], pos[v]);
            v = parent[head[v]];
        }
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        res ^= st.query(1, 0, n - 1, pos[u], pos[v]);
        return res;
    }
}