package fundamentals.linkedlist;

import sort.heap.Heap;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * <p>
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * <p>
 * return 1->4->3->2->5->NULL.
 * <p>
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 *
 * @author wangxuan
 * @date 2018/1/31 9:27
 */
public class ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int m, int n) {

        if (head == null)
            return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        // dummy -> 1 -> 2 -> 3 -> 4 -> 5

        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }

        ListNode start = pre.next;
        ListNode then = start.next;

        // 1 -> 2 -> 3 -> 4 -> 5 , m=2 n =4 ===> pre = 1, start = 2, then = 3

        // 每次只置换then到pre的后面
        for (int i = 0; i < n - m; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        // 第一次置换: dummy -> 1 -> 3 -> 2 -> 4 -> 5; pre = 1, start = 2, then = 4
        // 第二次置换: dummy -> 1 -> 4 -> 3 -> 2 -> 5; pre = 1, start = 2, then = 5
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ListNode pre = new ListNode().createLinkedList(arr);
        System.out.println(pre);
        ListNode after = new ReverseLinkedListII().reverseBetween(pre, 2, 8);
        System.out.println(after);
    }
}


class ListNode {

    int val;
    ListNode next;

    public ListNode() {
    }

    ListNode(int x) {
        val = x;
    }

    public ListNode createLinkedList(int[] arr) {

        ListNode head = new ListNode(arr[0]);

        ListNode curNode = head;
        for (int i = 1; i < arr.length; i++) {
            curNode.next = new ListNode(arr[i]);
            curNode = curNode.next;
        }

        return head;
    }

    public String toString() {
        return val + " -> " + next;
    }
}