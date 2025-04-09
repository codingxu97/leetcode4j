package com.codingxu.practice.leetcode.newly;

import com.codingxu.practice.leetcode.newly.model.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * <p>
 * 示例 1:
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * <p>
 * 示例 2:
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 */
public class L0105BuildTree {
    private static final Map<Integer, Integer> eleIdxMap = new HashMap<>();

    public static void main(String[] args) {
        int[] preorder1 = new int[]{3, 9, 20, 15, 7};
        int[] inorder1 = new int[]{9, 3, 15, 20, 7};
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
     * 空间复杂度：O(n)，除去返回的答案需要的 O(n) 空间之外，我们还需要使用 O(n) 的空间存储哈希映射，以及 O(h)（其中 h 是树的高度）的空间表示递归时栈空间。这里 h<n，所以总空间复杂度为 O(n)。
     *
     * @param preorder 前序遍历二叉树数组
     * @param inorder 中序遍历二叉树数组
     * @return 构造完成的树
     */
    public static TreeNode buildTreeOfficial1(int[] preorder, int[] inorder) {
        int idx = 0;
        for (int e : inorder) {
            eleIdxMap.put(e, idx++);
        }
        return doBuildTree(preorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }


    public static TreeNode doBuildTree(int[] preorder, int preOrderLeft, int preOrderRight, int inOrderLeft, int inOrderRight) {
        if (preOrderLeft > preOrderRight) {
            return null;
        }
        int rootVal = preorder[preOrderLeft];
        int inOrderRootIdx = eleIdxMap.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        int leftSubTreeNum = inOrderRootIdx - inOrderLeft;
        root.left = doBuildTree(preorder, preOrderLeft + 1, preOrderLeft + leftSubTreeNum, inOrderLeft, inOrderRootIdx - 1);
        root.right = doBuildTree(preorder, preOrderLeft + leftSubTreeNum + 1, preOrderRight, inOrderRootIdx + 1, inOrderRight);
        return root;
    }

    /**
     * 方法二：迭代。
     * 时间复杂度：O(n)，其中 n 是树中的节点个数。
     * 空间复杂度：O(n)，除去返回的答案需要的 O(n) 空间之外，我们还需要使用 O(h)（其中 h 是树的高度）的空间存储栈。这里 h<n，所以（在最坏情况下）总空间复杂度为 O(n)。
     *
     * @param preorder 前序遍历二叉树数组
     * @param inorder 中序遍历二叉树数组
     * @return 构造完成的树
     */
    public static TreeNode buildTreeOfficial2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }
}
