package leetcode.twopointer;

import util.ArrayUtil;

/**
 * 颜色分类
 *
 * @author chenmo
 * @date 2024/9/3
 */
public class SevenFive {

    public void sortColors(int[] nums) {
        // 双指针 时间复杂度O(N)
        // 分三段 设[0,left)为0  (right,n-1]为2
        int left = 0, i = 0, right = nums.length - 1;
        while (i <= right) {
            if (nums[i] == 0) {
                ArrayUtil.swap(nums, left, i);
                left++;
                i++;
            } else if (nums[i] == 2) {
                ArrayUtil.swap(nums, i, right);
                right--;
                // 不确定从right换过来的值 i不动
            } else {
                i++;
            }
        }
    }

    public void sortColors3(int[] nums) {
        // 一次遍历
        int left = 0, right = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            while (right > i && nums[i] == 2) {
                while (right > i && nums[right] == 2) {
                    right--;
                }
                ArrayUtil.swap(nums, i, right);
            }
            while (left < i && nums[i] == 0) {
                while (left < i && nums[left] == 0) {
                    left++;
                }
                ArrayUtil.swap(nums, i, left);
            }
        }
    }

    public void sortColors2(int[] nums) {
        // 一次遍历 时间复杂度O(N) 空间复杂度O(1)
        int left = 0, right = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            while (i < right && nums[i] == 2) {
                ArrayUtil.swap(nums, i, right);
                right--;
            }
            // 通过上面的代码可以保证此时nums[left]不可能为2
            if (nums[i] == 0) {
                ArrayUtil.swap(nums, i, left);
                left++;
            }
        }
    }

    public void sortColors1(int[] nums) {
        // 二次遍历 第一次调整2，第二次调整1 时间复杂度O(N) 空间复杂度O(1)
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (nums[right] == 2 && left < right) {
                right--;
            }
            while (nums[left] != 2 && left < right) {
                left++;
            }
            if (left < right) {
                ArrayUtil.swap(nums, left, right);
            }
        }
        left = 0;
        while (left < right) {
            while (nums[right] == 1 && left < right) {
                right--;
            }
            while (nums[left] != 1 && left < right) {
                left++;
            }
            if (left < right) {
                ArrayUtil.swap(nums, left, right);
            }
        }
    }
}
