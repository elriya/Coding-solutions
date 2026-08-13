class Solution {
    private class Node {
        int maxLen;
        int prefixLen;
        int suffixLen;
        char leftChar;
        char rightChar;
        int length;

        public Node(char ch) {
            this.maxLen = 1;
            this.prefixLen = 1;
            this.suffixLen = 1;
            this.leftChar = ch;
            this.rightChar = ch;
            this.length = 1;
        }

        public Node() {}
    }

    private Node[] tree;
    private char[] chars;

    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.length = left.length + right.length;
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        // Base maxLen from left and right children
        res.maxLen = Math.max(left.maxLen, right.maxLen);
        res.prefixLen = left.prefixLen;
        res.suffixLen = right.suffixLen;

        // Check if middle characters match to bridge the segment
        if (left.rightChar == right.leftChar) {
            res.maxLen = Math.max(res.maxLen, left.suffixLen + right.prefixLen);

            if (left.prefixLen == left.length) {
                res.prefixLen = left.length + right.prefixLen;
            }
            if (right.suffixLen == right.length) {
                res.suffixLen = right.length + left.suffixLen;
            }
        }

        return res;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, char ch) {
        if (start == end) {
            chars[idx] = ch;
            tree[node] = new Node(ch);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, ch);
        } else {
            update(2 * node + 1, mid + 1, end, idx, ch);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        this.chars = s.toCharArray();
        this.tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            update(1, 0, n - 1, idx, ch);
            result[i] = tree[1].maxLen;
        }

        return result;
    }
}