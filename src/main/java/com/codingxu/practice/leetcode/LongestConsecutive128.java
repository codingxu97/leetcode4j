package com.codingxu.practice.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive128 {
    /**
     * 复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中 n 为数组的长度。
     * <p>
     * 空间复杂度：O(n)。哈希表存储数组中所有的数需要 O(n) 的空间。
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
