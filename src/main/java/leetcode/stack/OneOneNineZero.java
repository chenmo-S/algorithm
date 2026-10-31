package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 反转每对括号间的子串
 *
 * @author chenmo
 * @date 2025/4/10
 */
public class OneOneNineZero {

    public String reverseParentheses(String s) {
        // 使用栈 时间复杂度O(N) 空间复杂度O(N)
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (c == ')') {
                sb.reverse().insert(0, stack.pop());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
