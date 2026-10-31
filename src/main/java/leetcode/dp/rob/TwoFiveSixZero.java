package leetcode.dp.rob;

/**
 * 打家劫舍Ⅳ
 *
 * @author chenmo
 * @date 2025/4/22
 */
public class TwoFiveSixZero {

    public int minCapability(int[] nums, int k) {
        // 结果是求小偷在保证至少窃取k个房屋的情况下 能从单个房间中窃取的最大金额
        // 所以res取值范围为[min,max] 二分法
        // 时间复杂度O(Nlog(M)) 空间复杂度O(1) N为数组的长度 M为max-min
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int res = 0;
        while (min <= max) {
            int mid = min + ((max - min) >> 1);
            // 统计可偷取的房屋数
            int count = 0;
            boolean visited = false;
            for (int num : nums) {
                // 把mid当做可窃取的最大金额
                // 要有间隔 使用visited来确认
                if (num <= mid && !visited) {
                    count++;
                    visited = true;
                } else {
                    // 代表下一个房屋可以访问
                    visited = false;
                }
            }
            if (count >= k) {
                // 满足条件 暂存结果
                res = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new TwoFiveSixZero().minCapability(new int[]{2, 7, 9, 3, 1}, 2));
    }
}
