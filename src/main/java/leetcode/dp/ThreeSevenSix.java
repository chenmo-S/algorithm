package leetcode.dp;

import java.util.Arrays;

/**
 * 摆动序列
 *
 * @author chenmo
 * @date 2025/5/14
 */
public class ThreeSevenSix {

    public int wiggleMaxLength(int[] nums) {
        // 贪心 时间复杂度O(N) 空间复杂度O(1)
        int n = nums.length;
        if (n == 1) {
            return 1;
        }
        // 贪心思想 直接从最左侧开始肯定是最优
        // 所以就是一次遍历即可
        int res = nums[1] == nums[0] ? 1 : 2;
        int preDiff = nums[1] - nums[0];
        for (int i = 2; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            if (diff == 0) {
                continue;
            }
            if (preDiff == 0) {
                // 从这里才算正式开始 前面全是相等的数字 让res=2
                res = 2;
                preDiff = diff;
            } else if ((diff < 0 && preDiff > 0) || (diff > 0 && preDiff < 0)) {
                res++;
                preDiff = diff;
            }

        }
        return res;
    }

    public int wiggleMaxLength2(int[] nums) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(N) 如果使用两个变量可优化为O(1)的空间复杂度
        int n = nums.length;
        if (n == 1) {
            return 1;
        }
        // 使用两个数组
        // up[i]代表前i个中最长的最后节点为上升的队列的长度
        // down[i]代表前i个中最长的最后节点为下降的队列的长度
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = down[0] = 1;
        up[1] = nums[1] > nums[0] ? 2 : 1;
        down[1] = nums[1] < nums[0] ? 2 : 1;
        for (int i = 2; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }

    public int wiggleMaxLength1(int[] nums) {
        // 动态规划 时间复杂度O(N^2) 空间复杂度O(N)
        // 使用一个数组记录差值
        int n = nums.length;
        int[] diff = new int[n];
        for (int i = 1; i < n; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
        // 设dp[i]为以下标i为序列结尾的最长摆动序列长度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < n; i++) {
            if (diff[i] == 0) {
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (diff[i] > 0) {
                    // 整数差值 要求前面是负数
                    dp[i] = Math.max(dp[i], diff[j] >= 0 && j > 0 ? 1 : dp[j] + 1);
                } else if (diff[i] < 0) {
                    // 负数差值 要求前面是正数
                    dp[i] = Math.max(dp[i], diff[j] <= 0 && j > 0 ? 1 : dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSevenSix().wiggleMaxLength(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }
}
