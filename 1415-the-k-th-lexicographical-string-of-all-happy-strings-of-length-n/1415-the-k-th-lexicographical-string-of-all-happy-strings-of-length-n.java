class Solution {
    int count = 0;
    String result = "";

    public String getHappyString(int n, int k) {
        backtrack(n, k, new StringBuilder());
        return result;
    }

    private void backtrack(int n, int k, StringBuilder current) {
        if (!result.equals("")) return;

        if (current.length() == n) {
            count++;
            if (count == k) {
                result = current.toString();
            }
            return;
        }

        for (char c : new char[]{'a', 'b', 'c'}) {
            if (current.length() > 0 && current.charAt(current.length() - 1) == c) {
                continue;
            }

            current.append(c);
            backtrack(n, k, current);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }
}