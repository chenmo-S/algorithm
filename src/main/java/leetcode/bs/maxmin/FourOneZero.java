package leetcode.bs.maxmin;

/**
 * 分割数组的最大值
 *
 * @author chenmo
 * @date 2025/4/24
 */
public class FourOneZero {

    public int splitArray(int[] nums, int k) {
        // 最小化最大值问题 二分查找
        // 时间复杂度O(Nlog(M)) 空间复杂度O(1) N为数组长度 M为sum-max
        // 结果取值范围应该在[max,sum]
        int sum = 0, max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        // 二分查找
        int res = 0;
        while (max <= sum) {
            // mid就是当前循环要控制的子数组最大值
            int mid = max + ((sum - max) >> 1);
            // 统计在mid的限制下 需要拆分多少个数组
            int count = 1, curSum = 0;
            for (int num : nums) {
                if (curSum + num > mid) {
                    // 必须拆分
                    count++;
                    curSum = num;
                } else {
                    curSum += num;
                }
            }
            if (count <= k) {
                // count可以小于k 因为小于k说明完全可以达到k的级别
                // mid可以继续减小
                res = mid;
                sum = mid - 1;
            } else {
                max = mid + 1;
            }
        }
        return res;
    }

    public int splitArray1(int[] nums, int k) {
        // 动态规划
        // 时间复杂度O(Nlog(M)) 空间复杂度O(1) N为数组长度 M为sum-max
        // 定义数组dp[i][j]为前i个数分成j段能取得的最小化最大值
        int n = nums.length;
        int[][] dp = new int[n + 1][k + 1];
        // 递推公式 增加一个m的遍历 dp[i][j]=min(dp[i][j],max(dp[m][j-1],sum(m~i)))
        // 求sum(m~i)可以使用前缀和降低时间复杂度
        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        // 确定遍历顺序 因为dp[i][j]和dp[i][j-1]有关 所以正向遍历即可
        for (int i = 1; i <= n; i++) {
            // 第二层不能简单的使用k 要确保j<=i 否则没有意义
            for (int j = 1; j <= k; j++) {
                for (int m = 1; m <= k; m++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[m][j - 1], pre[i] - pre[m]));
                }
            }
        }
        return dp[n][k];
    }
}
