package leetcode.array.sort;

import java.util.Arrays;

/**
 * H指数
 *
 * @author chenmo
 * @date 2025/3/25
 */
public class TwoSevenFour {

    public int hIndex(int[] citations) {
        // 计数排序 时间复杂度O(N) 空间复杂度O(N)
        // 用额外的aux[n]来记录超过n大小的数字 因为结果最大也就是n了
        int n = citations.length;
        int[] aux = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n) {
                aux[n]++;
            } else {
                aux[citations[i]]++;
            }
        }
        int total = 0;
        for (int i = n; i >= 0; i--) {
            total += aux[i];
            if (total >= i) {
                return i;
            }
        }
        return 0;
    }

    public int hIndex1(int[] citations) {
        // 普通排序 时间复杂度O(nlogN) 空间复杂度O(1)
        Arrays.sort(citations);
        int res = 0;
        for (int i = 0; i < citations.length; i++) {
            int curRes = Math.min(citations[i], citations.length - i);
            res = Math.max(curRes, res);
        }
        return res;
    }
}
