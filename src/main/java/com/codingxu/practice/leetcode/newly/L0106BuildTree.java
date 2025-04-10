package com.codingxu.practice.leetcode.newly;

import com.codingxu.practice.leetcode.newly.model.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 * <p>
 * 示例 1:
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 * <p>
 * 示例 2:
 * 输入：inorder = [-1], postorder = [-1]
 * 输出：[-1]
 */
public class L0106BuildTree {
    private static final Map<Integer, Integer> eleIdxMap = new HashMap<>();

    public static void main(String[] args) {
        int[] preorder1 = new int[]{9, 3, 15, 20, 7};
        int[] inorder1 = new int[]{9, 15, 7, 20, 3};
        TreeNode treeNode11 = buildTreeOfficial1(preorder1, inorder1);
        TreeNode treeNode12 = buildTreeOfficial2(preorder1, inorder1);

        int[] preorder2 = new int[]{-1};
        int[] inorder2 = new int[]{-1};
        TreeNode treeNode21 = buildTreeOfficial1(preorder2, inorder2);
        TreeNode treeNode22 = buildTreeOfficial2(preorder2, inorder2);
    }

    /**
     * 方法一：递归。
     * 时间复杂度：O(n)，其中 n 是树中的节点个数。
     * 空间复杂度：O(n)。我们需要使用 O(n) 的空间存储哈希表，以及 O(h)（其中 h 是树的高度）的空间表示递归时栈空间。这里 h<n，所以总空间复杂度为 O(n)。
     *
     * @param inorder 中序遍历二叉树数组
     * @param postorder 后序遍历二叉树数组
     * @return 构造完成的树
     */
    public static TreeNode buildTreeOfficial1(int[] inorder, int[] postorder) {
        int idx = 0;
        for (int e : inorder) {
            eleIdxMap.put(e, idx++);
        }
        return doBuildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    public static TreeNode doBuildTree(int[] inorder, int[] postorder, int inorderLeft, int inorderRight, int postorderLeft, int postorderRight) {
        if (postorderLeft > postorderRight) {
            return null;
        }
        int rootVal = postorder[postorderRight];
        int inorderRootIdx = eleIdxMap.get(rootVal);
        int rightSubTreeNum = inorderRight - inorderRootIdx;
        TreeNode root = new TreeNode(rootVal);
        root.right = doBuildTree(inorder, postorder, inorderRootIdx + 1, inorderRight, postorderRight - rightSubTreeNum, postorderRight - 1);
        root.left = doBuildTree(inorder, postorder, inorderLeft, inorderRootIdx - 1, postorderLeft, postorderRight - rightSubTreeNum - 1);
        return root;
    }

    /**
     * 方法二：迭代。
     * 时间复杂度：O(n)，其中 n 是树中的节点个数。
     * 空间复杂度：O(n)，我们需要使用 O(h)（其中 h 是树的高度）的空间存储栈。这里 h<n，所以（在最坏情况下）总空间复杂度为 O(n)。
     *
     * @param inorder 中序遍历二叉树数组
     * @param postorder 后序遍历二叉树数组
     * @return 构造完成的树
     */
    public static TreeNode buildTreeOfficial2(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }
        return root;
    }
}
