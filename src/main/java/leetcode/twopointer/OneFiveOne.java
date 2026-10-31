package leetcode.twopointer;

/**
 * 反转字符串中的单词
 *
 * @author chenmo
 * @date 2025/4/2
 */
public class OneFiveOne {

    public String reverseWords(String s) {
        // 双指针 时间复杂度O(N) 空间复杂度O(N)
        StringBuilder sb = new StringBuilder();
        int left = 0, right = s.length() - 1;
        while (right >= 0) {
            while (right >= 0 && s.charAt(right) == ' ') {
                right--;
            }
            if (right < 0) {
                return sb.substring(0, sb.length() - 1);
            }
            left = right;
            while (left >= 0 && s.charAt(left) != ' ') {
                left--;
            }
            sb.append(s, left + 1, right + 1).append(" ");
            right = left;
        }
        return sb.substring(0, sb.length() - 1);
    }
}
