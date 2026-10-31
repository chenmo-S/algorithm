package leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Z字形变换
 *
 * @author chenmo
 * @date 2024/12/25
 */
public class Six {

    public String convert(String s, int numRows) {
        // 找规律 时间复杂度O(N) 空间复杂度可以做到O(1)
        int n = s.length();
        if (numRows == 1 || numRows >= n) {
            return s;
        }
        // 一个周期数量为 cycle=numRows+numRows-2
        // 设row为行号 每一行的字母的下标为 row+k*cycle k从0开始
        // 但是中间的行有两列 第二列为 cycle-row+k*cycle
        int cycle = 2 * numRows - 2;
        // 结果数组不计入空间复杂度 虽然java的String是不可变的(严格来说也是O(N)的空间) -_-
        StringBuilder res = new StringBuilder();
        // 遍历行号 令i从row开始 每次递增cycle
        for (int row = 0; row < numRows; row++) {
            for (int i = row; i < n; i += cycle) {
                // 对于首尾只需要i循环添加即可
                res.append(s.charAt(i));
                if (row != 0 && row != numRows - 1) {
                    // 中间行额外添加第二列
                    // 由i=row+k*cycle 第二列j=cycle-row+k*cycle
                    // 将k*cycle=i-row 带入可得 j=cycle-2*row+i
                    int j = cycle - 2 * row + i;
                    if (j < n) {
                        res.append(s.charAt(j));
                    }
                }
            }
        }
        return res.toString();
    }

    public String convert1(String s, int numRows) {
        // 时间复杂度O(N) 空间复杂度O(N)
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> list = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        int direct = 1, index = 0;
        for (int i = 0; i < s.length(); i++) {
            list.get(index).append(s.charAt(i));
            index += direct;
            if (index == 0 || index == numRows - 1) {
                direct = -direct;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            res.append(list.get(i));
        }
        return res.toString();
    }
}
