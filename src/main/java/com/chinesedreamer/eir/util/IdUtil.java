package com.chinesedreamer.eir.util;

import java.util.Calendar;
import java.util.Random;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class IdUtil {
	/**
	 * yyyymmdd + 随机序列
	 * @return
	 */
	public static String generateJobId() {
		Calendar calendar = Calendar.getInstance();
		StringBuilder builder = new StringBuilder();
		builder.append(DateFormatUtils.format(calendar, "yyyyMMddHHmmss"))
		.append("-");
		Random random = new Random(calendar.getTime().getTime());
		builder.append(random.nextInt(100))
		.append("-")
		.append(random.nextInt(10));
		return builder.toString();
	}
	
	private final static String[] hexDigits = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4",
			"5", "6", "7", "8", "9", "0", ".", "-", "*", "/", "'", ":", ";", ">", "<", "~", "!", "@", "#", "$", "%",
			"^", "&", "(", ")", "{", "}", "[", "]", "|" };

	public static String generateSalt(int size) {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		int temp = 0;
		for (int i = 0; i < size; i++) {
			temp = random.nextInt(hexDigits.length);
			builder.append(hexDigits[temp]);
		}
		return builder.toString();
	}
}
