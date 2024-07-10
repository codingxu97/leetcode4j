package com.codingxu.practice.leetcode.newly;

/**
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
 * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
 * 返回 k 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2,_]
 * 解释：函数应该返回新的长度 2，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 2：
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5，并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按 非严格递增 排列
 */
public class L0026RemoveDuplicates {
    public static void main(String[] args) {
        int[] nums1 = {1,1,2};
        int count1 = removeDuplicates(nums1);
        int[] expectedNums1 = {1,2}; // 长度正确的期望答案
        assert count1 == expectedNums1.length;
        for (int i = 0; i < expectedNums1.length; i++) {
            assert nums1[i] == expectedNums1[i];
        }

        int[] nums2 = {0,1,1,1,2,2,3,3,4};
        int count2 = removeDuplicates(nums2);
        int[] expectedNums2 = {0,1,2,3,4}; // 长度正确的期望答案
        assert count2 == expectedNums2.length;
        for (int i = 0; i < expectedNums2.length; i++) {
            assert nums2[i] == expectedNums2[i];
        }
    }

    /**
     * 自己的方法。
     * 时间复杂度：O(n)，其中 n 是数组的长度。快指针和慢指针最多各移动 n 次。
     * 空间复杂度：O(1)。只需要使用常数的额外空间。
     *
     * @param nums 目标数组
     * @return 不重复元素个数
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int fast = 0;
        int slow = -1;
        int last = Integer.MIN_VALUE;
        while (fast < nums.length) {
            if (nums[fast] != last) {
                slow++;
                if (slow != fast) {
                    nums[slow] = nums[fast];
                }
            }
            last = nums[fast++];
        }
        return slow + 1;
    }

    /**
     * 方法一：双指针。
     * 时间复杂度：O(n)，其中 n 是数组的长度。快指针和慢指针最多各移动 n 次。
     * 空间复杂度：O(1)。只需要使用常数的额外空间。
     *
     * @param nums 目标数组
     * @return 不重复元素个数
     */
    public int removeDuplicatesOfficial(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
