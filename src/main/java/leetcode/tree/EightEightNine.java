package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据前序和后序遍历构造二叉树
 *
 * @author chenmo
 * @date 2025/4/9
 */
public class EightEightNine {

    int preIndex = 0;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;
        Map<Integer, Integer> postMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            postMap.put(postorder[i], i);
        }
        return dfs(preorder, postMap, n - 1, 0);
    }

    public TreeNode dfs(int[] preorder, Map<Integer, Integer> postMap, int preRight, int postLeft) {
        if (preIndex > preRight) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[preIndex]);
        preIndex++;
        // 已经没有子节点了
        if (preIndex > preRight) {
            return node;
        }
        // 有子节点优先认为左子树
        int leftCount = postMap.get(preorder[preIndex]) - postLeft + 1;
        node.left = dfs(preorder, postMap, preIndex + leftCount - 1, postLeft);
        node.right = dfs(preorder, postMap, preRight, postLeft + leftCount);
        return node;
    }


}
