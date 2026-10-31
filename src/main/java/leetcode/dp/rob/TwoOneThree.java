package leetcode.dp.rob;

/**
 * 打家劫舍Ⅱ
 *
 * @author chenmo
 * @date 2025/4/22
 */
public class TwoOneThree {

    public int rob(int[] nums) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(1) 空间优化
        // 两次求最大值 一次范围为0~n-2 一次为1~n-1
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
    }

    private int rob(int[] nums, int start, int end) {
        // 基础版打家劫舍 空间优化版本
        if (end - start == 0) {
            return nums[start];
        }
        int first = nums[start], two = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int tmp = two;
            two = Math.max(first + nums[i], two);
            first = tmp;
        }
        return two;
    }

    private int rob1(int[] nums, int start, int end) {
        // 基础版打家劫舍
        if (end - start == 0) {
            return nums[start];
        }
        int[] dp = new int[end - start + 1];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i - start] = Math.max(dp[i - 1 - start], dp[i - 2 - start] + nums[i]);
        }
        return dp[end - start];
    }
}
