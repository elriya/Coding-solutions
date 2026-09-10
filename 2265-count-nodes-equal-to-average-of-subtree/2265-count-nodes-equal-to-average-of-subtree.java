class Solution {
    int resultCount = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return resultCount;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int currentSum = left[0] + right[0] + node.val;
        int currentCount = left[1] + right[1] + 1;

        if (currentSum / currentCount == node.val) {
            resultCount++;
        }

        return new int[]{currentSum, currentCount};
    }
}