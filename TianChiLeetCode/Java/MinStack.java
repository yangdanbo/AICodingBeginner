//130.最小栈
//难度：简单
//题目介绍：https://tianchi.aliyun.com/oj/problems/0f5efm1lgkc71qit?spm=5176.15228502.0.0.123a79bfJzk7fR
import java.util.Stack;

public class MinStack {
    Stack<Integer> stack;
    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
    }

    public void pop() {
        if (stack != null) {
            stack.pop();
        }
    }

    public int top() {
        if (stack != null) {
            return stack.peek();
        } else {
            return -1;
        }
    }

    public int getMin() {
        int minValue = Integer.MAX_VALUE;
        if (stack != null) {
            for (Integer s : stack) {
                if (s < minValue) {
                    minValue = s;
                }
            }
        }
        return minValue;
    }
}