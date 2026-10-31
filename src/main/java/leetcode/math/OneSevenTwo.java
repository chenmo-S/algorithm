package leetcode.math;

/**
 * 阶乘后的零
 *
 * @author chenmo
 * @date 2025/5/20
 */
public class OneSevenTwo {

    public int trailingZeroes(int n) {
        // 只有2*5才会产生0 所以该题就是求2和5中的较小值 而2的次数肯定大于等于5 只需要求5即可
        for (int i = 5; i <= n; i += 5) {

        }
        return 1;
    }

}
