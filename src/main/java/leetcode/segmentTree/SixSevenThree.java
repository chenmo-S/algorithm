package leetcode.segmentTree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 最长递增子序列的个数<br>
 * 300题的进阶
 *
 * @author chenmo
 * @date 2025/3/6
 */
public class SixSevenThree {

    public int findNumberOfLIS(int[] nums) {
        // 贪心+前缀和+二分查找 还是300题的进阶版本
        // 时间复杂度O(NlogN) 空间复杂度O(N)
        // 需要两个二维数组
        // dp[i]本身是个数组 里面保存最长自增子序列的长度为i时所有可选的结尾数 可得该数组是有序的单调非增
        // count[i][j]代表对应dp[i][j]的个数 也就是最后将count[maxLen]的所有值加起来就是最终结果
        // count[i]可以使用前缀和记录 即最终结果可以优化为count[maxLen][count[maxLen].length-1]
        // 前缀和我们要考虑开头 所以需要提前放置一个0 这样count[i]的下标会比dp[i]的下标大一 切记
        // 由于每次都是添加到末尾 所以我们使用List
        ArrListEx<ArrListEx<Integer>> dp = new ArrListEx<>();
        ArrListEx<ArrListEx<Integer>> count = new ArrListEx<>();
        dp.add(new ArrListEx<>(nums[0]));
        count.add(new ArrListEx<>(0, 1));
        for (int i = 1; i < nums.length; i++) {
            int lastValue = dp.last().last();
            if (nums[i] == lastValue) {
                ArrListEx<Integer> listEx = count.last();
                if (dp.size() == 1) {
                    // 只有一个的情况只需要+1
                    listEx.set(listEx.size() - 1, listEx.last() + 1);
                } else {
                    // 需要加上上一个所有小于该数的个数
                    int index = this.searchLessOrEq(dp.get(dp.size() - 2), nums[i]);
                    ArrListEx<Integer> preCount = count.get(count.size() - 2);
                    listEx.set(listEx.size() - 1, listEx.last() + preCount.last() - preCount.get(index));
                }
            } else if (nums[i] > lastValue) {
                // maxLen变大
                // 找到第一个小于nums[i]的下标
                int index = this.searchLessOrEq(dp.last(), nums[i]);
                dp.add(new ArrListEx<>(nums[i]));
                count.add(new ArrListEx<>(0, count.last().last() - count.last().get(index)));
            } else {
                // 找到nums[i]应该插入的位置 第一个大于等于nums[i] 这里可以使用二分查找
                int left = 0, right = dp.size() - 1, index = 0;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    // 每次和dp[mid]的last比
                    if (dp.get(mid).last() >= nums[i]) {
                        index = mid;
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                dp.get(index).add(nums[i]);
                // 然后我们需要找到dp[index-1]中最后一个小于等于nums[i]的来确定count[i][nums[i]]的值
                // 依然可以二分查找
                if (index == 0) {
                    // 没有上一个了
                    ArrListEx<Integer> listEx = count.get(index);
                    listEx.add(listEx.last() + 1);
                } else {
                    // 找到第一个小于的值 加上即可
                    int index1 = this.searchLessOrEq(dp.get(index - 1), nums[i]);
                    count.get(index).add(count.get(index - 1).last() - count.get(index - 1).get(index1) + count.get(index).last());
                }
            }
        }
        return count.last().last();
    }

    private int searchLessOrEq(ArrListEx<Integer> list, int target) {
        // 二分查找 找到第一个小于target的下标(这里不能等于 等于不是递增序列) list是有序单调非增的
        int left = 0, right = list.size() - 1, res = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (list.get(mid) < target) {
                res = mid;
                right = right - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private static class ArrListEx<T> extends ArrayList<T> {
        private static final long serialVersionUID = 5055435609149330874L;

        @SafeVarargs
        ArrListEx(T... initValue) {
            this.addAll(Arrays.asList(initValue));
        }

        public T last() {
            return this.get(this.size() - 1);
        }
    }

    public int findNumberOfLIS1(int[] nums) {
        // 动态规划 时间复杂度O(N^2) 空间复杂度O(N)
        // 设dp[i]为以i为结尾的递增子序列的最大长度
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int len = 1;
        // 额外使用一个数组记录以i为结尾的最长序列的个数
        int[] count = new int[n + 1];
        Arrays.fill(count, 1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        // 重置计数
                        count[i] = count[j];
                        dp[i] = dp[j] + 1;
                    } else if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }
            if (dp[i] > len) {
                // 更新
                len = dp[i];
                res = count[i];
            } else if (dp[i] == len) {
                res += count[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(new SixSevenThree().findNumberOfLIS(new int[]{3, 1, 2}));
//        System.out.println(new SixSevenThree().findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
//        System.out.println(new SixSevenThree().findNumberOfLIS(new int[]{1, 2, 4, 3, 5, 4, 7, 2}));
        System.out.println(new SixSevenThree().findNumberOfLIS(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3}));
    }
}
