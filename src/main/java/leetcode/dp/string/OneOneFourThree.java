package leetcode.dp.string;

/**
 * 最长公共子序列
 *
 * @author chenmo
 * @date 2025/3/6
 */
public class OneOneFourThree {

    public int longestCommonSubsequence(String text1, String text2) {
        // 动态规划 因为dp只和左上，左和上，三个方向有关 可以减少空间复杂度
        // 时间复杂度O(MN) 空间复杂度O(min(M,N))
        int m = text1.length(), n = text2.length();
        // 让n尽可能小
        if (m < n) {
            return longestCommonSubsequence(text2, text1);
        }
        int[] dp = new int[n + 1];
        int leftTop = 0;
        for (int i = 1; i <= m; i++) {
            char c = text1.charAt(i - 1);
            // 每一行j每次遍历开始leftTop都等于0
            leftTop = 0;
            for (int j = 1; j <= n; j++) {
                // 临时值记录下当前的dp[i-1][j] 即当前top 也就是下一次j的leftTop
                int top = dp[j];
                if (c == text2.charAt(j - 1)) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(top, dp[j - 1]);
                }
                // 当前top作为下一次j的leftTop
                leftTop = top;
            }
        }
        return dp[n];
    }

    public int longestCommonSubsequence1(String text1, String text2) {
        // 动态规划 时间复杂度O(MN) 空间复杂度O(MN)
        int m = text1.length(), n = text2.length();
        // 设dp[i][j]为text1的前i个字符和text2的前j个字符的最长公共子序列
        int[][] dp = new int[m + 1][n + 1];
        char[] char1 = text1.toCharArray(), char2 = text2.toCharArray();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (char1[i - 1] == char2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 扩展一下 求LCS内容
     */
    public String findLCSContext(String text1, String text2) {
        // 动态规划 时间复杂度O(MN) 空间复杂度O(MN)
        int m = text1.length(), n = text2.length();
        // 设dp[i][j]为text1的前i个字符和text2的前j个字符的最长公共子序列
        int[][] dp = new int[m + 1][n + 1];
        char[] char1 = text1.toCharArray(), char2 = text2.toCharArray();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (char1[i - 1] == char2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 从右下角开始回溯，构造 LCS
        StringBuilder sb = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                // 该字符属于 LCS
                sb.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                // 向上走
                i--;
            } else {
                // 向左走
                j--;
            }
        }
        // 因为是从后往前添加的，需要反转
        return sb.reverse().toString();
    }

    static void main() {
        System.out.println(new OneOneFourThree().longestCommonSubsequence("abcde", "ace"));
        System.out.println(new OneOneFourThree().findLCSContext("abcde", "ace"));
    }
}
