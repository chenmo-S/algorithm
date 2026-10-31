package leetcode.array.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * 四数相加Ⅱ
 *
 * @author chenmo
 * @date 2025/4/29
 */
public class FourFiveFour {

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 分组使用hash表 时间复杂度O(N^2) 空间复杂度O(N^2)
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i : nums1) {
            for (int j : nums2) {
                countMap.merge(i + j, 1, Integer::sum);
            }
        }
        // 然后对nums3和nums4组合进行遍历 就跟第一题两数之和类似
        int res = 0;
        for (int i : nums3) {
            for (int j : nums4) {
                res += countMap.getOrDefault((-i - j), 0);
            }
        }
        return res;
    }
}
