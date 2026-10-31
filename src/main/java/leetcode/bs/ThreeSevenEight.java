package leetcode.bs;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 有序矩阵中第K小的元素
 *
 * @author chenmo
 * @date 2025/3/28
 */
public class ThreeSevenEight {

    public int kthSmallest(int[][] matrix, int k) {
        // 方法1只利用了每行是有序数组的特点
        // 同时利用行列都是有序数组这一特征 可以采用二分查找 时间复杂度O(log(MN)) 空间复杂度O(1)
        // 左上角最小值 右下角最大值
        int m = matrix.length;
        int n = matrix[0].length;
        int left = matrix[0][0], right = matrix[m - 1][n - 1];
        int res = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int count = findCountLessTarget(matrix, mid);
            if (count >= k) {
                res = mid;
                right = mid - 1;
            } else if (count < k) {
                // 在右边
                left = mid + 1;
            }
        }
        return res;
    }

    private int findCountLessTarget(int[][] matrix, int target) {
        // 返回有多少个数字 小于等于target
        int res = 0;
        // 从左下角开始找
        int m = matrix.length, n = matrix[0].length;
        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= target) {
                res += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return res;
    }

    public int kthSmallest1(int[][] matrix, int k) {
        // 我们可以利用每一行都是有序数组的特点进行归并排序 可参考23题的实现
        // 时间复杂度O(KlogN) 空间复杂度O(N)
        // 使用优先队列优化时间复杂度 也就是合并n个有序数组 到达k即可停止
        // 保存的数组长度为3 下标0为val,1为i,2为j
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            priorityQueue.add(new int[]{matrix[i][0], i, 0});
        }
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            if (--k == 0) {
                return poll[0];
            }
            if (poll[2] + 1 < n) {
                priorityQueue.add(new int[]{matrix[poll[1]][poll[2] + 1], poll[1], poll[2] + 1});
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSevenEight().kthSmallest(new int[][]{
                {1, 5, 9}, {10, 11, 13}, {12, 13, 15}
        }, 8));
    }
}
