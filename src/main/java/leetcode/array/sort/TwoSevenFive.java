package leetcode.array.sort;

/**
 * H指数Ⅱ
 *
 * @author chenmo
 * @date 2025/3/25
 */
public class TwoSevenFive {

    public int hIndex(int[] citations) {
        // 题目给出的排序数组 肯定是要求我们时间复杂度O(logN)的
        // 二分查找 时间复杂度O(logN) 空间复杂度O(1)
        int res = 0;
        // 我们的目标是找第一个大于等于n-i的值
        int n = citations.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (citations[mid] >= n - mid) {
                res = Math.min(citations[mid], n - mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public int hIndex1(int[] citations) {
        // 时间复杂度O(N) 空间复杂度O(1)
        int res = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            int curRes = Math.min(citations[i], citations.length - i);
            res = Math.max(res, curRes);
        }
        return res;
    }
}
