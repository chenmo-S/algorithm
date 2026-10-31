package leetcode.greedy;

/**
 * 递增的三元子序列
 *
 * @author chenmo
 * @date 2025/4/30
 */
public class ThreeThreeFour {

    public boolean increasingTriplet(int[] nums) {
        // 贪心思想 使用两个变量降低空间复杂度
        // 时间复杂度O(N) 空间复杂度O(1)
        // 使用one two表示三元组的前两个数字 为了获取递增的三元组 我们需要使one和two尽可能的小
        if (nums.length < 3) {
            return false;
        }
        int one = nums[0], two = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > two) {
                return true;
            } else if (nums[i] < two) {
                if (nums[i] > one) {
                    // 尽量减小two
                    two = nums[i];
                }
                if (nums[i] < one) {
                    // 尽量减小one
                    one = nums[i];
                }

            }
        }
        return false;
    }

    public boolean increasingTriplet1(int[] nums) {
        // 对每一个下标求其左侧最小值以及右侧最大值
        // 然后遍历所有遍历所有下标 如果满足条件即可返回否则返回false
        // 时间复杂度O(N) 空间复杂度O(N)
        int n = nums.length;
        int[] left = new int[n];
        left[0] = nums[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.min(left[i - 1], nums[i]);
        }
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], nums[i]);
        }

        // 遍历所有位置
        for (int i = 1; i < n - 1; i++) {
            if (left[i] < nums[i] && right[i] > nums[i]) {
                return true;
            }
        }
        return false;
    }
}
