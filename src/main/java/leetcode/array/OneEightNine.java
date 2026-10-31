package leetcode.array;

/**
 * 轮转数组
 *
 * @author chenmo
 * @date 2025/5/13
 */
public class OneEightNine {

    public void rotate(int[] nums, int k) {
        // 环状替换 时间复杂度O(N) 空间复杂度O(1)
        int n = nums.length;
        k = k % n;
        if (n == 1 || k == 0) {
            return;
        }
        // 统计已轮转的个数 达到n结束循环
        int count = 0;
        // 起始点
        int startIndex = 0;
        // 当前轮转的数下标以及值
        // 之所以需要额外curNum变量来记录 是因为在循环中nums[curIndex]不一定等于curNum
        int curIndex = 0;
        int curNum = nums[0];
        while (count < n) {
            // 确认当前位置经过轮转后的位置
            int nextIndex = (curIndex + k) % n;
            // 记录当前位置经过轮转后的位置的值，避免被覆盖
            int nextNum = nums[nextIndex];
            // 将当前值赋给轮转后的位置
            nums[nextIndex] = curNum;

            count++;
            // 此时要将临时变量的值nextNum放到正确位置
            // 所以将nextIndex赋值给curIndex进行下一次循环
            curIndex = nextIndex;
            curNum = nextNum;
            // 如果回到了起始点，需要重置起始点 确保循环可以结束 否则会一直在环里死循环
            if (nextIndex == startIndex) {
                startIndex++;
                curIndex = startIndex;
                curNum = nums[curIndex];
            }
        }
    }

    public void rotate2(int[] nums, int k) {
        // 双指针多次翻转 时间复杂度O(N) 空间复杂度O(1)
        int n = nums.length;
        if (n == 1) {
            return;
        }
        k = k % n;
        // 第一次翻转整个数组
        reverse(nums, 0, n - 1);
        // 第二次翻转前k个数字
        reverse(nums, 0, k - 1);
        // 第三次翻转后面的数字
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;

            start++;
            end--;
        }
    }

    public void rotate1(int[] nums, int k) {
        // 借助辅助数组 时间复杂度O(N) 空间复杂度O(N)
        int n = nums.length;
        if (n == 1) {
            return;
        }
        k = k % n;
        int[] aux = new int[n];
        System.arraycopy(nums, 0, aux, 0, n);
        for (int i = 0; i < n; i++) {
            int index = i - k;
            if (index >= 0) {
                nums[i] = aux[index];
            } else {
                nums[i] = aux[n + index];
            }
        }
    }
}
