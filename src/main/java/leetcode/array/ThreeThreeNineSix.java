package leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 使数组元素互不相同所需的最少操作次数
 *
 * @author chenmo
 * @date 2025/4/8
 */
public class ThreeThreeNineSix {

    public int minimumOperations(int[] nums) {
        // 倒序遍历
        Set<Integer> set = new HashSet<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (!set.add(nums[i])) {
                return i / 3 + 1;
            }
        }
        return 0;
    }

    public int minimumOperations1(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.merge(num, 1, Integer::sum);
        }
        countMap.entrySet().removeIf(integerIntegerEntry -> integerIntegerEntry.getValue() == 1);
        if (countMap.size() == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i += 3) {
            res++;
            for (int j = 0; j < 3; j++) {
                if (countMap.containsKey(nums[i + j])) {
                    if (countMap.merge(nums[i + j], -1, Integer::sum) == 1) {
                        countMap.remove(nums[i + j]);
                        if (countMap.size() == 0) {
                            return res;
                        }
                    }
                }
            }
        }
        return res;
    }
}
