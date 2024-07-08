package com.codingxu.practice.leetcode;

/**
 * 27. 移除元素。
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveElement27 {
    /**
     * 双指针：
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n)，其中 n 为序列的长度。我们只需要遍历该序列至多两次。
     * <p>
     * 空间复杂度：O(1)。我们只需要常数的空间保存若干变量。
     */
    public int removeElement1(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    /**
     * 双指针优化：
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n)O(n)O(n)，其中 nnn 为序列的长度。我们只需要遍历该序列至多一次。
     * <p>
     * 空间复杂度：O(1)O(1)O(1)。我们只需要常数的空间保存若干变量。
     */
    public int removeElement2(int[] nums, int val) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}
