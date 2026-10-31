package leetcode.greedy;

/**
 * 加油站
 *
 * @author chenmo
 * @date 2025/3/26
 */
public class OneThreeFour {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 贪心 时间复杂度O(N) 空间复杂度O(1)
        // 我们可以遍历每一个下标并尝试是否可以环游一圈
        // 当出现不能环游一圈时 我们需要从中断的地方+1开始再判断
        // 因为如果起点i不能到达 证明i到中断点中间的每一个节点都不能跨越中断点
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int sumOfGas = 0, sumOfCost = 0;
            // 记录可跨越的节点数
            int cnt = 0;
            while (cnt < n) {
                int index = (i + cnt) % n;
                sumOfGas += gas[index];
                sumOfCost += cost[index];
                if (sumOfGas < sumOfCost) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                // 外部循环还会+1
                i = i + cnt;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new OneThreeFour().canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    }
}
