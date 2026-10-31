package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 盛最多水的容器
 *
 * @author chenmo
 * @date 2024/4/25
 */
public class OneOne {

    public int maxArea(int[] height) {
        // 时间复杂度 O(n)
        // 每次移动x坐标必减少，所以只有移动较短的y轴才不会错过最大值
        int res = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            res = Math.max(res, Math.min(height[left], height[right]) * (right - left));
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    public int maxArea1(int[] height) {
        // 时间复杂度O(n2) 超时
        int res = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return res;
    }

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits==null||digits.length()==0){
            return res;
        }
        backTracking(res, new StringBuilder(), digits, 0, buildMap());
        return res;
    }

    private void backTracking(List<String> res, StringBuilder sb, String digits, int index, Map<Character, List<Character>> dictMap) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        for (Character c : dictMap.getOrDefault(sb.charAt(index), new ArrayList<>())) {
            sb.append(c);
            backTracking(res, sb, digits, index + 1, dictMap);
            sb.setLength(sb.length() - 1);
        }
    }

    private Map<Character, List<Character>> buildMap() {
        Map<Character, List<Character>> map = new HashMap<>(16);
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        return map;
    }

    public static void main(String[] args) {
        new OneOne().letterCombinations("23");
    }
}
