package sorts;

import java.util.Arrays;
import java.util.Random;

/**
 * @author chenmo
 * @date 2024/3/28
 */
public class RecursionMergeSort extends MergeSort {
    @Override
    public void sort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
    }

    private void mergeSort(int[] nums, int start, int end, int[] helper) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(nums, start, mid, helper);
            mergeSort(nums, mid + 1, end, helper);
            merge(nums, start, mid, end, helper);
        }
    }

    public static void main(String[] args) {
        int[] arrays = SortTest.initNums(1000);
        int[] copy1 = Arrays.copyOf(arrays, arrays.length);
        int[] copy2 = Arrays.copyOf(arrays, arrays.length);
        new IterationMergeSort().sort(copy1);
        iteration(copy2);
        System.out.println(Arrays.equals(copy1, copy2));
    }

    private static void iteration(int[] nums) {
        int n = nums.length;
        int[] helper = new int[n];
        for (int curLen = 1; curLen < n; curLen *= 2) {
            for (int start = 0; start < n; start += 2 * curLen) {
                int leftEnd = Math.min(start + curLen, n) - 1;
                int rightEnd = Math.min(start + 2 * curLen, n) - 1;
                merge1(nums, start, leftEnd, rightEnd, helper);
            }
        }
    }

    private static void merge1(int[] nums, int start, int mid, int end, int[] helper) {
        System.arraycopy(nums, start, helper, start, end - start + 1);
        int x = start, y = mid + 1;
        int index = start;
        while (x <= mid && y <= end) {
            if (helper[x] <= helper[y]) {
                nums[index++] = helper[x];
                x++;
            } else {
                nums[index++] = helper[y];
                y++;
            }
        }
        while (x <= mid) {
            nums[index++] = helper[x++];
        }

        while (y <= end) {
            nums[index++] = helper[y++];
        }
    }

    private void heapSort(int[] nums) {
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            adjustHeap(nums, i, nums.length - 1);
        }
    }

    private void adjustHeap(int[] nums, int index, int endIndex) {
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        int largestIndex = index;
        if (leftIndex <= endIndex && nums[largestIndex] < nums[leftIndex]) {
            largestIndex = leftIndex;
        }
        if (rightIndex <= endIndex && nums[largestIndex] < nums[rightIndex]) {
            largestIndex = rightIndex;
        }
        if (largestIndex != index) {
            adjustHeap(nums, largestIndex, endIndex);
        }
    }

    /**
     * 小顶堆
     */
    private void priorityQueue() {


    }

    /**
     * 自底向上调整堆 用于add
     */
    private void heapUp(int[] nums, int index) {
        int parentIndex = 0;
        while ((parentIndex = (index - 1) / 2) >= 0) {
            if (nums[parentIndex] > nums[index]) {
                // 交换
            } else {
                break;
            }
            index = parentIndex;
        }
    }
}
