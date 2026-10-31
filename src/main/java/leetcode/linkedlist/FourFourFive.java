package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 两数相加Ⅱ
 *
 * @author chenmo
 * @date 2025/4/29
 */
public class FourFourFive {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 还有一种方式 多次reverse亦可 时间复杂度O(N) 空间复杂度O(1)
        l1 = reverse(l1);
        l2 = reverse(l2);
        // 下面这些代码和使用栈的思路一致
        ListNode pre = new ListNode();
        ListNode dummy = pre;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            dummy.next = new ListNode(sum % 10);

            carry = sum / 10;
            dummy = dummy.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        appendTail(l1 == null ? l2 : l1, dummy, carry);
        return reverse(pre.next);
    }

    private void appendTail(ListNode l, ListNode dummy, int carry) {
        while (l != null) {
            int sum = l.val + carry;
            dummy.next = new ListNode(sum % 10);

            carry = sum / 10;
            dummy = dummy.next;
            l = l.next;
        }
        if (carry != 0) {
            dummy.next = new ListNode(carry);
        }
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        // 使用栈 时间复杂度O(N) 空间复杂度O(N)
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        fillStack(l1, stack1);
        fillStack(l2, stack2);
        int carry = 0;
        ListNode pre = new ListNode();
        ListNode dummy = pre;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            Integer pop1 = stack1.pop();
            Integer pop2 = stack2.pop();
            int sum = pop1 + pop2 + carry;
            dummy.next = new ListNode(sum % 10);

            carry = sum / 10;
            dummy = dummy.next;
        }
        appendTail(dummy, stack1.isEmpty() ? stack2 : stack1, carry);
        return reverse(pre.next);
    }

    private void fillStack(ListNode l, Deque<Integer> stack) {
        while (l != null) {
            stack.push(l.val);
            l = l.next;
        }
    }

    private void appendTail(ListNode dummy, Deque<Integer> stack, int carry) {
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int sum = pop + carry;
            dummy.next = new ListNode(sum % 10);

            carry = sum / 10;
            dummy = dummy.next;
        }
        if (carry != 0) {
            dummy.next = new ListNode(carry);
        }
    }

    private ListNode reverse(ListNode l) {
        ListNode pre = null;
        while (l != null) {
            ListNode next = l.next;
            l.next = pre;
            pre = l;
            l = next;
        }
        return pre;
    }
}
