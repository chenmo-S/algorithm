package leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * O(1)时间插入、删除和获取随机元素
 *
 * @author chenmo
 * @date 2025/4/29
 */
public class ThreeEightZero {

    class RandomizedSet {
        /**
         * hash表可以实现近乎O(1)的插入和删除 <br>
         * 如果想要O(1)的获取随机元素 需要使用数组所以我们还需要一个list <br>
         * 但是为了使list的删除可以达到O(1) 需要将hash的value设置为index 当删除时我们只需要set(index,last)这样即可近乎O(1)
         */
        private final Map<Integer, Integer> map;
        private final List<Integer> list;
        private final Random random;

        public RandomizedSet() {
            this.map = new HashMap<>();
            this.list = new ArrayList<>();
            this.random = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, list.size());
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            Integer index = map.get(val);
            Integer last = list.get(list.size() - 1);
            list.set(index, last);
            list.remove(list.size() - 1);
            map.put(last, index);
            map.remove(val);
            return true;
        }

        public int getRandom() {
            int index = random.nextInt(list.size());
            return list.get(index);
        }
    }
}
