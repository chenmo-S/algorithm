package sorts;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author chenmo
 * @date 2024/3/27
 */
public class BubbleSort implements Sort {
    @Override
    public void sort(int[] nums) {
        // 基础版冒泡排序 最优时间复杂度也是O(N^2)
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j - 1] > nums[j]) {
                    swap(nums, j, j - 1);
                }
            }
        }
    }

    public void optimizeSort(int[] nums) {
        // 优化版本 最优时间复杂度O(N)
        for (int i = 0; i < nums.length - 1; i++) {
            // 记录本轮是否发生交换
            boolean swap = false;
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j - 1] > nums[j]) {
                    swap = true;
                    swap(nums, j, j - 1);
                }
            }
            if (!swap) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int len = 100000;
        SortTest.testCost(() -> bubbleSort.sort(SortTest.initNums(len)));
        // 搞一个基本有序的
        int[] nums = SortTest.initNums(len);
        int[] source = new int[nums.length + len];
        System.arraycopy(nums, 0, source, 0, len);
        for (int i = 0; i < len; i++) {
            source[i + len] = 100 * i;
        }
        SortTest.testCost(() -> bubbleSort.optimizeSort(SortTest.initNums(len)));

        int[] copy1 = Arrays.copyOf(source, source.length);
        int[] copy2 = Arrays.copyOf(source, source.length);
        SortTest.testCost(() -> bubbleSort.sort(copy1));
        SortTest.testCost(() -> bubbleSort.optimizeSort(copy2));

    }
}
