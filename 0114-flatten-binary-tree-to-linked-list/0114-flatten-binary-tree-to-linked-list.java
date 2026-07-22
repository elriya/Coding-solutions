class Solution {
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        
        while (curr != null) {
            if (curr.left != null) {
                // Find the rightmost node of the left subtree
                TreeNode runner = curr.left;
                while (runner.right != null) {
                    runner = runner.right;
                }
                
                // Rewire pointers
                runner.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            
            // Move to the next right node
            curr = curr.right;
        }
    }
}