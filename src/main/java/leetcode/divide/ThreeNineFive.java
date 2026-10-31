package leetcode.divide;

/**
 * 至少有K个重复字符的最长子串
 *
 * @author chenmo
 * @date 2025/4/16
 */
public class ThreeNineFive {

    public int longestSubstring(String s, int k) {
        // 分治
        // 如果存在一个字符c 出现次数大于0且小于k 则任何包含c的子串都不能满足要求
        // 所以根据该字符 进行切分 分治求值
        return recursion(s, 0, s.length() - 1, k);
    }

    private int recursion(String s, int left, int right, int k) {
        // 记录所有字符的出现次数
        int[] nums = new int[26];
        for (int i = left; i <= right; i++) {
            nums[s.charAt(i) - 'a']++;
        }
        // 找到目标字符
        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (nums[i] > 0 && nums[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        // 没有该目标字符
        if (split == 0) {
            return right - left + 1;
        }
        int res = 0;
        int i = left;
        while (i <= right) {
            while (i <= right && s.charAt(i) == split) {
                i++;
            }
            if (i > right) {
                break;
            }
            int start = i;
            while (i <= right && s.charAt(i) != split) {
                i++;
            }
            int length = recursion(s, start, i - 1, k);
            res = Math.max(res, length);
        }
        return res;
    }
}
