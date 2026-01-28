import java.util.Stack;

class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> scoreStack = new Stack<>();

        for (String op : operations) {
            switch (op) {
                case "+":
                    int top = scoreStack.pop();
                    int newScore = top + scoreStack.peek();
                    scoreStack.push(top);
                    scoreStack.push(newScore);
                    break;
                case "D":
                    scoreStack.push(2 * scoreStack.peek());
                    break;
                case "C":
                    scoreStack.pop();
                    break;
                default:
                    scoreStack.push(Integer.parseInt(op));
                    break;
            }
        }

        int totalSum = 0;
        for (int score : scoreStack) {
            totalSum += score;
        }

        return totalSum;
    }
}