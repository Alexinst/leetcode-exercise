/*
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 *
 * Reference: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-han-shou-xu-fei-/

给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。

你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。

返回获得利润的最大值。

示例 1:
	输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
	输出: 8
	解释: 能够达到的最大利润:  
	在此处买入 prices[0] = 1
	在此处卖出 prices[3] = 8
	在此处买入 prices[4] = 4
	在此处卖出 prices[5] = 9
	
	总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

注意:
	1. 0 < prices.length <= 50000.
	2. 0 < prices[i] < 50000.
	3. 0 <= fee < 50000.

----------------------------------------------------------------------------------------------------

Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.

Example 1:
	Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
	Output: 8
	Explanation: The maximum profit can be achieved by:
	Buying at prices[0] = 1
	Selling at prices[3] = 8
	Buying at prices[4] = 4
	Selling at prices[5] = 9
	The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
	
Note:
	1. 0 < prices.length <= 50000.
	2. 0 < prices[i] < 50000.
	3. 0 <= fee < 50000.
*/

class Solution1 {
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int unhold = 0, hold = - prices[0];

        for (int i = 1; i < len; i++) {
            int tmp = unhold;
            unhold = Math.max(unhold, hold + prices[i] - fee);
            hold = Math.max(hold, tmp - prices[i]);
        }

        return unhold;
    }
}
