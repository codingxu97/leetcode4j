package com.codingxu.practice.leetcode.newly;

/**
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * <p>
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * <p>
 * 提示：
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 */
public class L0124MaxPathSum {
    public static void main(String[] args) {
        Integer[] a = new Integer[]{-10,9,20,null,null,15,7};
        TreeNode treeNode = new TreeNode(a[0]);
        generateTree(a, treeNode, 0);
        int i = maxPathSum(treeNode);
        System.out.println(i);
    }

    public static void generateTree(Integer[] arr, TreeNode node, int index) {
        if (index * 2 + 1 < arr.length && arr[index * 2 + 1] != null) {
            node.left = new TreeNode(arr[index * 2 + 1]);
            generateTree(arr, node.left, index * 2 + 1);
        }

        if (index * 2 + 2 < arr.length && arr[index * 2 + 2] != null) {
            node.right = new TreeNode(arr[index * 2 + 2]);
            generateTree(arr, node.right, index * 2 + 2);
        }
    }

    private static Integer max = Integer.MIN_VALUE;

    /**
     * 时间复杂度：O(N)，其中 N 是二叉树中的节点个数。对每个节点访问不超过 2 次。
     * 空间复杂度：O(N)，其中 N 是二叉树中的节点个数。空间复杂度主要取决于递归调用层数，最大层数等于二叉树的高度，最坏情况下，二叉树的高度等于二叉树中的节点个数。
     *
     * @param root 根节点
     * @return 路径和的最大值
     */
    public static int maxPathSum(TreeNode root) {
        maxSum(root);
        return max;
    }

    public static Integer maxSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftMax = Math.max(0, maxSum(node.left));
        int rightMax = Math.max(0, maxSum(node.right));

        int maxSum = leftMax + rightMax + node.val;
        max = Math.max(max, maxSum);
        return node.val + Math.max(leftMax, rightMax);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
