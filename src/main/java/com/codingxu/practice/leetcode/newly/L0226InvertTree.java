package com.codingxu.practice.leetcode.newly;

import com.codingxu.practice.leetcode.newly.model.TreeNode;

/**
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * <p>
 * 示例 1：
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * <p>
 * 示例 2：
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 * <p>
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 */
public class L0226InvertTree {
    public static void main(String[] args) {
        Integer[] arr1 = new Integer[] {4, 2, 7, 1, 3, 6, 9};
        TreeNode treeNode1 = buildTreeNode(arr1);
        TreeNode invertTreeNode1 = invertTree(treeNode1);

        Integer[] arr2 = new Integer[] {2,1,3};
        TreeNode treeNode2 = buildTreeNode(arr2);
        TreeNode invertTreeNode2 = invertTree(treeNode2);

        Integer[] arr3 = new Integer[] {};
        TreeNode treeNode3 = buildTreeNode(arr3);
        TreeNode invertTreeNode3 = invertTree(treeNode3);
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
     * 时间复杂度：O(N)，其中 N 为二叉树节点的数目。我们会遍历二叉树中的每一个节点，对每个节点而言，我们在常数时间内交换其两棵子树。
     * 空间复杂度：O(N)。使用的空间由递归栈的深度决定，它等于当前节点在二叉树中的高度。在平均情况下，二叉树的高度与节点个数为对数关系，即 O(logN)。而在最坏情况下，树形成链状，空间复杂度为 O(N)。
     *
     * @param root 根节点
     * @return 翻转后的树节点
     */
    private static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            invertTree(root.left);
        }
        if (root.right != null) {
            invertTree(root.right);
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }

    /**
     * 方法一：递归。
     * 时间复杂度：O(N)，其中 N 为二叉树节点的数目。我们会遍历二叉树中的每一个节点，对每个节点而言，我们在常数时间内交换其两棵子树。
     * 空间复杂度：O(N)。使用的空间由递归栈的深度决定，它等于当前节点在二叉树中的高度。在平均情况下，二叉树的高度与节点个数为对数关系，即 O(logN)。而在最坏情况下，树形成链状，空间复杂度为 O(N)。
     *
     * @param root 根节点
     * @return 翻转后的树节点
     */
    private static TreeNode invertTreeOfficial(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            invertTree(root.left);
        }
        if (root.right != null) {
            invertTree(root.right);
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}