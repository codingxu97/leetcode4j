package com.codingxu.practice.leetcode;

/**
 * 283. 移动零。
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */
public class MoveZeroes283 {
    /**
     * 复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中 n 为序列长度。每个位置至多被遍历两次。
     * <p>
     * 空间复杂度：O(1)。只需要常数的空间存放若干变量。
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
