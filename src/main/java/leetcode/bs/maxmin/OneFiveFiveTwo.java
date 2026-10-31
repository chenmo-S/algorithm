package leetcode.bs.maxmin;

import java.util.Arrays;

/**
 * 两球之间的磁力
 *
 * @author chenmo
 * @date 2025/4/28
 */
public class OneFiveFiveTwo {

    public int maxDistance(int[] position, int m) {
        // 最大化最小值问题 二分查找
        // 首先排序 求min和max 时间复杂度O(NlogM) 空间复杂度O(logN) 排序的空间复杂度 M是max-min 一般会比N大
        Arrays.sort(position);
        int n = position.length;
        int max = position[n - 1] - position[0];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            min = Math.min(min, position[i] - position[i - 1]);
        }
        int left = min, right = max;
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 判断mid是否符合条件
            if (getCount(position, mid) >= m) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private int getCount(int[] position, int distance) {
        int res = 1;
        int pre = position[0];
        for (int i = 1; i < position.length; i++) {
            if (position[i] - pre >= distance) {
                res++;
                pre = position[i];
            }
        }
        return res;
    }
}
