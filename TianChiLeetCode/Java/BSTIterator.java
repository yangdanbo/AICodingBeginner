//139.二叉搜索树迭代器
//难度：中等
//题目介绍：https://tianchi.aliyun.com/oj/problems/fjjnoehktyxlctbg?spm=5176.15228502.0.0.560979bfNRRMng
import java.util.Stack;

public class BSTIterator {
    TreeNode cur;
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        cur = root;
        stack = new Stack<>();
    }
    public int next() {
        while(cur != null){
            stack.push(cur);
            cur = cur.left;
        }
        TreeNode node = stack.pop();
        cur = node.right;
        return node.val;
    }

    public boolean hasNext() {
        return cur != null || !stack.empty();
    }
}