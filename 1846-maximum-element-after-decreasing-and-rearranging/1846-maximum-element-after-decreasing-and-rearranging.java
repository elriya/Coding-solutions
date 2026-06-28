import java.util.Arrays;

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        //Sort the array to handle elements greedily
        Arrays.sort(arr);
        
        //The first element must strictly be 1
        arr[0] = 1;
        
        //Enforce the adjacent difference constraint
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1] + 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        
        //The last element will be the maximum possible value
        return arr[arr.length - 1];
    }
}