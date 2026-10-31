package leetcode.twopointer;

import java.util.Arrays;

/**
 * 最接近的三数之和
 *
 * @author chenmo
 * @date 2025/4/21
 */
public class OneSix {

    public int threeSumClosest(int[] nums, int target) {
        // 排序+双指针 时间复杂度O(N^2) 空间复杂度O(1)
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    // 正好
                    return target;
                } else if (sum < target) {
                    if (target - sum < diff) {
                        diff = target - sum;
                        res = sum;
                    }
                    left++;
                } else {
                    if (sum - target < diff) {
                        diff = sum - target;
                        res = sum;
                    }
                    right--;
                }

            }
        }
        return res;
    }
}
