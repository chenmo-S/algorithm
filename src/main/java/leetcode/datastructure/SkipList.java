package leetcode.datastructure;

import java.util.Random;

/**
 * 1206 设计跳表
 *
 * @author chenmo
 * @date 2025/3/17
 */
public class SkipList {

    class Skiplist {
        // 设置最大层级
        static final int MAX_LEVEL = 32;
        // 生成下一层的因子 1/4
        static final double P_FACTOR = 0.25;
        // 头结点(哑结点)
        private SkiplistNode head;
        // 当前层级
        private int curLevel;
        private Random random;

        public Skiplist() {
            this.head = new SkiplistNode(-1, MAX_LEVEL);
            this.curLevel = 0;
            this.random = new Random();
        }

        public boolean search(int target) {
            SkiplistNode curr = this.head;
            // 从最高层开始查找
            for (int i = curLevel - 1; i >= 0; i--) {
                while (curr.forward[i] != null && curr.forward[i].val < target) {
                    curr = curr.forward[i];
                }
                if (curr.forward[i] != null && curr.forward[i].val == target) {
                    return true;
                }
            }
            return false;
        }

        public void add(int num) {
            SkiplistNode[] pre = new SkiplistNode[MAX_LEVEL];
            SkiplistNode curr = this.head;
            // 从最高层开始 之所以从最高层而不是具体的lv层 是因为要利用高层的索引加速 否则如果lv较小 找node位置的过程会退化为O(N)
            for (int i = curLevel - 1; i >= 0; i--) {
                // 找到第 i 层小于且最接近 num 的元素
                while (curr.forward[i] != null && curr.forward[i].val < num) {
                    curr = curr.forward[i];
                }
                pre[i] = curr;
            }
            int lv = randomLevel();
            // 升级 则该节点的pre为head
            for (int i = curLevel; i < lv; i++) {
                pre[i] = head;
            }
            curLevel = Math.max(curLevel, lv);
            SkiplistNode newNode = new SkiplistNode(num, lv);
            for (int i = 0; i < lv; i++) {
                // 对第 i 层的状态进行更新，将当前元素的 forward 指向新的节点
                newNode.forward[i] = pre[i].forward[i];
                pre[i].forward[i] = newNode;
            }
        }

        public boolean erase(int num) {
            // 依然是从最高层开始
            SkiplistNode[] pre = new SkiplistNode[MAX_LEVEL];
            SkiplistNode curr = head;
            // 从最高层找到最底层 不能提前终止
            for (int i = curLevel - 1; i >= 0; i--) {
                while (curr.forward[i] != null && curr.forward[i].val < num) {
                    curr = curr.forward[i];
                }
                pre[i] = curr;
            }
            // 最底层判断 不存在
            SkiplistNode deleteNode = pre[0].forward[0];
            if (deleteNode == null || deleteNode.val != num) {
                return false;
            }
            // 逐层删除
            for (int i = 0; i < curLevel; i++) {
                // 该层不包含 则可以终止了
                if (pre[i].forward[i] == null || pre[i].forward[i] != deleteNode) {
                    break;
                }
                pre[i].forward[i] = pre[i].forward[i].forward[i];
            }
            // 如果需要则降低层级
            while (curLevel > 1 && head.forward[curLevel - 1] == null) {
                curLevel--;
            }
            return true;
        }

        private int randomLevel() {
            int lv = 1;
            // 随机生成 lv
            while (random.nextDouble() < P_FACTOR && lv < MAX_LEVEL) {
                lv++;
            }
            return lv;
        }
    }

    class SkiplistNode {
        int val;
        SkiplistNode[] forward;

        public SkiplistNode(int val, int level) {
            this.val = val;
            this.forward = new SkiplistNode[level];
        }
    }

}
