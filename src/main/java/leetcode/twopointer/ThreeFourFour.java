package leetcode.twopointer;

/**
 * 反转字符串
 *
 * @author chenmo
 * @date 2025/4/2
 */
public class ThreeFourFour {

    public void reverseString(char[] s) {
        // 简单题 双指针 时间复杂度O(N) 空间复杂度O(1)
        int left = 0, right = s.length - 1;
        while (left < right) {
            swap(s, left++, right--);
        }
    }

    private void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }
}
