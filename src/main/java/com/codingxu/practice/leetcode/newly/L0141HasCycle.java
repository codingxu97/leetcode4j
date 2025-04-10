package com.codingxu.practice.leetcode.newly;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

/**
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 * <p>
 *
 * 提示：
 * 链表中节点的数目范围是 [0, 104]
 * -105 <= Node.val <= 105
 * pos 为 -1 或者链表中的一个 有效索引 。
 */
public class L0141HasCycle {
    public static void main(String[] args) {

    }

    /**
     * 方法一：哈希表。
     * 时间复杂度：O(N)，其中 N 是链表中的节点数。最坏情况下我们需要遍历每个节点一次。
     * 空间复杂度：O(N)，其中 N 是链表中的节点数。主要为哈希表的开销，最坏情况下我们需要将每个节点插入到哈希表中一次。
     *
     * @param head 头指针
     * @return 是否有环
     */
    public static boolean hasCycle(ListNode head) {
        ListNode p = head;
        if (p == null) {
            return false;
        }
        HashSet<ListNode> nodeSet = new HashSet<>();
        while (p.next != null) {
            p = p.next;
            if (nodeSet.contains(p)) {
                return true;
            }
            nodeSet.add(p);
        }
        return false;
    }

    /**
     * 方法二：快慢指针。
     * 时间复杂度：O(N)，其中 N 是链表中的节点数。当链表中不存在环时，快指针将先于慢指针到达链表尾部，链表中每个节点至多被访问两次。当链表中存在环时，每一轮移动后，快慢指针的距离将减小一。而初始距离为环的长度，因此至多移动 N 轮。
     * 空间复杂度：O(1)。我们只使用了两个指针的额外空间。
     *
     * @param head 头指针
     * @return 是否有环
     */
    public static boolean hasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode p1 = head, p2 = head;
        while (true) {
            p1 = p1.next;
            p2 = Optional.ofNullable(p2.next).map(p -> p.next).orElse(null);
            if (p1 == null || p2 == null) {
                return false;
            }
            if (p1 == p2) {
                return true;
            }
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
