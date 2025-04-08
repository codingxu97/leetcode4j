package com.codingxu.practice.leetcode.newly;

import com.codingxu.practice.leetcode.newly.model.TreeNode;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：root = [1,null,2]
 * 输出：2
 */
public class L0104MaxDepth {
    public static void main(String[] args) {
        Integer[] arr1 = new Integer[] {3, 9, 20, null, null, 15, 7};
        TreeNode treeNode1 = buildTreeNode(arr1);
        int maxDepthMethod11 = maxDepthOfficial1(treeNode1);
        int maxDepthMethod21 = maxDepthOfficial2(treeNode1);

        Integer[] arr2 = new Integer[] {1, null, 2};
        TreeNode treeNode2 = buildTreeNode(arr2);
        int maxDepthMethod12 = maxDepthOfficial1(treeNode2);
        int maxDepthMethod22 = maxDepthOfficial2(treeNode2);
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
     * 方法一：深度优先搜索。
     * 时间复杂度：O(n)，其中 n 为二叉树节点的个数。每个节点在递归中只被遍历一次。
     * 空间复杂度：O(height)，其中 height 表示二叉树的高度。递归函数需要栈空间，而栈空间取决于递归的深度，因此空间复杂度等价于二叉树的高度。
     *
     * @param root 根节点
     * @return 最大深度
     */
    private static int maxDepthOfficial1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepthOfficial1(root.left), maxDepthOfficial1(root.right)) + 1;
    }

    /**
     * 方法二：广度优先搜索。
     * 时间复杂度：O(n)，其中 n 为二叉树的节点个数。与方法一同样的分析，每个节点只会被访问一次。
     * 空间复杂度：此方法空间的消耗取决于队列存储的元素数量，其在最坏情况下会达到 O(n)。
     *
     * @param root 根节点
     * @return 最大深度
     */
    private static int maxDepthOfficial2(TreeNode root) {
       if (root == null) {
           return 0;
       }
       Queue<TreeNode> treeNodeQueue = new LinkedBlockingQueue<>();
       treeNodeQueue.offer(root);
       int ans = 0;
       while (!treeNodeQueue.isEmpty()) {
           int size = treeNodeQueue.size();
           while (size > 0) {
               TreeNode currentNode = treeNodeQueue.poll();
               if (currentNode.left != null) {
                   treeNodeQueue.offer(currentNode.left);
               }
               if (currentNode.right != null) {
                   treeNodeQueue.offer(currentNode.right);
               }
               size--;
           }
           ans++;
       }
       return ans;
    }
}

