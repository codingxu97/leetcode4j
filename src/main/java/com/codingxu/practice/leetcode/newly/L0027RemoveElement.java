package com.codingxu.practice.leetcode.newly;

import java.util.*;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。
 * 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
 * 更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。
 * 返回 k。
 * <p>
 * 示例 1：
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2,_,_]
 * 解释：你的函数函数应该返回 k = 2, 并且 nums 中的前两个元素均为 2。
 * 你在返回的 k 个元素之外留下了什么并不重要（因此它们并不计入评测）。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3,_,_,_]
 * 解释：你的函数应该返回 k = 5，并且 nums 中的前五个元素为 0,0,1,3,4。
 * 注意这五个元素可以任意顺序返回。
 * 你在返回的 k 个元素之外留下了什么并不重要（因此它们并不计入评测）。
 * <p>
 * 提示：
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 */
public class L0027RemoveElement {
    public static void main(String[] args) {
        // 测试用例 1
        int[] nums = new int[]{3,2,2,3};
        int val = 3;
        int actualLength = removeElement(nums, val);
        int[] expectedNums = {2,2}; // 长度正确的预期答案。
        // 它以不等于 val 的值排序。
        assert actualLength == expectedNums.length;
        Arrays.sort(nums, 0, actualLength); // 排序 nums 的前 k 个元素
        for (int i = 0; i < actualLength; i++) {
            assert nums[i] == expectedNums[i];
        }
        // 测试用例 2
        int[] nums2 = new int[]{0,1,2,2,3,0,4,2};
        int val2 = 2;
        int actualLength2 = removeElement(nums2, val2);
        int[] expectedNums2 = {0,1,4,0,3}; // 长度正确的预期答案。
        // 它以不等于 val 的值排序。
        assert actualLength2 == expectedNums2.length;
        Arrays.sort(nums2, 0, actualLength2); // 排序 nums 的前 k 个元素
        for (int i = 0; i < actualLength2; i++) {
            assert nums2[i] == expectedNums2[i];
        }
    }

    /**
     * 自己的方法。
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1)。
     *
     * @param nums 目标数组
     * @param val 待移除元素
     * @return 数组移除后长度
     */
    public static int removeElement(int[] nums, int val) {
        int tailIndex = nums.length - 1;
        int count = 0;

        while (tailIndex >= 0 && nums[tailIndex] == val) {
            count++;
            tailIndex--;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tailIndex < 0) {
                break;
            }
            int num = nums[i];
            if (num == val) {
                nums[i] = nums[tailIndex];
                count++;
                tailIndex--;
                while (tailIndex >= 0 && nums[tailIndex] == val) {
                    count++;
                    tailIndex--;
                }
            }
            if (i >= tailIndex) {
                break;
            }
        }
        return nums.length - count;
    }

    /**
     * 方法一：双指针。
     * 时间复杂度：O(n)，其中 n 为序列的长度。我们只需要遍历该序列至多两次。
     * 空间复杂度：O(1)。我们只需要常数的空间保存若干变量。
     *
     * @param nums 目标数组
     * @param val 待移除元素
     * @return 数组移除后长度
     */
    public static int removeElementOfficial1(int[] nums, int val) {
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
     * 方法二：双指针优化。
     * 时间复杂度：O(n)，其中 n 为序列的长度。我们只需要遍历该序列至多一次。
     * 空间复杂度：O(1)。我们只需要常数的空间保存若干变量。
     *
     * @param nums 目标数组
     * @param val 待移除元素
     * @return 数组移除后长度
     */
    public static int removeElementOfficial2(int[] nums, int val) {
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
