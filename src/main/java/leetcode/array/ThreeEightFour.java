package leetcode.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

/**
 * 打乱数组
 *
 * @author chenmo
 * @date 2025/5/12
 */
public class ThreeEightFour {

    /**
     * 洗牌算法 时间复杂度O(N) 还需要一个数组保留原数组 空间复杂度O(N)
     */
    class Solution {
        /**
         * 保存原始数组
         */
        int[] original;
        /**
         * 打乱数组
         */
        int[] shuffle;

        Random random;

        public Solution(int[] nums) {
            this.original = nums;
            this.shuffle = new int[nums.length];
            System.arraycopy(nums, 0, shuffle, 0, nums.length);
            this.random = new Random();
        }

        public int[] reset() {
            return original;
        }

        public int[] shuffle() {
            // 洗牌算法 从后向前遍历 将随机数和当前下标进行交换
            for (int i = shuffle.length - 1; i >= 0; i--) {
                int random = this.random.nextInt(i + 1);
                if (random != i) {
                    int tmp = shuffle[i];
                    shuffle[i] = shuffle[random];
                    shuffle[random] = tmp;
                }
            }
            return shuffle;
        }
    }

    /**
     * 使用回溯枚举全排列 超时
     */
    class Solution1 {
        /**
         * 保存原始数组
         */
        int[] original;
        /**
         * 保留所有可能的打乱数组 唯一的代价是初始化会慢一些 但是试题中只会调用一次初始化方法
         */
        List<int[]> shuffle;

        Random random;

        public Solution1(int[] nums) {
            this.original = nums;
            this.random = new Random();
            this.shuffle = initShuffle(original);
        }

        public int[] reset() {
            return original;
        }

        public int[] shuffle() {
            return shuffle.get(random.nextInt(shuffle.size()));
        }

        /**
         * 本质上就是全排列
         *
         * @return
         */
        private List<int[]> initShuffle(int[] arr) {
            List<int[]> res = new ArrayList<>();
            backTracking(res, arr, new ArrayDeque<>(), new boolean[arr.length]);
            return res;
        }

        private void backTracking(List<int[]> res, int[] arr, Deque<Integer> deque, boolean[] visited) {
            if (deque.size() == arr.length) {
                res.add(deque.stream().mapToInt(Integer::intValue).toArray());
                return;
            }
            for (int i = 0; i < arr.length; i++) {
                if (visited[i]) {
                    continue;
                }
                deque.add(arr[i]);
                visited[i] = true;
                backTracking(res, arr, deque, visited);
                visited[i] = false;
                deque.removeLast();
            }
        }
    }
}
