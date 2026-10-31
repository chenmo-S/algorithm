package leetcode.tree.bst;

import leetcode.auxclass.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 二叉搜索树中第K小的元素
 *
 * @author chenmo
 * @date 2025/3/26
 */
public class TwoThreeZero {

    public int kthSmallest(TreeNode root, int k) {
        // 中序遍历 迭代
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode pop = stack.pop();
            if (--k == 0) {
                return pop.val;
            }
            root = pop.right;
        }
        return -1;
    }

    int res = 0;

    int num = 0;

    public int kthSmallest1(TreeNode root, int k) {
        // 中序遍历 递归
        num = k;
        inorder(root);
        return res;
    }

    private void inorder(TreeNode root) {
        if (root == null || num <= 0) {
            return;
        }
        inorder(root.left);
        if (--num == 0) {
            res = root.val;
            return;
        }
        inorder(root.right);
    }
}
