/*
根据每日气温列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高的天数。如果之后都不会升高，请输入 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的都是 [30, 100] 范围内的整数。
*/

#include <iostream>
#include <vector>
#include <stack>

using std::vector;
using std::stack;
using std::pair;
using std::make_pair;

class Solution1 {
public:
	vector<int> dailyTemperatures(vector<int>& temperatures) 
	{
		stack<pair<int, int>>st;
		vector<int> nums(temperatures.size(), 0);
		for (int i = 0; i < temperatures.size(); i++) 
		{
			while (!st.empty() && st.top().first < temperatures[i]) 
			{
				nums[st.top().second] = (i - st.top().second);
				st.pop();
			}
			st.push(make_pair(temperatures[i], i));
		}
		return nums;
	}
};

class MySolution {
public:
	vector<int> dailyTemperatures(vector<int>& T) {
		vector<int> output;
		int size = T.size();
		for (int i = 0; i < size - 1; i++) {
			int j = i + 1;
			while (j < size) {
				if (T[j] > T[i]) {
					output.push_back(j - i);
					break;
				}
				j++;
			}
			if (j == size) output.push_back(0);
		}
		output.push_back(0);
		
		return output;
	}
};
