package com.codingxu.practice.leetcode.newly;

import com.codingxu.practice.leetcode.newly.model.TreeNode;

import java.util.*;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：root = [0]
 * 输出：[0]
 */
public class L0114Flatten {
    public static void main(String[] args) {
        Integer[] arr1 = new Integer[] {1, 2, 5, 3, 4, 6, 7};
        flatten(buildTreeNode(arr1));
        flattenOfficial1(buildTreeNode(arr1));
        flattenOfficial2(buildTreeNode(arr1));
        flattenOfficial3(buildTreeNode(arr1));

        Integer[] arr2 = new Integer[] {};
        flatten(buildTreeNode(arr2));
        flattenOfficial1(buildTreeNode(arr2));
        flattenOfficial2(buildTreeNode(arr2));
        flattenOfficial3(buildTreeNode(arr2));

        Integer[] arr3 = new Integer[] {0};
        flatten(buildTreeNode(arr3));
        flattenOfficial1(buildTreeNode(arr3));
        flattenOfficial2(buildTreeNode(arr3));
        flattenOfficial3(buildTreeNode(arr3));
    }

    private static TreeNode buildTreeNode(Integer[] arr) {
        return buildTreeNode(arr, 0);
    }

    private static TreeNode buildTreeNode(Integer[] arr, int index) {
        if (index > arr.length - 1) {
            return null;
        }
        if (arr[index] == null) {
            return null;
        }
        TreeNode treeNode = new TreeNode();
        treeNode.val = arr[index];
        treeNode.left = buildTreeNode(arr, index * 2 + 1);
        treeNode.right = buildTreeNode(arr, index * 2 + 2);
        return treeNode;
    }

    /**
     * 自己的方法。
     * 时间复杂度：O(n)，其中 n 是二叉树的节点数。前序遍历的时间复杂度是 O(n)，前序遍历的同时对每个节点更新左右子节点的信息，更新子节点信息的时间复杂度是 O(1)，因此总时间复杂度是 O(n)。
     * 空间复杂度：O(n)，其中 n 是二叉树的节点数。空间复杂度取决于栈的大小，栈内的元素个数不会超过 n。
     *
     * @param root 根节点
     */
    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> treeNodeStack = new Stack<>();
        treeNodeStack.push(root);
        TreeNode pre = null;
        while (!treeNodeStack.empty()) {
            TreeNode curNode = treeNodeStack.pop();
            if (curNode == null) {
                continue;
            }
            if (pre == null) {
                pre = curNode;
            } else {
                pre.right = curNode;
                pre.left = null;
                pre = pre.right;
            }
            treeNodeStack.push(curNode.right);
            treeNodeStack.push(curNode.left);
        }
    }

    /**
     * 方法一：前序遍历。
     * 时间复杂度：O(n)，其中 n 是二叉树的节点数。前序遍历的时间复杂度是 O(n)，前序遍历之后，需要对每个节点更新左右子节点的信息，时间复杂度也是 O(n)。
     * 空间复杂度：O(n)，其中 n 是二叉树的节点数。空间复杂度取决于栈（递归调用栈或者迭代中显性使用的栈）和存储前序遍历结果的列表的大小，栈内的元素个数不会超过 n，前序遍历列表中的元素个数是 n。
     *
     * @param root 根节点
     */
    public static void flattenOfficial1(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorderTraversal(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    public static void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }

    /**
     * 方法二：前序遍历和展开同步进行。
     * 时间复杂度：O(n)，其中 n 是二叉树的节点数。前序遍历的时间复杂度是 O(n)，前序遍历的同时对每个节点更新左右子节点的信息，更新子节点信息的时间复杂度是 O(1)，因此总时间复杂度是 O(n)。
     * 空间复杂度：O(n)，其中 n 是二叉树的节点数。空间复杂度取决于栈的大小，栈内的元素个数不会超过 n。
     *
     * @param root 根节点
     */
    public static void flattenOfficial2(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (prev != null) {
                prev.left = null;
                prev.right = curr;
            }
            TreeNode left = curr.left, right = curr.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
            prev = curr;
        }
    }

    /**
     * 方法三：寻找前驱节点。
     * 时间复杂度：O(n)，其中 n 是二叉树的节点数。展开为单链表的过程中，需要对每个节点访问一次，在寻找前驱节点的过程中，每个节点最多被额外访问一次。
     * 空间复杂度：O(1)。
     *
     * @param root 根节点
     */
    public static void flattenOfficial3(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }
}
