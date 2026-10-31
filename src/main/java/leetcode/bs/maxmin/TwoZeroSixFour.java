package leetcode.bs.maxmin;

/**
 * 分配给商店的最多商品的最小值
 *
 * @author chenmo
 * @date 2025/4/28
 */
public class TwoZeroSixFour {

    public int minimizedMaximum(int n, int[] quantities) {
        // 最小化最大值问题 二分查找
        // 一家商店最多一种商品 则其取值为[1,max]
        // 时间复杂度O(NlogM) 空间复杂度O(1) M为max
        int max = Integer.MIN_VALUE;
        for (int quantity : quantities) {
            max = Math.max(max, quantity);
        }
        int left = 1, right = max;
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 判断mid是否满足条件 可以全部分配
            if (needShopCount(mid, quantities) <= n) {
                // 满足条件
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private int needShopCount(int max, int[] quantities) {
        int count = 0;
        for (int quantity : quantities) {
            // 利用计算机整除机制 合并是否是max的整数倍
            count += (quantity + max - 1) / max;
        }
        return count;
    }
}
