package leetcode.greedy;

import java.util.Arrays;

/**
 * 分发糖果
 *
 * @author chenmo
 * @date 2025/3/26
 */
public class OneThreeFive {

    public int candy(int[] ratings) {
        // 一次遍历 时间复杂度O(N) 空间复杂度O(1)
        // 从左到右枚举每一个同学，记前一个同学分得的糖果数量为 pre
        // 如果处于递增序列 则将当前改为pre+1
        // 如果处于递减序列 则将当前分配为1 并将之前所有递减的值都+1 (因为上一个递减可能刚分配为1)
        // 但是特别的 当递增序列=递减序列的长度时 我们要将递增序列的最后一个值也加入递减序列
        int sum = 1, pre = 1, dec = 0, inc = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                // 计算当前的糖果数 相等的情况下可以只分一个
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                sum += pre;
                inc = pre;
            } else {
                // 处于递减序列
                dec++;
                if (dec == inc) {
                    dec++;
                }
                pre = 1;
                sum += dec;
            }
        }
        return sum;
    }

    public int candy1(int[] ratings) {
        // 两次遍历 时间复杂度O(N) 空间复杂度O(N)
        int n = ratings.length;
        int[] left = new int[n];
        Arrays.fill(left, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        int[] right = new int[n];
        Arrays.fill(right, 1);
        int res = left[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
            res += Math.max(left[i], right[i]);
        }
        return res;
    }
}
