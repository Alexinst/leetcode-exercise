/*
 * https://leetcode-cn.com/problems/wiggle-subsequence/ 
 
如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。

例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。

给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。

示例 1:
	输入: [1,7,4,9,2,5]
	输出: 6 
	解释: 整个序列均为摆动序列。

示例 2:
	输入: [1,17,5,10,13,15,10,5,16,8]
	输出: 7
	解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。

示例 3:
	输入: [1,2,3,4,5,6,7,8,9]
	输出: 2

进阶: 你能否用 O(n) 时间复杂度完成此题?

----------------------------------------------------------------------------------------------------

A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

Example 1:
	Input: [1,7,4,9,2,5]
	Output: 6
	Explanation: The entire sequence is a wiggle sequence.

Example 2:
	Input: [1,17,5,10,13,15,10,5,16,8]
	Output: 7
	Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

Example 3:
	Input: [1,2,3,4,5,6,7,8,9]
	Output: 2

Follow up: Can you do it in O(n) time?
*/

class MySolution {
    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        if (len < 2) return len;

        int max = 1, i = 0;
        while (i < len - 1 && nums[i] == nums[i + 1]) i++;
        
        // dir: 1 表示 前数比后数小，呈涨势；-1 表示 前数比后数大，呈跌势
        int dir = nums[i] < nums[i + 1] ? 1 : -1;  // 确定第一个势是起是伏
        for (; i < len - 1;) {
            if (dir == 1) {
                while (i < len - 1 && nums[i] <= nums[i + 1]) {
                    i++;
                }
            } else {
                while (i < len - 1 && nums[i] >= nums[i + 1]) {
                    i++;
                }
            }

            max++;
            dir *= -1;
        }

        return max;
    }
}

class Solution1 {
    private static final int BEGIN = 0;
    public static final int UP = 1;
    public static final int DOWN = 2;

    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;

        int maxLength = 1;
        int state = BEGIN;
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            switch (state) {
                
                case BEGIN:
                    if (nums[i] > nums[i - 1]) {
                        state = UP;
                    } else if (nums[i] < nums[i - 1]) {
                        state = DOWN;
                    }
                    maxLength++;
                    break;
                    
                case UP:
                    if (nums[i] < nums[i - 1]) {
                        state = DOWN;
                        maxLength++;
                    }
                    break;
                    
                case DOWN:
                    if (nums[i] > nums[i - 1]) {
                        state = UP;
                        maxLength++;
                    }
                    break;
            }
        }
        return maxLength;
    }
}
