package leetcode.backtracking;

import com.google.common.collect.Lists;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 单词拆分Ⅱ
 *
 * @author chenmo
 * @date 2025/3/26
 */
public class OneFourZero {

    public List<String> wordBreak(String s, List<String> wordDict) {
        // 试一试官方题解的解法 回溯+记忆化 竟然比直接回溯慢 我要找下那个被去掉的测试用例了
        List<String> res = new ArrayList<>();
        Map<Integer, List<List<String>>> memo = new HashMap<>();
        List<List<String>> last = new ArrayList<>();
        last.add(new ArrayList<>());
        memo.put(s.length(), last);
        backTracking(res, new ArrayDeque<>(), s, 0, new HashSet<>(wordDict), memo);
        return res;
    }

    private List<List<String>> backTracking(List<String> res, Deque<String> subList, String s, int index, Set<String> wordDict, Map<Integer, List<List<String>>> memo) {
        if (memo.containsKey(index)) {
            List<List<String>> lists = memo.get(index);
            for (List<String> sub : lists) {
                List<String> newSub = new ArrayList<>(subList);
                newSub.addAll(sub);
                res.add(String.join(" ", newSub));
            }
            return lists;
        }
        // 这里其实走不到了 因为预添加了 map中一定存在
        if (index == s.length()) {
            res.add(String.join(" ", subList));
        }
        List<List<String>> curLists = new ArrayList<>();
        for (int i = index + 1; i <= s.length(); i++) {
            String str = s.substring(index, i);
            if (wordDict.contains(str)) {
                subList.add(str);
                List<List<String>> subLists = backTracking(res, subList, s, i, wordDict, memo);
                subList.removeLast();
                for (List<String> sub : subLists) {
                    List<String> newSub = new ArrayList<>(sub);
                    newSub.add(0, str);
                    curLists.add(newSub);
                }
            }
        }
        memo.put(index, curLists);
        return curLists;
    }

    public List<String> wordBreak1(String s, List<String> wordDict) {
        // 纯回溯 居然不超时 好像是说改测试用例了 leetCode 1ms
        List<String> res = new ArrayList<>();
        backTracking(res, new ArrayDeque<>(), s, 0, new HashSet<>(wordDict));
        return res;
    }

    private void backTracking(List<String> res, Deque<String> subList, String s, int index, Set<String> wordDict) {
        if (index == s.length()) {
            res.add(String.join(" ", subList));
            return;
        }
        for (int i = index + 1; i <= s.length(); i++) {
            String str = s.substring(index, i);
            if (wordDict.contains(str)) {
                subList.add(str);
                backTracking(res, subList, s, i, wordDict);
                subList.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        new OneFourZero().wordBreak("catsanddog",
                Lists.newArrayList("cat", "cats", "and", "sand", "dog"));
    }
}
