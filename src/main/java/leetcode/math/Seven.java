package leetcode.math;

/**
 * 整数反转
 *
 * @author chenmo
 * @date 2026/9/29
 */
public class Seven {

    public static int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        // 不用记录正负数 因为/和%保留符号
        int v1 = Integer.MAX_VALUE / 10;
        int v2 = Integer.MIN_VALUE / 10;
        int remainder1 = Integer.MAX_VALUE % 10;
        int remainder2 = Integer.MIN_VALUE % 10;
        int res = 0;
        while (x != 0) {
            if (res > v1 || res < v2) {
                return 0;
            }
            // 确认余数是否越界
            int remainder = x % 10;
            if ((res == v1 && remainder > remainder1)
                    || (res == v2 && remainder < remainder2)) {
                return 0;
            }
            res = res * 10 + remainder;
            x /= 10;
        }
        return res;
    }
}
