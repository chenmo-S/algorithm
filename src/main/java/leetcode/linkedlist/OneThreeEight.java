package leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 随机链表的复制
 *
 * @author chenmo
 * @date 2025/4/29
 */
public class OneThreeEight {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        // 我们需要一个hash表记录oldNode->newNode的映射
        // 第一遍遍历只copy外层节点
        Node pre = new Node(0);
        Node dummy = pre;
        Map<Node, Node> old2NewMap = new HashMap<>();
        while (head != null) {
            dummy.next = new Node(head.val);
            dummy.next.random = head.random;

            old2NewMap.put(head, dummy.next);
            dummy = dummy.next;
            head = head.next;
        }
        // 第二遍遍历更新random属性
        head = pre.next;
        while (head != null) {
            head.random = old2NewMap.get(head.random);
            head = head.next;
        }
        return pre.next;
    }
}
