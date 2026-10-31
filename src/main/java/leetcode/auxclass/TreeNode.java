package leetcode.auxclass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author chenmo
 * @date 2024/4/16
 */
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

