package leetcode.bs.maxmin;

/**
 * @author chenmo
 * @date 2025/4/29
 */
public class OneZeroOneOne {

    public int shipWithinDays(int[] weights, int days) {
        // 最小化最大值问题 二分查找
        // 二分查找的边界 [max,sum] 时间复杂度O(NlogSum) 空间复杂度O(1) M为sum-max
        int sum = 0, max = Integer.MIN_VALUE;
        for (int weight : weights) {
            sum += weight;
            max = Math.max(weight, max);
        }
        int left = max, right = sum;
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 判断在mid的限制下是否可以在days天内运输完成
            if (getDays(weights, mid) > days) {
                // 无法完成
                left = mid + 1;
            } else {
                // 可以完成 尝试压缩max
                res = mid;
                right = mid - 1;
            }
        }
        return res;
    }

    private int getDays(int[] weights, int max) {
        int res = 0;
        int sum = 0;
        for (int weight : weights) {
            if (sum + weight > max) {
                res++;
                sum = weight;
            } else {
                sum += weight;
            }
        }
        // 最后一个区间要加一
        return ++res;
    }

    public static void main(String[] args) {
        System.out.println(new OneZeroOneOne().getDays(new int[]{1, 2, 3, 1, 1}, 2));
    }
}
