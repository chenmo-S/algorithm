package leetcode.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字符串解码
 *
 * @author chenmo
 * @date 2024/8/14
 */
public class ThreeNineFour {

    public String decodeString(String s) {
        // 栈 时间复杂度O(N) 空间复杂度O(N)
        Deque<String> deque = new ArrayDeque<>();
        // 用来保存数字
        StringBuilder num = new StringBuilder();
        // 用来保存字符串
        List<String> str = new ArrayList<>();
        // 遇到数字以及左括号以及字母都入栈 遇到右括号开始出栈 直到遇到左括号 然后将数字取出并把此时拼接成的字符串入栈
        // 则最后从栈底到栈顶的字符串拼接就是结果 所以我们可使用Deque
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num.append(c);
            } else if (c == ']') {
                // 特殊处理 此时deque肯定不为空
                while (!deque.peekFirst().equals("[")) {
                    str.add(deque.pollFirst());
                }
                // 弹出"["
                deque.pollFirst();
                // 弹出数字 此时一定有
                int count = Integer.parseInt(deque.pollFirst());
                StringBuilder dealStr = new StringBuilder();
                for (int k = str.size() - 1; k >= 0; k--) {
                    dealStr.append(str.get(k));
                }
                StringBuilder countStr = new StringBuilder();
                for (int j = 0; j < count; j++) {
                    countStr.append(dealStr);
                }
                deque.addFirst(countStr.toString());
                // 重置
                str.clear();
            } else {
                // 数字入栈 并将num重置
                if (num.length() > 0) {
                    deque.addFirst(num.toString());
                    num.setLength(0);
                }
                deque.addFirst(String.valueOf(c));
            }
        }
        StringBuilder res = new StringBuilder();
        while (!deque.isEmpty()) {
            res.append(deque.pollLast());
        }
        return res.toString();
    }

    public String decodeString3(String s) {
        // 递归 内层递归遇到第一个']'即返回 时间复杂度O(N) 空间复杂度O(N)
        return recursion(s, 0).str;
    }

    private Aux recursion(String s, int start) {
        int num = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                Aux aux = recursion(s, i + 1);
                for (int j = 0; j < num; j++) {
                    sb.append(aux.str);
                }
                num = 0;
                i = aux.index;
            } else if (c == ']') {
                return new Aux(i, sb.toString());
            } else {
                sb.append(c);
            }
        }
        return new Aux(0, sb.toString());
    }

    private class Aux {
        int index;
        String str;

        private Aux(int index, String str) {
            this.index = index;
            this.str = str;
        }
    }

    public String decodeString2(String s) {
        // 递归优化 使用map记录[对应]的下标 减少遍历 时间复杂度O(N) 空间复杂度O(N)
        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                deque.push(i);
            } else if (c == ']') {
                indexMap.put(deque.poll(), i);
            }
        }
        return recursion(s, 0, s.length(), indexMap).toString();
    }

    private StringBuilder recursion(String s, int start, int end, Map<Integer, Integer> indexMap) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else if ('[' == c) {
                Integer rightIndex = indexMap.get(i);
                StringBuilder recursion = recursion(s, i + 1, rightIndex, indexMap);
                for (int j = 0; j < num; j++) {
                    sb.append(recursion);
                }
                i = rightIndex;
                num = 0;
            } else {
                sb.append(c);
            }
        }
        return sb;
    }

    public String decodeString1(String s) {
        // 递归
        return recursion(s).toString();
    }

    private StringBuilder recursion(String s) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else if ('[' == c) {
                int valid = 1;
                for (int j = i + 1; j < s.length(); j++) {
                    char c1 = s.charAt(j);
                    if (c1 == ']') {
                        valid--;
                    } else if (c1 == '[') {
                        valid++;
                    }
                    if (valid == 0) {
                        StringBuilder recursion = recursion(s.substring(i + 1, j));
                        for (int k = 0; k < num; k++) {
                            sb.append(recursion);
                        }
                        num = 0;
                        i = j;
                        break;
                    }
                }
            } else {
                sb.append(c);
            }
        }
        return sb;
    }

    public static void main(String[] args) {
//        System.out.println(new ThreeNineFour().decodeString("3[a]2[bc]"));
//        System.out.println(new ThreeNineFour().decodeString("3[a2[c]]"));
//        System.out.println(new ThreeNineFour().decodeString("2[abc]3[cd]ef"));
        System.out.println(new ThreeNineFour().decodeString3("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
    }
}
