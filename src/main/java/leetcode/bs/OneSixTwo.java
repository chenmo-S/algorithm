package leetcode.bs;

/**
 * 寻找峰值
 *
 * @author chenmo
 * @date 2025/4/29
 */
public class OneSixTwo {

    public int findPeakElement(int[] nums) {
        // 题目要求时间复杂度为O(logN) 可以采用二分查找
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 判断nums[mid-1],nums[mid],nums[mid+1]三者的关系
            int leftRes = compare(nums, mid - 1, mid);
            int rightRes = compare(nums, mid, mid + 1);
            if (leftRes < 0 && rightRes > 0) {
                return mid;
            } else if (leftRes > 0) {
                // 证明左边至少有一个递增 所以左侧肯定有答案 区间减为左侧
                // 特别注意 这不说明右边没有答案 但是题目只要求返回一个 我们可以确定左侧肯定有 而右侧只是可能有
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private int compare(int[] nums, int i, int j) {
        if (i == -1) {
            return -1;
        }
        if (j == nums.length) {
            return 1;
        }
        // j-i=1 题意nums[i]不可能等于nums[j]
        return nums[i] > nums[j] ? 1 : -1;
    }
}
