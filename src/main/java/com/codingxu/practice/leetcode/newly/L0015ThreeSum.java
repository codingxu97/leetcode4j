package com.codingxu.practice.leetcode.newly;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * 你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * <p>
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 * <p>
 * 提示：
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class L0015ThreeSum {
    public static void main(String[] args) {
        int[] nums1 = new int[] {-1,0,1,2,-1,-4};
        int[] nums2 = new int[] {0,1,1};
        int[] nums3 = new int[] {0,0,0};
        List<List<Integer>> lists1 = threeSum(nums1);
        List<List<Integer>> lists2 = threeSum(nums2);
        List<List<Integer>> lists3 = threeSum(nums3);
    }

    /**
     * 时间复杂度：
     *  O(N2)，其中 N 是数组 nums 的长度。
     * 空间复杂度：
     *  O(logN)。忽略存储答案的空间，额外的排序的空间复杂度为 O(logN)。
     *  然而修改了输入的数组 nums，在实际情况下不一定允许，因此也可以看成使用了一个额外的数组存储了 nums 的副本并进行排序，空间复杂度为 O(N)。
     *
     * @param nums 候选数字数组
     * @return 结果集合
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        // 先分组，以便更好使用双指针法。
        quickSort(nums, 0, nums.length - 1);
        List<List<Integer>> resultList = new ArrayList<>();
        for (int first = 0; first < nums.length - 2; first++) {
            // 由于已经排序，故可和上一个元素进行比对，若相同则直接跳过，避免重复
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int target = -nums[first];
            int third = nums.length - 1;
            for (int second = first + 1; second < nums.length - 1; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                List<Integer> trippleList = new ArrayList<>();
                while (third > second && nums[second] + nums[third] > target) {
                    third--;
                }

                if (second == third) {
                    break;
                }

                if (nums[second] + nums[third] == target) {
                    trippleList.add(nums[first]);
                    trippleList.add(nums[second]);
                    trippleList.add(nums[third]);
                    resultList.add(trippleList);
                }
            }
        }
        return resultList;
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int base = nums[start];
        int left = start;
        int right = end;

        while (left != right) {
            while (left < right && nums[right] >= base) {
                right--;
            }

            while (left < right && nums[left] < base) {
                left++;
            }

            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }

        if (start != left) {
            int temp = nums[start];
            nums[start] = nums[left];
            nums[left] = temp;
        }

        quickSort(nums, start, left);
        quickSort(nums, left + 1, end);
    }
}
