package com.codingxu.practice.leetcode;

import java.util.Arrays;

public class CoinChange332 {
    public static void main(String[] args) {
        int i = coinChange1(new int[]{1, 2, 5}, 30);
        int i2 = coinChange2(new int[]{1, 2, 5}, 30);
        System.out.println(i);
        System.out.println(i2);
    }

    public static int coinChange1(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange1(coins, amount, new int[amount]);
    }

    private static int coinChange1(int[] coins, int rem, int[] count) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange1(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    private static int coinChange2(int[] coins, int amount) {
        int[] temp = new int[amount + 1];
        Arrays.fill(temp, amount);
        temp[0] = 0;
        for (int i = 1; i < amount + 1; i ++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                temp[i] = Math.min(temp[i], temp[i - coin] + 1);
            }
        }
        return temp[amount] > 0 ? temp[amount] : -1;
    }
}
