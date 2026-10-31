package leetcode.segmentTree;

import java.util.Arrays;

/**
 * 最长递增子序列Ⅱ
 *
 * @author chenmo
 * @date 2025/3/6
 */
public class TwoFourZeroSeven {

    public int lengthOfLIS(int[] nums, int k) {
        // 用线段树来优化dp
        // 因为在dp的内层循环里 本质上找的就是[0,i]的中符合nums[j]<=nums[i]<=nums[j]+k的max(dp[j])
        return 0;
    }

    public int lengthOfLIS1(int[] nums, int k) {
        // 动态规划 时间复杂度O(N^2) 空间复杂度O(N) leetCode超时 71/84
        // 设dp[i]为nums以i为结尾的最长递增子序列
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && nums[i] - nums[j] <= k && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 错误 76/84
     *
     * @param nums
     * @param k
     * @return
     */
    public int lengthOfLISError(int[] nums, int k) {
        // 贪心加二分查找 和300题一样  这个方法报错 比如[1,3,3,4] k=1 正确结果是3,4的len=2 该计算会为1 len=1
        // 设dp[i]为i长度的递增子序列的最小结尾值
        // 详情看300题吧
        int n = nums.length;
        int[] dp = new int[n + 1];
        int len = 1;
        dp[1] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == dp[len]) {
                continue;
            }
            if (nums[i] > dp[len] && nums[i] - dp[len] <= k) {
                dp[++len] = nums[i];
            } else if (nums[i] < dp[len]) {
                // 二分查找
                int left = 1, right = len, index = 0;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (dp[mid] >= nums[i]) {
                        index = mid;
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                dp[index] = nums[i];
            }
        }
        return len;
    }

    public static void main(String[] args) {
        new TwoFourZeroSeven().lengthOfLIS1(new int[]{4, 2, 1, 4, 3, 4, 5, 8, 15}, 3);
    }
}
