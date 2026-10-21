class Solution {
    public int minOperations(String s) {
        if (isSorted(s)) {
            return 0;
        }
        int n = s.length();
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return -1;
        }
        
        if (canSortInOps(s, 1)) {
            return 1;
        }
        
        if (canSortInOps(s, 2)) {
            return 2;
        }
        
        return 3;
    }
    
    private boolean isSorted(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean canSortInOps(String s, int ops) {
        int n = s.length();
        if (ops == 1) {
            if (checkSort(s, 0, n - 2)) return true;
            if (checkSort(s, 1, n - 1)) return true;
            return false;
        } else if (ops == 2) {
            String[] nexts = getNextStates(s);
            for (String next : nexts) {
                if (canSortInOps(next, 1)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    
    private boolean checkSort(String s, int l, int r) {
        char[] arr = s.toCharArray();
        java.util.Arrays.sort(arr, l, r + 1);
        String sortedStr = new String(arr);
        return isSorted(sortedStr);
    }
    
    private String[] getNextStates(String s) {
        int n = s.length();
        String[] states = new String[2];
        
        char[] arr1 = s.toCharArray();
        java.util.Arrays.sort(arr1, 0, n - 1);
        states[0] = new String(arr1);
        
        char[] arr2 = s.toCharArray();
        java.util.Arrays.sort(arr2, 1, n);
        states[1] = new String(arr2);
        
        return states;
    }
}