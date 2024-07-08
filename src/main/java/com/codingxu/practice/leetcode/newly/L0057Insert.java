package com.codingxu.practice.leetcode.newly;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表 intervals，其中 intervals[i] = [starti, endi] 表示第 i 个区间的开始和结束，并且 intervals 按照 starti 升序排列。
 * 同样给定一个区间 newInterval = [start, end] 表示另一个区间的开始和结束。
 * 在 intervals 中插入区间 newInterval，使得 intervals 依然按照 starti 升序排列，且区间之间不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 返回插入之后的 intervals。
 * <p>
 * 注意 你不需要原地修改 intervals。你可以创建一个新数组然后返回它。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * <p>
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * <p>
 * 提示：
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 105
 * intervals 根据 starti 按 升序 排列
 * newInterval.length == 2
 * 0 <= start <= end <= 105
 */
public class L0057Insert {
    public static void main(String[] args) {
        int[][] intervals1 = new int[][]{{1,3},{6,9}};
        int[] newInterval1 = {2,5};

        int[][] intervals2 = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval2 = {4,8};

        int[][] insertResult1 = insert(intervals1, newInterval1);
        int[][] insertResult2 = insert(intervals2, newInterval2);
    }

    /**
     * 自己的方法。
     * 时间复杂度：O(n)，其中 n 是数组 intervals 的长度，虽然用了嵌套 while，但总体遍历次数为 n。
     * 空间复杂度：O(1)。除了存储返回答案的空间以外，我们只需要额外的常数空间即可。
     *
     * @param intervals 原区间数组，int[][]
     * @param newInterval 待插入区间，int[]
     * @return 合并后的结果区间数组
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> resultArr = new ArrayList<>();
        int i = 0;
        if (intervals.length == 0) {
            resultArr.add(newInterval);
        }

        while (i < intervals.length) {
            int[] targetInterval = intervals[i];
            // 处理 newInterval 和 遍历区间元素 targetInterval 不重叠的情况
            if (newInterval[0] > targetInterval[1]) {
                resultArr.add(targetInterval);
                if (i == intervals.length - 1 || newInterval[1] < intervals[i + 1][0]) {
                    resultArr.add(newInterval);
                }
                i++;
                continue;
            }

            if (targetInterval[0] > newInterval[1]) {
                if (i == 0) {
                    resultArr.add(newInterval);
                }
                resultArr.add(targetInterval);
                i++;
                continue;
            }
            // 处理 newInterval 和 遍历区间元素 targetInterval 重叠的情况
            // 找到重叠区间的 min 和 max，并加入到
            int min = Math.min(targetInterval[0], newInterval[0]);
            int max = Math.max(targetInterval[1], newInterval[1]);
            while (i <= intervals.length) {
                if (intervals.length == i) {
                    resultArr.add(new int[] {min, max});
                    break;
                }
                int[] nextInterval = intervals[i];
                if (nextInterval[0] > newInterval[1]) {
                    resultArr.add(new int[] {min, max});
                    resultArr.add(nextInterval);
                    i++;
                    break;
                }
                min = Math.min(nextInterval[0], min);
                max = Math.max(nextInterval[1], max);
                i++;
            }
        }
        return resultArr.toArray(value -> new int[0][]);
    }

    /**
     * 官方的方法。
     * 时间复杂度：O(n)，其中 n 是数组 intervals 的长度，即给定的区间个数。
     * 空间复杂度：O(1)。除了存储返回答案的空间以外，我们只需要额外的常数空间即可。
     *
     * @param intervals 原区间数组，int[][]
     * @param newInterval 待插入区间，int[]
     * @return 合并后的结果区间数组
     */
    public int[][] insertOfficial(int[][] intervals, int[] newInterval) {
        int left = newInterval[0], right = newInterval[1];
        boolean placed = false;

        ArrayList<int[]> arrayList = new ArrayList<>();
        for(int[] interval : intervals) {
            if (interval[0] > right) {
                // 插入集合无交集，且比该区间小，将插入区间加入
                if(!placed) {
                    arrayList.add(new int[]{left, right});
                    placed = true;
                }
                arrayList.add(interval);
            } else if (interval[1] < left) {
                // 插入集合无交际，且比该区间大，将该区间加入
                arrayList.add(interval);
            } else {
                // 插入区间和本区间有交集，取并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }

        if (!placed) {
            arrayList.add(new int[]{left, right});
        }

        int[][] ans = new int[arrayList.size()][2];
        for (int i = 0; i < arrayList.size(); ++i) {
            ans[i] = arrayList.get(i);
        }
        return ans;
    }
}
