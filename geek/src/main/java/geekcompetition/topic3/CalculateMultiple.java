package geekcompetition.topic3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CalculateMultiple {
	/**
	 * 题目描述： 一个大于2的整数N，他可能等于比它小的若干个整数（大于等于2并且不等于自己）乘积。
	 * 如果存在这样的连续整数，将他们输出，如果没有则输出-1。 例：整数60，60=3*4*5。所以输出[3 4 5]
	 * ---------------------------------------------------------------------------
	 * 通过开方的方式计算一个整数分解成连续n个整数的乘积 其中涉及的逻辑：
	 * 若开方结果减去开方数的一半小于2则跳出开方循环
	 * 首先要理解开方结果减去开方数的一半表示开方结果左侧最小的那个值 最小的值必须大于2
	 * 当最小的那个乘数等于开方结果的时候，应该跳出计算，因为计算结果必定大于给定的数
	 * 
	 * @param string
	 * @return
	 */
	static String calculate(String string) {
		Double srcDoubleNumber = null;
		try {
			srcDoubleNumber = Double.valueOf(string);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		String str = "";
		if (Objects.nonNull(srcDoubleNumber) && srcDoubleNumber > 2) {
			for (int i = 2;; i++) {
				// 开方
				Double radicandLongNumber = Double.valueOf(StrictMath.pow(srcDoubleNumber, 1.0 / i));
				Double radicandHalf = Double.valueOf(i) / 2;
				if (radicandLongNumber - radicandHalf <= 2d)
					break;
				String result = calculate(srcDoubleNumber, radicandLongNumber, i, radicandHalf);
				if (Objects.nonNull(result)) {
					str = str + result + ",";
				}
			}
		}

		return str.length() > 0 ? str.substring(0, str.length() - 1) : "-1";
	}

	/**
	 * 循环计算
	 * 
	 * @param srcdoubleNumber
	 *            需要分解的数
	 * @param radicandLongNumber
	 *            开n次方后的数，开放后取Long+1
	 * @param radicand
	 *            开的N次方
	 * @param half
	 *            n次方的一半
	 * @return
	 */
	static String calculate(Double srcdoubleNumber, Double radicandLongNumber, int radicand, Double half) {
		// 如果开n次方后的数减去开的n次方的一半小于0则放弃该次计算
		if (radicandLongNumber - half > 0) {
			List<Long> list = new ArrayList<>(radicand);
			// 循环条件是最左边的数小于开方结果，因为最左边的数都等于开方结果的话，那么连乘结果一定是大于给定的数
			for (long start = (long) (radicandLongNumber - half); start <= radicandLongNumber; start++) {
				Long calcNumber = 1L;
				// 循环乘积，循环次数等于开的n次方
				for (int i = 0; i < radicand; i++) {
					Long multiplierNumber = start + i;
					calcNumber = calcNumber * multiplierNumber;
					list.add(multiplierNumber);
				}
				// 如果碰到乘积相等，则该次开方后计算此次分解成功
				if (calcNumber.compareTo(srcdoubleNumber.longValue()) == 0) {
					return list.toString();
				}
				// 清除list
				list.clear();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		String calculate = calculate("57274321104000");
		System.out.println(calculate);
	}
}
