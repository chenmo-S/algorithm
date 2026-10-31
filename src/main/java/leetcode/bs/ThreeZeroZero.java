package leetcode.bs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 最长递增子序列
 *
 * @author chenmo
 * @date 2024/9/10
 */
public class ThreeZeroZero {

    public int lengthOfLIS(int[] nums) {
        // 进阶 贪心+二分查找 时间复杂度O(NlogN) 空间复杂度O(N)
        // 想要求得尽可能长的递增序列 则每次加入的新值都要尽可能的小
        // 设数组dp[i]表示长度为i的递增序列的最小的结尾值 设len为此时的最大长度
        // 可得dp[i]是单调递增的的 对于nums[i]>dp[len],dp[++len]=nums[i];
        // 如果nums[i]<dp[len] 则遍历dp数组找到第一个大于nums[i]的位置index然后更新
        // 因为dp数组是单调递增的 所以可以二分查找
        int n = nums.length;
        int[] dp = new int[n + 1];
        int len = 1;
        dp[1] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == dp[len]) {
                continue;
            }
            if (nums[i] > dp[len]) {
                dp[++len] = nums[i];
            } else {
                // nums[i]<dp[len] 为了编码便利 dp[0]没用到 left要赋值为1
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

    public int lengthOfLIS1(int[] nums) {
        // 动态规划 时间复杂度O(N^2) 空间复杂度O(N)
        // 设dp[i]为以i位置数字为结尾的自增子序列最大长度
        int[] dp = new int[nums.length];
        // 所有的单个数字都是1
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 进阶 返回最长自增子序列的内容
     */
    static class Advance {

        /**
         * 贪心加二分查找的实现
         *
         * @param nums
         * @return
         */
        public int[] lengthOfLIS(int[] nums) {
            // 进阶 贪心+二分查找 时间复杂度O(NlogN) 空间复杂度O(N)
            // 想要求得尽可能长的递增序列 则每次加入的新值都要尽可能的小
            // 设数组dp[i]表示长度为i的递增序列的最小的结尾值 设len为此时的最大长度
            // 可得dp[i]是单调递增的的 对于nums[i]>dp[len],dp[++len]=nums[i];
            // 如果nums[i]<dp[len] 则遍历dp数组找到第一个大于nums[i]的位置index然后更新
            // 因为dp数组是单调递增的 所以可以二分查找
            int n = nums.length;
            int[] dp = new int[n + 1];
            int len = 1;
            dp[1] = nums[0];
            // 额外使用一个Map记录序列
            Map<Integer, List<Integer>> seqMap = new HashMap<>();
            // 先把len=1放进去 直接用匿名内部类得了
            seqMap.put(1, new ArrayList<Integer>() {
                private static final long serialVersionUID = -9026730200683211791L;

                {
                    add(nums[0]);
                }
            });
            for (int i = 1; i < n; i++) {
                if (nums[i] == dp[len]) {
                    continue;
                }
                if (nums[i] > dp[len]) {
                    dp[++len] = nums[i];
                    // len不会变小 这里肯定是absent
                    List<Integer> seqList = seqMap.computeIfAbsent(len, k -> new ArrayList<>());
                    seqList.addAll(seqMap.get(len - 1));
                    seqList.add(nums[i]);
                } else {
                    // nums[i]<dp[len] 为了编码便利 dp[0]没用到 left要赋值为1
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
                    // 修改index长度的list的最后一个值
                    List<Integer> seqList = seqMap.get(index);
                    seqList.set(seqList.size() - 1, nums[i]);
                }
            }
            return seqMap.get(len).stream().mapToInt(Integer::intValue).toArray();
        }

        /**
         * 动态规划的实现
         *
         * @param nums
         * @return
         */
        public int[] lengthOfLIS1(int[] nums) {
            // 动态规划 时间复杂度O(N^2) 空间复杂度O(N)
            // 设dp[i]为以i位置数字为结尾的自增子序列最大长度
            int[] dp = new int[nums.length];
            // 所有的单个数字都是1
            Arrays.fill(dp, 1);
            // 提供一个数组记录前一个元素的索引
            int[] preIndex = new int[nums.length];
            // 默认没有前一个元素
            Arrays.fill(preIndex, -1);
            // 记录最长递增子序列的结尾索引
            int endIndex = 0;
            int len = 1;
            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    // 同时符合这两个条件才会更新dp[i]
                    if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        preIndex[i] = j;
                    }
                }
                if (dp[i] > len) {
                    len = dp[i];
                    endIndex = i;
                }
            }
            int[] res = new int[len];
            for (int i = endIndex; i != -1; i = preIndex[i]) {
                res[--len] = nums[i];
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ThreeZeroZero().lengthOfLIS(new int[]{10, 9, 2, 4, 6, 101, 5}));
        System.out.println(Arrays.toString(new Advance().lengthOfLIS(new int[]{10, 9, 2, 4, 6, 101, 5})));
        System.out.println(Arrays.toString(new Advance().lengthOfLIS1(new int[]{10, 9, 2, 4, 6, 101, 5})));
    }
}
