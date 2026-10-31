package leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计最大元素至少出现K次的子数组
 *
 * @author chenmo
 * @date 2025/4/30
 */
public class TwoNineSixTwo {

    public long countSubarrays(int[] nums, int k) {
        // 使用Map记录最大元素的所有下标
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(nums[i], max);
            indexMap.computeIfAbsent(nums[i], v -> new ArrayList<>()).add(i);
        }
        List<Integer> list = indexMap.get(max);
        if (list.size() < k) {
            return 0;
        }
        long res = 0;
        // 滑动窗口遍历list
        int left = 0;
        while (left <= list.size() - k) {
            res += list.get(left) + 1;

            left++;
        }

        return res;
    }
}
