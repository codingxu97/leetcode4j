package com.codingxu.practice.leetcode;

/**
 * 11. 盛最多水的容器。
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * <p>
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 返回容器可以储存的最大水量。
 * <p>
 * 说明：你不能倾斜容器。
 */
public class MaxArea11 {
    /**
     * 复杂度分析：
     * <p>
     * 时间复杂度：O(N)，双指针总计最多遍历整个数组一次。
     * <p>
     * 空间复杂度：O(1)，只需要额外的常数级别的空间。
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }
}
