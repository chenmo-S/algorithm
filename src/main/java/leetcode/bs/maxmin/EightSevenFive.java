package leetcode.bs.maxmin;

/**
 * 爱吃香蕉的珂珂
 *
 * @author chenmo
 * @date 2025/4/27
 */
public class EightSevenFive {

    public int minEatingSpeed(int[] piles, int h) {
        // 最小化最大值问题 二分查找 二分的范围为[1,max]
        // 时间复杂度O(NlogM) 空间复杂度O(1)
        int max = Integer.MIN_VALUE;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        int left = 1, right = max;
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 统计mid是否可以满足
            if (getHour(piles, mid) > h) {
                left = mid + 1;
            } else {
                res = mid;
                right = mid - 1;
            }
        }
        return res;
    }

    private long getHour(int[] piles, int speed) {
        long res = 0;
        for (int pile : piles) {
            // 可以涵盖pile%speed==0和pile%speed!=0
            int curTime = (pile + speed - 1) / speed;
            res += curTime;
        }
        return res;
    }
}
