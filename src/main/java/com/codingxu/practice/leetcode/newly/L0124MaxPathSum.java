package com.codingxu.practice.leetcode.newly;

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

    public static int maxPathSum(TreeNode root) {
        maxSum(root);
        return max;
    }

    public static Integer maxSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftMax = maxSum(node.left);
        int rightMax = maxSum(node.right);

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
