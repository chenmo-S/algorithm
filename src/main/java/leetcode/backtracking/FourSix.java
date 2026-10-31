package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 *
 * @author chenmo
 * @date 2024/8/22
 */
public class FourSix {

    public List<List<Integer>> permute(int[] nums) {
        // 去掉visited数组 使用下标交换
        // 使用index记录当前待交换的下标 index前的下标代表已经确定了具体的数字
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        for (int num : nums) {
            subList.add(num);
        }
        backTracking(res, subList, nums, 0);
        return res;
    }

    private void backTracking(List<List<Integer>> res, List<Integer> subList, int[] nums, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>(subList));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            // 用交换代表将i的数字填充到当前位置
            Collections.swap(subList, index, i);
            backTracking(res, subList, nums, index + 1);
            // 交换回来
            Collections.swap(subList, index, i);
        }
    }

    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTracking(res, new LinkedList<>(), nums, new boolean[nums.length]);
        return res;
    }

    private void backTracking(List<List<Integer>> res, LinkedList<Integer> subList, int[] nums, boolean[] visited) {
        if (subList.size() == nums.length) {
            res.add(new ArrayList<>(subList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                subList.add(nums[i]);
                visited[i] = true;
                backTracking(res, subList, nums, visited);
                visited[i] = false;
                subList.removeLast();
            }
        }
    }
}
