package leetcode.design;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * 扁平化嵌套列表迭代器
 *
 * @author chenmo
 * @date 2025/5/15
 */
public class ThreeFourOne {

    /**
     * 使用stack记录当前迭代器指针 先入后出 空间复杂度O(N) 初始化方法时间复杂度O(1) next和hasNext时间复杂度O(1)  <br>
     * 在ide中写不出来 因为题意NestedInteger类似于继承Integer和List
     */
    public class NestedIterator implements Iterator<Integer> {

        Deque<Iterator<Integer>> stack;

        public NestedIterator(List<NestedInteger> nestedList) {

        }

        @Override
        public Integer next() {
            return 1;
        }

        @Override
        public boolean hasNext() {
            return false;
        }
    }

    /**
     * 将元素展平后存储 空间复杂度O(N) 初始化方法时间复杂度O(N) next和hasNext时间复杂度O(1)
     */
    public class NestedIterator1 implements Iterator<Integer> {

        /**
         * 存储展平后的元素
         */
        List<Integer> list;
        int cursor;

        public NestedIterator1(List<NestedInteger> nestedList) {
            // 在初始化中直接将NestedInteger展平
            this.list = new ArrayList<>();
            flatten(nestedList, list);
        }

        private void flatten(List<NestedInteger> nestedList, List<Integer> list) {
            if (nestedList == null || nestedList.size() == 0) {
                return;
            }
            for (NestedInteger nestedInteger : nestedList) {
                if (nestedInteger.isInteger()) {
                    list.add(nestedInteger.getInteger());
                } else {
                    flatten(nestedInteger.getList(), list);
                }
            }
        }

        @Override
        public Integer next() {
            return list.get(cursor++);
        }

        @Override
        public boolean hasNext() {
            return cursor < list.size();
        }
    }

    public interface NestedInteger {

        boolean isInteger();

        Integer getInteger();

        List<NestedInteger> getList();
    }
}
