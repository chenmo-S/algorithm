package leetcode.array.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 最大数
 *
 * @author chenmo
 * @date 2025/5/12
 */
public class OneSevenNine {

    public String largestNumber(int[] nums) {
        //  将数组转换为字符串数组然后进行排序
        // 时间复杂度O(NlogN) 空间复杂度O(N)
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(arr, (s1, s2) -> {
            String one = s1 + s2;
            String two = s2 + s1;
            return two.compareTo(one);
        });

        StringBuilder res = new StringBuilder();
        for (String s : arr) {
            res.append(s);
        }
        return res.toString();
    }
}
