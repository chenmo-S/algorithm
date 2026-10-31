package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 从中序和后序遍历序列构造二叉树
 *
 * @author chenmo
 * @date 2024/6/14
 */
public class OneZeroSix {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 递归
        // 后序遍历为 左右中 所以最后一个节点即root
        // 题意值不会重复 使用map记录节点位置
        Map<Integer, Integer> indexMap = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        postIndex = postorder.length - 1;
        return buildTree(indexMap, 0, inorder.length - 1, postorder);
    }

    private TreeNode buildTree(Map<Integer, Integer> indexMap, int inLeft, int inRight, int[] postorder) {
        if (inLeft > inRight) {
            return null;
        }
        TreeNode node = new TreeNode(postorder[postIndex]);
        postIndex--;
        Integer index = indexMap.get(node.val);
        // 优先递归右子树
        node.right = buildTree(indexMap, index + 1, inRight, postorder);
        node.left = buildTree(indexMap, inLeft, index - 1, postorder);

        return node;
    }

    private int postIndex = 0;

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        // 递归
        // 后序遍历为 左右中 所以最后一个节点即root
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        // 题意值不会重复 使用map记录节点位置
        Map<Integer, Integer> indexMap = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        postIndex = postorder.length - 1;
        buildTree(root, indexMap, 0, inorder.length - 1, postorder);
        return root;
    }

    private void buildTree(TreeNode root, Map<Integer, Integer> indexMap, int inLeft, int inRight, int[] postorder) {
        Integer index = indexMap.get(root.val);
        if (inLeft > inRight) {
            return;
        }
        if (postIndex < 1) {
            return;
        }
        postIndex--;
        if (index < inRight) {
            TreeNode node = new TreeNode(postorder[postIndex]);
            root.right = node;
            buildTree(node, indexMap, index + 1, inRight, postorder);
        }

        if (index > inLeft) {
            TreeNode node = new TreeNode(postorder[postIndex]);
            root.left = node;
            buildTree(node, indexMap, inLeft, index - 1, postorder);
        }
    }

    public static void main(String[] args) {
        OneZeroSix oneZeroSix = new OneZeroSix();
        oneZeroSix.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
    }
}
