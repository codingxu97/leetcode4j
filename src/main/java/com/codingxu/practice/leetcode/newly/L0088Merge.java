package com.codingxu.practice.leetcode.newly;

import java.util.Arrays;

/**
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 * <p>
 * 示例 2：
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 解释：需要合并 [1] 和 [] 。
 * 合并结果是 [1] 。
 * <p>
 * 示例 3：
 * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 * 输出：[1]
 * 解释：需要合并的数组是 [] 和 [1] 。
 * 合并结果是 [1] 。
 * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 * <p>
 * 提示：
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[j] <= 109
 */
public class L0088Merge {
    public static void main(String[] args) {
        int[] nums1 = new int[]{21,22,23,29,0,0,0};
        int m = 4;
        int[] nums2 = new int[]{12,15,16};
        int n = 3;
        merge3(nums1, m, nums2, n);
    }

    /**
     * 方法一：直接合并后排序。
     * 时间复杂度：O((m+n)log(m+n))。排序序列长度为 m+n，套用快速排序的时间复杂度即可，平均情况为 O((m+n)log(m+n))。
     * 空间复杂度：O(log(m+n))。排序序列长度为 m+n，套用快速排序的空间复杂度即可，平均情况为 O(log(m+n))。
     *
     * @param nums1 数组 1
     * @param m 数组 1 中有效元素个数
     * @param nums2 数组 2
     * @param n 数组 2 中有效元素个数
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n >= 0) {
            System.arraycopy(nums2, 0, nums1, m, n);
        }
        Arrays.sort(nums1);
    }

    /**
     * 方法二：双指针。
     * 时间复杂度：O(m+n)。指针移动单调递增，最多移动 m+n 次，因此时间复杂度为 O(m+n)。
     * 空间复杂度：O(m+n)。需要建立长度为 m+n 的中间数组 sorted。
     *
     * @param nums1 数组 1
     * @param m 数组 1 中有效元素个数
     * @param nums2 数组 2
     * @param n 数组 2 中有效元素个数
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        if (m + n >= 0) {
            System.arraycopy(sorted, 0, nums1, 0, m + n);
        }
    }

    /**
     * 方法三：逆向双指针。
     * 时间复杂度：O(m+n)。指针移动单调递减，最多移动 m+n 次，因此时间复杂度为 O(m+n)。
     * 空间复杂度：O(1)。直接对数组 nums1 原地修改，不需要额外空间。
     *
     * @param nums1 数组 1
     * @param m 数组 1 中有效元素个数
     * @param nums2 数组 2
     * @param n 数组 2 中有效元素个数
     */
    public static void merge3(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, tail = nums1.length - 1;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 < 0) {
                nums1[tail--] = nums2[p2--];
                continue;
            }
            if (p2 < 0) {
                nums1[tail--] = nums1[p1--];
                continue;
            }
            if (nums1[p1] > nums2[p2]) {
                nums1[tail--] = nums1[p1--];
            } else {
                nums1[tail--] = nums2[p2--];
            }
        }
    }
}
