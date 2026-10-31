package leetcode.math;

/**
 * 字符串转换整数(atoi)
 *
 * @author chenmo
 * @date 2024/7/17
 */
public class Eight {

    public int myAtoi(String s) {
        // 直接构造 时间复杂度O(N) 空间复杂度O(1)
        int index = 0;
        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }
        if (index == s.length()) {
            return 0;
        }
        // 判断第一个字符
        char first = s.charAt(index);
        int flag = 1;
        int res = 0;
        if (first == '-') {
            flag = -1;
            index++;
        } else if (first == '+') {
            flag = 1;
            index++;
        } else if (first < '0' || first > '9') {
            return 0;
        }
        int v1 = Integer.MAX_VALUE / 10;
        int v2 = Integer.MIN_VALUE / 10;
        int remainder1 = Integer.MAX_VALUE % 10;
        // 变成正数 好和个位数比较
        int remainder2 = -(Integer.MIN_VALUE % 10);
        // 从下标1开始
        while (index < s.length()) {
            char c = s.charAt(index);
            // 截断 直接返回
            if (c < '0' || c > '9') {
                return flag * res;
            }
            int num = c - '0';
            // 乘十大于或者加上个位数大于 则溢出
            if (flag > 0 && (res > v1 || (res == v1 && num >= remainder1))) {
                return Integer.MAX_VALUE;
            }
            if (flag < 0 && (-res < v2 || (-res == v2 && num >= remainder2))) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + num;
            index++;
        }
        return flag * res;
    }

    public int myAtoi1(String s) {
        // 不使用库函数 时间复杂度O(N) 空间复杂度O(1)
        if (s == null) {
            return 0;
        }
        int res = 0, sign = 1;
        int floor = Integer.MAX_VALUE / 10;
        int remainder = Integer.MAX_VALUE % 10;
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                // 前导空格
                if (flag) {
                    continue;
                } else {
                    break;
                }
            }
            if (c >= '0' && c <= '9') {
                int i1 = c - '0';
                if ((res == floor && i1 > remainder) || res > floor) {
                    return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                res = (res * 10) + i1;
            } else {
                if (flag && (c == '-' || c == '+')) {
                    sign = c == '-' ? -1 : 1;
                } else {
                    break;
                }
            }
            flag = false;
        }
        return res * sign;
    }

    public static void main(String[] args) {
        System.out.println(new Eight().myAtoi("042"));
        System.out.println(new Eight().myAtoi("     -42"));
        System.out.println(new Eight().myAtoi("-123456789987456123"));
    }
}
